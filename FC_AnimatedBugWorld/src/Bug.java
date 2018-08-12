import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bug {

	//Fields
	private float xPos,yPos,dx,dy;
	private Circle circle;  //maybe remake as final
	private float radius;
	private int energy;
	
	//basic constructor
	public Bug(int width, int height) {
		this.xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		this.yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.radius=20;
		this.energy=1000;
		
		this.circle=new Circle(xPos,yPos,radius);
		
		//from the internet to get random colours
		//https://stackoverflow.com/questions/35715283/set-text-to-random-color-opacity-javafx
		this.circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
	}
	
	//constructor with bug size inputs
	public Bug (int width, int height, float x, float y, float rad) {
		this.xPos=x;
		this.yPos=y;
		this.dx=-1.5f;
		this.dy=-1.5f;
		this.radius=rad;
		this.energy=1000;
		
		circle=new Circle(xPos,yPos,radius);
		
		//from the internet to get random colours
		//https://stackoverflow.com/questions/35715283/set-text-to-random-color-opacity-javafx
		circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
	}
	
	//bug needs an x,y,dx,dy,rad and circle to draw it
	//can i just redraw the circle at the x and y??
	
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
	
	public Circle getCircle() {
		return this.circle;
	}
	
	public float getXPos() {
		return this.xPos;
		//or return circle.getCenterX()+circle.getTranslateX??
	}
	
	public void setXPos(float x) {
		this.xPos=x;
		circle.setTranslateX(x);
	}
	
	public float getYPos() {
		return this.yPos;
	}
	
	public void setYPos(float y) {
		this.yPos=y;
		circle.setTranslateY(y);
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public void move() {
		this.xPos+=this.dx;
		this.yPos+=this.dy;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public void loseEnergy() {
		this.energy--;
	}
	
	public void gainEnergy() {
		this.energy=this.energy+400;
	}
	
	public void setColour() {
		this.circle.setFill(Color.WHITE);
	}
}
