/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * This class is where the ArrayLists of Bugs and Plants are held.
 * These Entities are moved around, collided off walls and each other, and gain or lose energy.
 * When an Entity has lost all its energy, it is moved to a deadEntiry ArrayList.
 */


import java.util.ArrayList;

public class World {

	//Fields
	private int height,width;
	private ArrayList<Bug> bugList=new ArrayList<Bug>();
	private ArrayList<Plant> plantList=new ArrayList<Plant>();
	private int numberOfBugs = 10;
	private int numberOfPlants=20;
	
	private ArrayList<Bug> deadBugs=new ArrayList<Bug>();
	private ArrayList<Plant> deadPlants=new ArrayList<Plant>();
	
	//class constructor
	public World(int w,int h) {
		this.height=h;
		this.width=w;	
	}
	
	public World() {
		
	}
	
	//generate bugs and plants to fill the world	
	public void populate() {
		
		//create the plants, making sure they do not overlap on any other plants previously created
		for(int i=0;i<this.numberOfPlants;i++) {
			float radius=7;
			boolean plantCollides=true;
			while(plantCollides) {
				double xTemp=(Math.random()*(this.width-2*radius)+radius);
				double yTemp=(Math.random()*(this.height-2*radius)+radius);
				plantCollides=detectCollide(xTemp,yTemp,radius);
				if(!plantCollides) {
					plantList.add(new Plant((float)xTemp, (float)yTemp, radius));
				}
			}
		}
		
		//create the bugs, making sure they do not overlap on any other plant or bug previously created
		for(int i=0;i<this.numberOfBugs;i++) {
			float radius=10;
			boolean bugCollides=true;
			while(bugCollides) {
				double xTemp=(Math.random()*(this.width-2*radius)+radius);
				double yTemp=(Math.random()*(this.height-2*radius)+radius);
				bugCollides=detectCollide(xTemp,yTemp,radius);
				if(!bugCollides) {
					bugList.add(new Bug((float)xTemp, (float)yTemp, radius));
				}
			}
		}

	}
	
	//allows the bugs to bounce off the walls
	public void collideWalls(Bug b) {

		//collides with left wall
		if(b.getXPos()<b.getRadius()) {
			b.setdx(-1);
			b.setTranslateX(b.getRadius()-b.getCenterX());
		}
		
		//collides with right wall
		if(b.getXPos()> this.width-b.getRadius()) {
			b.setdx(-1);
			b.setTranslateX(this.width-b.getRadius()-b.getCenterX());
		}
		
		//collides with top
		if(b.getYPos()<b.getRadius()) {
			b.setdy(-1);
			b.setTranslateY(b.getRadius()-b.getCenterY());
		}
		
		//collides with bottom
		if(b.getYPos()>this.height-b.getRadius()) {
			b.setdy(-1);
			b.setTranslateY(this.height-b.getRadius()-b.getCenterY());
		}
		
		//these are moved to update() b.move()
//		b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx());
//		b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy());
		
	} //end collides wall
	
	//allows a bug to bounce off any other bug in the world, but not itself
	public void collideBugs(Bug b, int bugI) {
		
		//extracting primary bug values
		double x1=b.getXPos();
		double y1=b.getYPos();
		double rad1=b.getRadius();
		
		for(int i=0;i<this.bugList.size();i++) {
			if(i!=bugI) {
				//extracting second bug values
				double x2=this.bugList.get(i).getXPos();
				double y2=this.bugList.get(i).getYPos();
				double rad2=this.bugList.get(i).getRadius();
				
				
				double minDistSqd=Math.pow(rad1+rad2, 2);
				double distSqd=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
				
				//if the two bugs are colliding, change both bug values
				if(distSqd<minDistSqd) {
					b.setdx(-1);
					b.setdy(-1);
					//these seem to be the most stable configuration
					b.setTranslateX(b.getTranslateX()+b.getdx());
					b.setTranslateY(b.getTranslateY()+b.getdy());
					
//					b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx()+b.getdxSign()*1.5);
//					b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy()+b.getdySign()*1.5);
					//maybe also move second ball values??
					
					this.bugList.get(i).setdx(-1);
					this.bugList.get(i).setdy(-1);
					//these seem to be the most stable configuration
					this.bugList.get(i).setTranslateX(this.bugList.get(i).getTranslateX()+this.bugList.get(i).getdx());
					this.bugList.get(i).setTranslateY(this.bugList.get(i).getTranslateY()+this.bugList.get(i).getdy());
					
//					this.bugList.get(i).getCircle().setTranslateX(this.bugList.get(i).getCircle().getTranslateX()+this.bugList.get(i).getdx()+this.bugList.get(i).getdxSign()*1.5);
//					this.bugList.get(i).getCircle().setTranslateY(this.bugList.get(i).getCircle().getTranslateY()+this.bugList.get(i).getdy()+this.bugList.get(i).getdySign()*1.5);
					
					//now need to move away if too close <<maybe not needed??
//					double newXVal=x2+Math.sqrt(minDistSqd-(Math.pow(y1-y2, 2)));
//					double newYVal=y2+Math.sqrt(minDistSqd-(Math.pow(x1-x2, 2)));
//					b.getCircle().setTranslateX(newXVal-b.getCircle().getCenterX());
//					b.getCircle().setTranslateY(newYVal-b.getCircle().getCenterY());
					
					//still not working
					//xI=((2*xJ)+sqrt(sq(2*xJ)-4*(sq(xJ)-minDistanceSqd))/(2));
//					double newXVal=(Math.pow(2*x2, 2)+Math.sqrt(Math.pow(2*x2, 2)-4*(Math.pow(x2, 2)-minDistSqd)))/2.0;
//					double newYVal=(Math.pow(2*y2, 2)+Math.sqrt(Math.pow(2*y2, 2)-4*(Math.pow(y2, 2)-minDistSqd)))/2.0;
//					b.getCircle().setTranslateX(newXVal-b.getCircle().getCenterX());
//					b.getCircle().setTranslateY(newYVal-b.getCircle().getCenterY());
				}
				
			}
		} //end for bugList
		
	} //end collideBugs
	
	
	//allows a bug to collide off plant objects
	public void collidePlants(Bug b) {
		
		//extracting bug values
		double x1=b.getXPos();
		double y1=b.getYPos();
		double rad1=b.getRadius();
		for(int i=0;i<this.plantList.size();i++) {
			
			//extracting plant values
			double x2=this.plantList.get(i).getXPos();
			double y2=this.plantList.get(i).getYPos();
			double rad2=this.plantList.get(i).getRadius();
			
			double minDistSqd=Math.pow(rad1+rad2, 2);
			double distSqd=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
			
			//if they collide, change bug movement and energy values, and plant energy values
			if(distSqd<minDistSqd) {
				b.setdx(-1);
				b.setdy(-1);

				//if bug hits a plant, plant gets eaten
				this.plantList.get(i).eaten();
				b.gainEnergy();

			}
		} //end for plantList
			
	} //end collidePlants
	
	//detects if an object will collide with any existing plants or bugs
	//collision with walls is accounted for in the generating x and y values stage (in populate)
	public boolean detectCollide(double x1, double y1, double rad1) {
		
		//detect collision with plants
		for(int i=0;i<this.plantList.size();i++) {		
			double x2=this.plantList.get(i).getXPos();
			double y2=this.plantList.get(i).getYPos();
			double rad2=this.plantList.get(i).getRadius();
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
		
		//detect collision with bugs
		for(int i=0;i<this.bugList.size();i++) {		
			double x2=this.bugList.get(i).getXPos();
			double y2=this.bugList.get(i).getYPos();
			double rad2=this.bugList.get(i).getRadius();
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
		
		return false;
	} //end detectCollide
	

	//applies all the movement updates
	public void update() {
		
		//apply all the movements
		for(int i=0;i<this.bugList.size();i++) {
			bugList.get(i).lookForFood(plantList);
			bugList.get(i).moveBug();
			collideWalls(this.bugList.get(i));
			collidePlants(this.bugList.get(i));
			collideBugs(this.bugList.get(i), i);
			bugList.get(i).move();
		}
		
		//update all the plants energy. If energy=0 (dead), move to deadplant list
		for(int i=0;i<this.plantList.size();i++) {
			if(this.plantList.get(i).getEnergy()<=0) {			
				this.deadPlants.add(this.plantList.get(i));
				this.plantList.remove(i);
			}
			
		}
		
		//update all the bugs energy. If energy=0 (dead), move to deadbug list
		for(int i=0;i<this.bugList.size();i++) {
			if(this.bugList.get(i).getEnergy()>0) {
				this.bugList.get(i).loseEnergy();
			}else {
				this.deadBugs.add(this.bugList.get(i));
				this.bugList.remove(i);
			}
		}
		
		
	}
	
	//updates world size based on scene size from AnimationUI
	public void updateWorldSize(double w, double h) {
		this.width=(int)w;
		this.height=(int)h;
	}
	
	public void restart() {
		this.plantList.clear();
		this.bugList.clear();
		this.deadBugs.clear();
		this.deadPlants.clear();
		
	}
	
	public void setNumbers(int bug, int plant) {
		this.numberOfBugs=bug;
		this.numberOfPlants=plant;
	}
	
	
	//getters and setters
	public ArrayList<Bug> getDeadBugs(){
		return this.deadBugs;
	}
	
	public ArrayList<Plant> getDeadPlants(){
		return this.deadPlants;
	}
	
	public ArrayList<Bug> getBugList(){
		return this.bugList;
	}
	
	public ArrayList<Plant> getPlantList(){
		return this.plantList;
	}
	
	public int getBugListSize() {
		return this.bugList.size();
	}
	
	public int getPlantListSize() {
		return this.plantList.size();
	}
	
	
	
	
}
