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
		//System.out.println("populated");
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
		
		//need to fix this so bugs don't collide while creating
		boolean bugAdded=false;
		for(int i=0;i<this.numberOfBugs;i++) {
			bugList.add(new Bug(this.width, this.height));
//			bugAdded=false;
//			while(!bugAdded) {
//				//float xtemp=(float)(Math.random()*(width-2*this.radius)+this.radius);
//			}
		}
		
		//bugList.add(new Bug(this.width,this.height));
		
	}
	
	public void collideWalls(Bug b) {
		
		//can't handle window size changes because it lives in world, not animation ui
		
		
		//System.out.println("collide walls");
		//collides with left wall
//		if(b.getXPos()<b.getRadius()) { 
//			b.setdx(-1);
//			b.setXPos(b.getRadius());
//			
//		}
		//Left hand wall
		if(b.getCircle().getCenterX()+b.getCircle().getTranslateX()<b.getCircle().getRadius()) {
			b.setdx(-1);
			//b.getCircle().setTranslateX(b.getCircle().getRadius());
		}
		
		//collides with right wall
//		if(b.getXPos()>this.width-b.getRadius()) {
//			b.setdx(-1);
//			b.setXPos(this.width-b.getRadius());
//		}
		
		if(b.getCircle().getCenterX()+b.getCircle().getTranslateX()> this.width-b.getCircle().getRadius()) {
			b.setdx(-1);
			//b.getCircle().setTranslateX(this.width-b.getCircle().getRadius());
		}
		
		//collides with top
//		if(b.getYPos()<0 ) {
//			b.setdy(-1);
//			b.setYPos(b.getRadius());
//		}
		
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()<b.getCircle().getRadius()) {
			b.setdy(-1);
			//b.getCircle().setTranslateY(b.getCircle().getRadius());
		}
		//collides with bottom
//		if(b.getYPos()>this.height-b.getRadius()) {
//			b.setdy(-1);
//			b.setYPos(this.width-b.getRadius());					
//		}	
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()>this.height-b.getCircle().getRadius()) {
			b.setdy(-1);
			//b.getCircle().setTranslateY(this.width-b.getCircle().getRadius());
			//balls[i].setYPos(scene.getWidth()-balls[i].getCircle().getRadius());
		}
		b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx());
		b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy());
		
	} //end collides wall
	
	public void collideBugs(Bug b, int bugI) {
		double x1=b.getCircle().getCenterX()+b.getCircle().getTranslateX();
		double y1=b.getCircle().getCenterY()+b.getCircle().getTranslateY();
		double rad1=b.getCircle().getRadius();
		
		for(int i=0;i<this.bugList.size();i++) {
			if(i!=bugI) {
				double x2=this.bugList.get(i).getCircle().getCenterX()+this.bugList.get(i).getCircle().getTranslateX();
				double y2=this.bugList.get(i).getCircle().getCenterY()+this.bugList.get(i).getCircle().getTranslateY();
				double rad2=this.bugList.get(i).getCircle().getRadius();
				
				//double minDistSqd=Math.pow(rad1+rad2, 2);
				
				if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
					b.setdx(-1);
					b.setdy(-1);
					b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx());
					b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy());
					//maybe also move second ball values??
					
					this.bugList.get(i).setdx(-1);
					this.bugList.get(i).setdy(-1);
					this.bugList.get(i).getCircle().setTranslateX(this.bugList.get(i).getCircle().getTranslateX()+this.bugList.get(i).getdx());
					this.bugList.get(i).getCircle().setTranslateY(this.bugList.get(i).getCircle().getTranslateY()+this.bugList.get(i).getdy());
					
					//now need to move away if too close
					
					
				}
				
			}
		} //end for bugList
		
	} //end collideBugs
	
/**	
	public boolean detectCollide(float x, float y, float rad) {
		
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
	
	*/
	
	public void update() {
		//System.out.println("update");
		//apply all the movements
		for(int i=0;i<bugList.size();i++) {
			collideWalls(bugList.get(i));
			
		}
		for(int i=0;i<bugList.size();i++) {
			collideBugs(bugList.get(i), i);
		}
		
	}
	
	//do i even need this method??
	public void draw() {
		//System.out.println("draw");
		//draw to the screen
		//clear what is on screen
		//redraw everything
		
		
	}
	
	//return bugList
	public ArrayList<Bug> getBugList(){
		return bugList;
	}
	
	public int getBugListSize() {
		return bugList.size();
	}
	
}
