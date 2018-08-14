/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * The class Bug extends Entity, which itself extends Circle.
 * The bug is represented by one of three randomly chosen images.
 * The bug will look for food nearby it, and will move towards it.
 * If no food is found nearby, the bug will move randomly.
 * Random movement: the bug will move in a straight diagonal line for 50 frames.
 * Then it will pick a new diagonal to move along (which may be the same as the previous diagonal).
 * If the bug eats a plant it will gain energy.
 * Every move the bug makes looses it energy.
 * 
 */

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bug extends Entity{

	//Fields
	private float dx,dy;
	private int movement=0;
	private ArrayList<Plant> foodNearby =new ArrayList<Plant>();
	
	public Bug(float x, float y, float rad) {
		super(x, y, rad);
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.energy=1000;
		chooseImage();
	}
	
	//choose which bug image to display
	public void chooseImage() {
		int direction = (int)(Math.random()*3);
		if(direction<1) {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("beetle.png"))));
		}else if (direction<2){
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("bug1.png"))));
		}else {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("bug2.png"))));
		}	
	}

	//get the bug to look for food nearby it
	public void lookForFood(ArrayList<Plant> plantList) {
		foodNearby.clear();
		double radOffset=getRadius()*5; //how far away the bug will look for food

		for(int i=0;i<plantList.size();i++) {		
			double x2=plantList.get(i).getXPos();
			double y2=plantList.get(i).getYPos();
			double rad2=plantList.get(i).getRadius();
			
			if(Math.pow(getXPos()-x2, 2)+Math.pow(getYPos()-y2, 2)<Math.pow(getRadius()+rad2+radOffset, 2)) {
				foodNearby.add(plantList.get(i));
			}
		}
	}
	
	//move the bug according to if it can see food or not
	public void moveBug() {
		if(foodNearby.isEmpty()) {
			changeDirectionRandom();
		}else {
			moveToFood(foodNearby.get(0));			
		}
	}
	
	//directs the bug towards the food by manipulating the bug's dx and dy values
	public void moveToFood(Plant plant) {		
		if(getXPos()>plant.getXPos() && getYPos()>plant.getYPos()) {
			setDirection(-1,-1);
		} else if(getXPos()<plant.getXPos() && getYPos()>plant.getYPos()) {
			setDirection(1,-1);
		} else if(getXPos()<plant.getXPos() && getYPos()<plant.getYPos()) {
			setDirection(1,1);
		} else {
			setDirection(-1,1);
		}	
	}
	
	
	//actually moves the bug
	public void move() {		
		setTranslateX(getTranslateX()+this.dx);
		setTranslateY(getTranslateY()+this.dy);
	}
	
	//directs the bug to move in a random direction, by manipulating the bug's dx and dy values,
	//but only if movement value is over threshold
	public void changeDirectionRandom() {
		if(this.movement>50) {
			int direction = (int)(Math.random()*4);		
			switch (direction) {			
				case 0: setDirection(1,-1);
						break;
				case 1:	setDirection(-1,1);
						break;
				case 2: setDirection(-1,-1);
						break;
				case 3: setDirection(1,1);
						break;		
			} 
			this.movement=0;
		}		
	}
	
	//every time bug moves, lose energy and log a movement
	public void loseEnergy() {
		this.energy--;
		this.movement++;
	}
	
	//gain energy for every plant eaten
	public void gainEnergy() {
		this.energy=this.energy+50;
	}

	//getters and setters
	public float getdx() {
		return this.dx;
	}
	
	public float getdy() {
		return this.dy;
	}

	public void setdx(int change) {
		this.dx=change*this.dx;
	}
		
	public void setdy(int change) {
		this.dy=change*this.dy;
	}
	
	public void setDirection(int changeX, int changeY) {
		this.dx=changeX*Math.abs(this.dx); 
		this.dy=changeY*Math.abs(this.dy); 
	}
	
}
