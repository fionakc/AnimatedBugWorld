/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * The class Plant extends Entity, which itself extends Circle.
 * The plant is represented by one of two randomly selected images.
 * If a bug eats the plant it will lose energy.
 * 
 */

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Plant extends Entity {

	//Plant has no unique fields
	
	public Plant(float x, float y, float rad) {
		super(x, y, rad);
		this.energy=5;
		chooseImage();
	}
	
	//choose which plant image to display
	public void chooseImage() {
		int direction = (int)(Math.random()*2);
		if(direction<1) {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("leaf1.png"))));
		}else {
			setFill(new ImagePattern(new Image(getClass().getResourceAsStream("leaf2.png"))));
		}
	}
	
	//plant looses energy if it is eaten, but only if it still has energy
	public void eaten() {
		if(this.energy>0) {
			this.energy--;			
		}			
	}
	
	
}
