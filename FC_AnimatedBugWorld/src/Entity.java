/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * The class Entity extends Circle, and is a superclass of Bug and Plant.
 */


import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Entity extends Circle {

	//Fields
	protected float xPos,yPos,rad;
	protected int energy;
	
	public Entity(float x, float y, float rad) {
		//creates a circle object with these values
		super((double)(x),(double)(y),(double)(rad));	
	}
	
	//return the x position of the circle object
	public double getXPos() {
		return getCenterX()+getTranslateX();
	}
	
	//return the y position of the circle object
	public double getYPos() {
		return getCenterY()+getTranslateY();
	}

	//return the energy of the entity object
	public int getEnergy() {
		return this.energy;
	}
}
