import java.util.ArrayList;

public class World {

	//Fields
	private int height,width;
	private ArrayList<Bug> bugList=new ArrayList<Bug>();
	private ArrayList<Plant> plantList=new ArrayList<Plant>();
	private int numberOfBugs = 5;
	private int numberOfPlants=5;
	
	private ArrayList<Bug> deadBugs=new ArrayList<Bug>();
	private ArrayList<Plant> deadPlants=new ArrayList<Plant>();
	
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

	}
	
	
	public void collideWalls(Bug b) {

		//collides with left hand wall
		if(b.getCircle().getCenterX()+b.getCircle().getTranslateX()<b.getCircle().getRadius()) {
			b.setdx(-1);
			b.getCircle().setTranslateX(b.getCircle().getRadius()-b.getCircle().getCenterX());
		}
		
		//collides with right wall
		if(b.getCircle().getCenterX()+b.getCircle().getTranslateX()> this.width-b.getCircle().getRadius()) {
			b.setdx(-1);
			b.getCircle().setTranslateX(this.width-b.getCircle().getRadius()-b.getCircle().getCenterX());
		}
		
		//collides with top
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()<b.getCircle().getRadius()) {
			b.setdy(-1);
			b.getCircle().setTranslateY(b.getCircle().getRadius()-b.getCircle().getCenterY());
		}
		
		//collides with bottom
		if(b.getCircle().getCenterY()+b.getCircle().getTranslateY()>this.height-b.getCircle().getRadius()) {
			b.setdy(-1);
			b.getCircle().setTranslateY(this.height-b.getCircle().getRadius()-b.getCircle().getCenterY());
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

				//if bug hits a plant, plant gets eaten
				this.plantList.get(i).eaten();
				//System.out.println("plant "+i+" has age "+this.plantList.get(i).getAge());
				b.gainEnergy();

			}
		} //end for plantList
			
	} //end collidePlants
	
	
	public boolean detectCollide(double x1, double y1, double rad1) {
		
		for(int i=0;i<this.plantList.size();i++) {		
			double x2=this.plantList.get(i).getCircle().getCenterX()+this.plantList.get(i).getCircle().getTranslateX();
			double y2=this.plantList.get(i).getCircle().getCenterY()+this.plantList.get(i).getCircle().getTranslateY();
			double rad2=this.plantList.get(i).getCircle().getRadius();
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
				
		for(int i=0;i<this.bugList.size();i++) {		
			double x2=this.bugList.get(i).getCircle().getCenterX()+this.bugList.get(i).getCircle().getTranslateX();
			double y2=this.bugList.get(i).getCircle().getCenterY()+this.bugList.get(i).getCircle().getTranslateY();
			double rad2=this.bugList.get(i).getCircle().getRadius();
			
			if(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)<Math.pow(rad1+rad2, 2)) {
				return true;
			}
		}
		
		return false;
	} //end detectCollide
	

	
	public void update() {
		
		//apply all the movements
		for(int i=0;i<this.bugList.size();i++) {
			collideWalls(this.bugList.get(i));
			collidePlants(this.bugList.get(i));
			collideBugs(this.bugList.get(i), i);
			
		}
		
		//update all the plants  <<not great
		for(int i=0;i<this.plantList.size();i++) {
			if(this.plantList.get(i).getAge()>0) {
				//plantList.get(i).ageUp();
			} else {
				this.deadPlants.add(this.plantList.get(i));
				this.plantList.remove(i);
			}
			
		}
		
		for(int i=0;i<this.bugList.size();i++) {
			if(this.bugList.get(i).getEnergy()>0) {
				this.bugList.get(i).loseEnergy();
			}else {
				this.deadBugs.add(this.bugList.get(i));
				this.bugList.remove(i);
			}
		}
		
	}
	
	//do i even need this method??
	public void draw() {
		//System.out.println("draw");
		//draw to the screen
		//clear what is on screen
		//redraw everything
		
		
	}
	
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
	
	public void updateWorldSize(double w, double h) {
		this.width=(int)w;
		this.height=(int)h;
	}
	
	
	
}
