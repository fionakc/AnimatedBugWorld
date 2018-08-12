import java.util.ArrayList;

//import main.Rabbit;

public class World {

	//Fields
	private int height,width;
	private ArrayList<Bug> bugList=new ArrayList<Bug>();
	private ArrayList<Plant> plantList=new ArrayList<Plant>();
	//also need plant arraylist
	private int numberOfBugs = 5;
	private int numberOfPlants=5;
	
	public World(int w,int h) {
		this.height=h;
		this.width=w;
	}
	
	
	
	public void populate() {
		
		for(int i=0;i<this.numberOfPlants;i++) {
			float radius=10;
			boolean plantCollides=true;
			while(plantCollides) {
				double xTemp=(Math.random()*(this.width-2*radius)+radius);
				double yTemp=(Math.random()*(this.height-2*radius)+radius);
				plantCollides=detectCollide(xTemp,yTemp,radius);
				if(!plantCollides) {
					plantList.add(new Plant(this.width, this.height, (float)xTemp, (float)yTemp, radius));
				}
			}
		}
		
		for(int i=0;i<this.numberOfBugs;i++) {
			float radius=15;
			boolean bugCollides=true;
			while(bugCollides) {
				double xTemp=(Math.random()*(this.width-2*radius)+radius);
				double yTemp=(Math.random()*(this.height-2*radius)+radius);
				bugCollides=detectCollide(xTemp,yTemp,radius);
				if(!bugCollides) {
					bugList.add(new Bug(this.width, this.height, (float)xTemp, (float)yTemp, radius));
				}
			}
		}
		
		
		//need to fix this so bugs don't collide while creating
//		boolean bugAdded=false;
//		for(int i=0;i<this.numberOfBugs;i++) {
//			bugList.add(new Bug(this.width, this.height));
////			bugAdded=false;
////			while(!bugAdded) {
////				//float xtemp=(float)(Math.random()*(width-2*this.radius)+this.radius);
////			}
//		}
		
		//bugList.add(new Bug(this.width,this.height));
		
	}
	
	public void collideWalls(Bug b) {
		
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
			b.getCircle().setTranslateX(b.getCircle().getRadius()-b.getCircle().getCenterX());
		}
		
		//collides with right wall
//		if(b.getXPos()>this.width-b.getRadius()) {
//			b.setdx(-1);
//			b.setXPos(this.width-b.getRadius());
//		}
		
		if(b.getCircle().getCenterX()+b.getCircle().getTranslateX()> this.width-b.getCircle().getRadius()) {
			b.setdx(-1);
			b.getCircle().setTranslateX(this.width-b.getCircle().getRadius()-b.getCircle().getCenterX());
		}
		
		//collides with top
//		if(b.getYPos()<0 ) {
//			b.setdy(-1);
//			b.setYPos(b.getRadius());
//		}
		
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()<b.getCircle().getRadius()) {
			b.setdy(-1);
			b.getCircle().setTranslateY(b.getCircle().getRadius()-b.getCircle().getCenterY());
		}
		//collides with bottom
//		if(b.getYPos()>this.height-b.getRadius()) {
//			b.setdy(-1);
//			b.setYPos(this.width-b.getRadius());					
//		}	
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()>this.height-b.getCircle().getRadius()) {
			b.setdy(-1);
			b.getCircle().setTranslateY(this.height-b.getCircle().getRadius()-b.getCircle().getCenterY());
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
				
				
				double minDistSqd=Math.pow(rad1+rad2, 2);
				double distSqd=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
				
				if(distSqd<minDistSqd) {
					b.setdx(-1);
					b.setdy(-1);
					b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx());
					b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy());
					//maybe also move second ball values??
					
					this.bugList.get(i).setdx(-1);
					this.bugList.get(i).setdy(-1);
					this.bugList.get(i).getCircle().setTranslateX(this.bugList.get(i).getCircle().getTranslateX()+this.bugList.get(i).getdx());
					this.bugList.get(i).getCircle().setTranslateY(this.bugList.get(i).getCircle().getTranslateY()+this.bugList.get(i).getdy());
					
					//now need to move away if too close <<maybe not needed??
//					double newXVal=x2+Math.sqrt(minDistSqd-(Math.pow(y1-y2, 2)));
//					double newYVal=y2+Math.sqrt(minDistSqd-(Math.pow(x1-x2, 2)));
//					b.getCircle().setTranslateX(newXVal-b.getCircle().getCenterX());
//					b.getCircle().setTranslateY(newYVal-b.getCircle().getCenterY());
				}
				
			}
		} //end for bugList
		
	} //end collideBugs
	
	//pass in a bug to collide off plant objects
	public void collidePlants(Bug b) {
		double x1=b.getCircle().getCenterX()+b.getCircle().getTranslateX();
		double y1=b.getCircle().getCenterY()+b.getCircle().getTranslateY();
		double rad1=b.getCircle().getRadius();
		for(int i=0;i<this.plantList.size();i++) {
			
			double x2=this.plantList.get(i).getCircle().getCenterX()+this.plantList.get(i).getCircle().getTranslateX();
			double y2=this.plantList.get(i).getCircle().getCenterY()+this.plantList.get(i).getCircle().getTranslateY();
			double rad2=this.plantList.get(i).getCircle().getRadius();
			
			double minDistSqd=Math.pow(rad1+rad2, 2);
			double distSqd=Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
			
			if(distSqd<minDistSqd) {
				b.setdx(-1);
				b.setdy(-1);
				b.getCircle().setTranslateX(b.getCircle().getTranslateX()+b.getdx());
				b.getCircle().setTranslateY(b.getCircle().getTranslateY()+b.getdy());


			}
		} //end for plantList
			
	} //end collidePlants
	
	public boolean detectCollide(double x1, double y1, double rad1) {
		
		for(int i=0;i<this.plantList.size();i++) {		
			double x2=this.plantList.get(i).getCircle().getCenterX()+this.plantList.get(i).getCircle().getTranslateX();
			double y2=this.plantList.get(i).getCircle().getCenterY()+this.plantList.get(i).getCircle().getTranslateY();
			double rad2=this.plantList.get(i).getCircle().getRadius();
			
			//double minDistSqd=Math.pow(rad1+rad2, 2);
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
		
		
		for(int i=0;i<this.bugList.size();i++) {		
			double x2=this.bugList.get(i).getCircle().getCenterX()+this.bugList.get(i).getCircle().getTranslateX();
			double y2=this.bugList.get(i).getCircle().getCenterY()+this.bugList.get(i).getCircle().getTranslateY();
			double rad2=this.bugList.get(i).getCircle().getRadius();
			
			//double minDistSqd=Math.pow(rad1+rad2, 2);
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
		
		
		return false;
	} //end detectCollide
	

	
	public void update() {
		//System.out.println("update");
		//apply all the movements
		for(int i=0;i<this.bugList.size();i++) {
			collideWalls(this.bugList.get(i));
			collidePlants(this.bugList.get(i));
			collideBugs(this.bugList.get(i), i);
			
		}
//		for(int i=0;i<bugList.size();i++) {
//			collideBugs(bugList.get(i), i);
//		}
		
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
	
	public void updateWorldSize(double w, double h) {
		this.width=(int)w;
		this.height=(int)h;
	}
	
}
