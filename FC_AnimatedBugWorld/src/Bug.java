import javafx.scene.shape.Circle;

public class Bug {

	//Fields
	private float xPos,yPos,dx,dy;
	private Circle circle;  //maybe remake as final
	private float radius=5;
	
	public Bug(int width, int height) {
		xPos=(float) (Math.random()*(width-2*this.radius)+this.radius);
		yPos=(float) (Math.random()*(height-2*this.radius)+this.radius);
		dx=-1.5f;
		dy=-1.5f;
		
		circle=new Circle(xPos,yPos,radius);
		
	}
	
	//bug needs an x,y,dx,dy,rad and circle to draw it
	//can i just redraw the circle at the x and y??
	
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
		//or return circle.getCenterX()??
	}
	
	public void setXPos(float x) {
		this.xPos=x;
		circle.setCenterX(x);
	}
	
	public float getYPos() {
		return this.yPos;
	}
	
	public void setYPos(float y) {
		this.yPos=y;
		circle.setCenterY(y);
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public void move() {
		this.xPos+=this.dx;
		this.yPos+=this.dy;
	}
}
