import java.util.ArrayList;

//import main.Rabbit;

public class World {

	//Fields
	private int height,width;
	private ArrayList<Bug> bugList=new ArrayList<Bug>();
	//also need plant arraylist
	private int numberOfBugs = 5;
	
	public World(int w,int h) {
		this.height=h;
		this.width=w;
	}
	
	public void populate() {
		System.out.println("populated");
		//create bug and plant objects, store in arraylists
		/**
		 * boolean rabbitAdded=false;		
		for(int i=0;i<this.numberOfRabbits;i++) {
			rabbitAdded=false;
			while(!rabbitAdded) {
				int Xpos = (int)(Math.random()*(this.worldWidthx)); //create new animal position values
				int Ypos = (int)(Math.random()*(this.worldHeighty));
				boolean checkCollide=detectCollideAll(Xpos,Ypos);
				if(!checkCollide) {
					Rabbit rabbitTemp = new Rabbit (Xpos,Ypos);
					this.animalList.add(rabbitTemp);
					rabbitAdded=true;
				} 				
			} 				
		} 
		 */
//		boolean bugAdded=false;
//		for(int i=0;i<this.numberOfBugs;i++) {
//			bugAdded=false;
//			while(!bugAdded) {
//				//float xtemp=(float)(Math.random()*(width-2*this.radius)+this.radius);
//			}
//		}
		
		bugList.add(new Bug(this.width,this.height));
		
	}
	
	public void collideWalls(Bug b) {
		System.out.println("collide walls");
		//collides with left wall
		if(b.getXPos()<b.getRadius()) { 
			b.setdx(-1);
			b.setXPos(b.getRadius());
			
		}
		
		//collides with right wall
		if(b.getXPos()>this.width-b.getRadius()) {
			b.setdx(-1);
			b.setXPos(this.width-b.getRadius());
		}
		
		//collides with top
		if(b.getYPos()<0 ) {
			b.setdy(-1);
			b.setYPos(b.getRadius());
		}
		
		//collides with bottom
		if(b.getYPos()>this.height-b.getRadius()) {
			b.setdy(-1);
			b.setYPos(this.width-b.getRadius());
		}	
	} //end collides wall
	
	public boolean detectCollide(float x, float y, float rad) {
		//collide against walls
		
		//collides with sides
		if(x<rad || x>this.width-rad) {
			return true;
		}
		//collides with top and bottom
		if(y<0 || y>this.height-rad) {
			return true;
		}	
		
		//collide against plants
		
		//collide against other bugs
		
		
		for (int i=0;i<this.bugList.size();i++) {
			float x2=bugList.get(i).getXPos();
	        float y2=bugList.get(i).getYPos();
	        float rad2=bugList.get(i).getRadius();
	
	
	        float minDistanceSqd=(float) Math.pow(rad+rad2,2);
	        float distanceSqd=(float) (Math.pow(x-x2,2)+Math.pow(y-y2,2));
			
			if(distanceSqd<minDistanceSqd) {
				return true;
			} //end if check
			
		} //end for bugList
		
		
		return false;
	}
	
	public void update() {
		System.out.println("update");
		//apply all the movements
		for(int i=0;i<bugList.size();i++) {
			collideWalls(bugList.get(i));
		}
	}
	
	public void draw() {
		System.out.println("draw");
		//draw to the screen
		//clear what is on screen
		//redraw everything
	}
	
}
