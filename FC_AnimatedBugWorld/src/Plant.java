import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Plant extends Entity {

	//Fields
	//private float xPos,yPos;
	//private Circle circle;  //maybe remake as final
	//private float radius;
	//private int energy;
	
	//maybe move choosing image to another method??
	//Image image1=new Image(getClass().getResourceAsStream("leaf1.png"));
	//Image image2=new Image(getClass().getResourceAsStream("leaf2.png"));

	/**
	//basic constructor
	public Plant(int width, int height) {
		this.xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		this.yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		
		this.radius=20;
		this.energy=5;
		this.circle=new Circle(xPos,yPos,radius);

		this.circle.setFill(Color.GREEN);
		
	}*/
	/**
	//constructor with plant size inputs
	public Plant (float x, float y, float rad) {
		this.xPos=x;
		this.yPos=y;
		
		this.radius=rad;
		this.energy=5;
		circle=new Circle(xPos,yPos,radius);
		
		//picks one of two images for the plant
		int direction = (int)(Math.random()*2);
		if(direction<1) {
			this.circle.setFill(new ImagePattern(image1));
		}else {
			this.circle.setFill(new ImagePattern(image2));
		}
		
		//this.circle.setFill(Color.GREEN);
			
	}
	*/
	
	public Plant(float x, float y, float rad) {
		super(x, y, rad);
		this.energy=5;
		chooseImage();
	}
	/**
	public double getXPos() {
		return this.circle.getCenterX()+this.circle.getTranslateX();
	}
	
	public double getYPos() {
		return this.circle.getCenterY()+this.circle.getTranslateY();
	}
	
	public double getRadius() {
		return this.circle.getRadius();
	}
	*/
	
	public void chooseImage() {
		int direction = (int)(Math.random()*2);
		if(direction<1) {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("leaf1.png"))));
		}else {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("leaf2.png"))));
		}
	}
	
	//this method no longer works??
//	public Circle getCircle() {
//		return this.circle;
//	}
	
	public void ageUp() {
		//if(this.age!=9) {
			this.energy++;
		//}
	}
	
	public void eaten() {
		//can only eat plant if it still has age/energy
		if(this.energy>0) {
			this.energy--;			
		}			
	}
	
	/**
	public int getEnergy() {
		return this.energy;
	}
	*/
	
	
}
