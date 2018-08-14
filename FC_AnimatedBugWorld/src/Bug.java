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
 */

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Bug extends Entity{

	//Fields
//	private float xPos,yPos,dx,dy;
	private float dx,dy;
//	private Circle circle;  //maybe remake as final
//	private float radius;
//	private int energy;
	private int movement=0;
//	Image image=new Image(getClass().getResourceAsStream("beetle.png"));
	//Paint imageView = new ImageView(image);
	private ArrayList<Plant> foodNearby =new ArrayList<Plant>();
	
	/**
	//basic constructor
	public Bug(int width, int height) {
		this.xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		this.yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.radius=20;
		this.energy=1000;
		
		this.circle=new Circle(xPos,yPos,radius);
		this.circle.setFill(new ImagePattern(image));
		//p.setFill(new ImagePattern(flower, 0, 0, 1, 1, true));
		
		//from the internet to get random colours
		//https://stackoverflow.com/questions/35715283/set-text-to-random-color-opacity-javafx
		//this.circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
	}
	
	//constructor with bug size inputs
	public Bug (float x, float y, float rad) {
		this.xPos=x;
		this.yPos=y;
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.radius=rad;
		this.energy=1000;
		
		this.circle=new Circle(xPos,yPos,radius);
		
		//randomly pic bug icon
		int direction = (int)(Math.random()*3);
		if(direction<1) {
			this.circle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("beetle.png"))));
		}else if (direction<2){
			this.circle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("bug1.png"))));
		}else {
			this.circle.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("bug2.png"))));
		}			
		//this.circle.setFill(new ImagePattern(image));
		//from the internet to get random colours
		//https://stackoverflow.com/questions/35715283/set-text-to-random-color-opacity-javafx
		//circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
	}
	*/
	
	public Bug(float x, float y, float rad) {
		super(x, y, rad);
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.energy=1000;
		chooseImage();
	}
	
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

	public void lookForFood(ArrayList<Plant> plantList) {
		foodNearby.clear();
		double radOffset=getRadius()*5;

		for(int i=0;i<plantList.size();i++) {		
			double x2=plantList.get(i).getXPos();
			double y2=plantList.get(i).getYPos();
			double rad2=plantList.get(i).getRadius();
			
			if(Math.pow(getXPos()-x2, 2)+Math.pow(getYPos()-y2, 2)<Math.pow(getRadius()+rad2+radOffset, 2)) {
				foodNearby.add(plantList.get(i));
			}
		}
	}
	
	public void moveBug() {
		if(foodNearby.isEmpty()) {
			changeDirectionRandom();
		}else {
			moveToFood(foodNearby.get(0));
				
			
		}
	}
	
	public void moveToFood(Plant plant) {
		//if bug is right and down from plant
		if(getXPos()>plant.getXPos() && getYPos()>plant.getYPos()) {
			setDirection(-1,-1);
		} else if(getXPos()<plant.getXPos() && getYPos()>plant.getYPos()) {
			setDirection(1,-1);
		} else if(getXPos()<plant.getXPos() && getYPos()<plant.getYPos()) {
			setDirection(1,1);
		} else {
			setDirection(-1,1);
		}
		//move();
	}
	
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
	
//	public Circle getCircle() {
//		return this.circle;
//	}
	/**
	public double getXPos() {
		//return this.xPos;
		return this.circle.getCenterX()+this.circle.getTranslateX();
	}
	*/
//	
//	public void setXPos(float x) {
//		this.xPos=x;
//		circle.setTranslateX(x);
//	}
//	
	/**
	public double getYPos() {
		//return this.yPos;
		return this.circle.getCenterY()+this.circle.getTranslateY();
	}
	*/
//	
//	public void setYPos(float y) {
//		this.yPos=y;
//		circle.setTranslateY(y);
//	}
//	
	/**
	public double getRadius() {
		return this.circle.getRadius();
	}
	*/
//	
	public void setDirection(int changeX, int changeY) {
		this.dx=changeX*Math.abs(this.dx); //this.dx;
		this.dy=changeY*Math.abs(this.dy); //this.dx;
	}
	
	public void move() {		
		setTranslateX(getTranslateX()+this.dx);
		setTranslateY(getTranslateY()+this.dy);
	}
	
	
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
		//move();
		
	}
	
	/**
	public int getEnergy() {
		return this.energy;
	}
	*/
	
	//every time bug moves, lose energy and log a movement
	public void loseEnergy() {
		this.energy--;
		this.movement++;
	}
	
	public void gainEnergy() {
		this.energy=this.energy+400;
	}
	
//	public int getdxSign() {
//		if(this.dx<0) {
//			return -1;
//		}else {
//			return 1;
//		}
//	}
//	
//	public int getdySign() {
//		if(this.dy<0) {
//			return -1;
//		}else {
//			return 1;
//		}
//	}
}
