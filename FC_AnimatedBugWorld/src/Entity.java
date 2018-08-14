import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Entity extends Circle {

	//Fields
	protected float xPos,yPos,rad;
	protected int energy;
	protected Image image;
	
	public Entity(float x, float y, float rad) {
		//creates a circle object with these values
		super((double)(x),(double)(y),(double)(rad));	
	}
	
	public double getXPos() {
		return getCenterX()+getTranslateX();
	}
	
	public double getYPos() {
		return getCenterY()+getTranslateY();
	}
	
	//get radius is already a circle method
	
	//unsure how to return circle??
	
	public int getEnergy() {
		return this.energy;
	}
}
