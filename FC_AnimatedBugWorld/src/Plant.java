import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant {

	//Fields
	private float xPos,yPos;
	private Circle circle;  //maybe remake as final
	private float radius;
	private int age;

	//basic constructor
	public Plant(int width, int height) {
		this.xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		this.yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		
		this.radius=20;
		this.age=5;
		this.circle=new Circle(xPos,yPos,radius);

		this.circle.setFill(Color.GREEN);
		
	}
	
	//constructor with plant size inputs
	public Plant (int width, int height, float x, float y, float rad) {
		this.xPos=x;
		this.yPos=y;
		
		this.radius=rad;
		this.age=5;
		circle=new Circle(xPos,yPos,radius);
		
		this.circle.setFill(Color.GREEN);
			
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public Circle getCircle() {
		return this.circle;
	}
	
	public void ageUp() {
		//if(this.age!=9) {
			this.age++;
		//}
	}
	
	public void eaten() {
		//can only eat plant if it still has age/energy
		if(this.age>0) {
			this.age--;			
		}			
		
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setColour() {
		this.circle.setFill(Color.WHITE);
	}
	
}
