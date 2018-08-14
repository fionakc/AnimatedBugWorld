/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * This programme will generate a window where you can view a bugworld.
 * There will be bugs and plants moving around the window.
 * The bugs will look for the plants, and move towards them if they are close enough to see them.
 * Otherwise they will move randomly.
 * If the bugs eat too much of the plant's energy, the plants will die and disappear.
 * If the bugs go too long without eating, they will run out of energy, die, and disappear.
 * 
 * You can choose the starting number of bugs and plants (up to 50).
 * Otherwise the default values will be used.
 * The start button starts the simulation.
 * Pressing it again will restart the simulation using the numbers in text fields.
 * The clear button will empty the bugworld.
 * The pause button will allow the movements in the bugworld to be paused onscreen.
 * The resume button will restart the movements without resetting any values.
 * 
 * There is an intermittent issue where sometimes the bugs will get stuck on each other.
 * With more time this could possibly be resolved by moving one of the bugs further away after it has collided.
 * 
 */



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationUI extends Application {

	//Fields
	private int width=600, height=500;
	
	private Pane panel=new Pane();	
	private BorderPane layoutpane = new BorderPane();
	private final Scene scene=new Scene(layoutpane,width,height);
	private World startWorld=new World();
	
	private TextField numBugs=new TextField();
	private TextField numPlants=new TextField();
	private boolean running =false;
	
	private int numBugDefault=10;
	private int numPlantDefault=20;
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//what to do at each keyframe
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateBugWorld();			
			}		
		});
		
		Timeline tl= TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build();
		tl.play();
		
		//create a start button for bugworld
		Button startBtn=new Button();
		startBtn.setText("Start");
		startBtn.setMaxWidth(Double.MAX_VALUE);
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { 	//what we want to happen when button pressed
				if(!running) {						//if bugworld not already running, start bugworld
					startBugWorld();
					running=true;
				} else {							//if bugworld is already running, stop it and start a new bugworld
					clearBugWorld();
					startBugWorld();
					running=true;
				}
			}
			
		}); //end startBtn set action
		
			
		//create a reset button
		Button resetBtn=new Button();
		resetBtn.setText("Clear");
		resetBtn.setMaxWidth(Double.MAX_VALUE);
		
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { 	//what we want to happen when button pressed				
				clearBugWorld();
				running=false;			
			}		
		}); //end resettBtn set action
		
			
		//create a pause button
		Button pauseBtn=new Button();
		pauseBtn.setText("Pause");
		pauseBtn.setMaxWidth(Double.MAX_VALUE);
		
		pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				tl.pause();
			}			
		}); //end pauseBtn set action
		
		//create a resume button
		Button resumeBtn=new Button();
		resumeBtn.setText("Resume");
		resumeBtn.setMaxWidth(Double.MAX_VALUE);
		
		resumeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				tl.play();
			}			
		}); //end resumeBtn set action
		

		//create heading
		Label title=new Label("Welcome to Bug World");
		title.setAlignment(Pos.CENTER);		
		title.setFont(Font.font ("Verdana", 20));
		title.setPadding(new Insets(15));
		
		//create labels for the text fields
		Label numBugLabel=new Label("Number of Bugs (max 50)");
		Label numPlantLabel=new Label("Number of Plants (max 50)");
		numBugs.setText(String.valueOf(numBugDefault));
		numPlants.setText(String.valueOf(numPlantDefault));
				
		//create border around bug movement window
		panel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		//create menu for buttons/textfields down the left side
		VBox leftside=new VBox();
		leftside.getChildren().addAll(numBugLabel, numBugs, numPlantLabel, numPlants, startBtn,resetBtn,pauseBtn,resumeBtn);
		leftside.setPadding(new Insets(10));
		leftside.setSpacing(10);
		
		//add things to borderpane
		BackgroundFill backFill=new BackgroundFill(Color.WHITE,null, null);
		layoutpane.setBackground(new Background(backFill)); 
		layoutpane.setPadding(new Insets(15, 10, 10, 10));
		layoutpane.setTop(title);
		layoutpane.setLeft(leftside);
		layoutpane.setCenter(panel);

		//display everything
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
	//set up the bugworld, fill it with bugs and plants
	public void startBugWorld() {
		findStartNumbers();				
		startWorld.populate();
		
		//put plants into group individually
		int plantListSize=startWorld.getPlantListSize();
		for(int i=0;i<plantListSize;i++) {
			panel.getChildren().add(startWorld.getPlantList().get(i));
		}
		
		//put bugs into group individually
		int bugListSize=startWorld.getBugListSize();
		for(int i=0;i<bugListSize;i++) {
			panel.getChildren().add(startWorld.getBugList().get(i));
		}	
		
	}
	
	//remove all bugs and plants from bugworld
	public void clearBugWorld() {
		//remove plants from group individually				
		for(int i=0;i<startWorld.getPlantList().size();i++) {
			panel.getChildren().remove(startWorld.getPlantList().get(i));
		}
		
		//remove bugs from group individually
		for(int i=0; i<startWorld.getBugList().size();i++) {
			panel.getChildren().remove(startWorld.getBugList().get(i));
		}	
		
		//clear out world values
		startWorld.restart();
	}
	
	//the updates to occur each keyFrame
	public void updateBugWorld() {
		
		startWorld.updateWorldSize(panel.getWidth(), panel.getHeight());
		startWorld.update();
				
		//remove dead plants and bugs from scene		
		for(int i=0;i<startWorld.getDeadPlants().size();i++) {
			panel.getChildren().remove(startWorld.getDeadPlants().get(i));
		}
		for(int i=0;i<startWorld.getDeadBugs().size();i++) {
			panel.getChildren().remove(startWorld.getDeadBugs().get(i));
		}	
	}
	
	//pass startWorld the initial plant and bug numbers
	public void findStartNumbers() {
		
		//check the number of bugs is valid and less than 50
		boolean numBugsValid=isInteger(numBugs.getText());		//check that text input is a valid number
		int numBugInt=numBugDefault;							//set int to default value
		if(numBugsValid) {										//if text input is valid
			numBugInt=Integer.valueOf(numBugs.getText());		//set int to input value
			if(numBugInt>50) {									//if int is larger than 50
				numBugInt=50;									//set int to 50
			}													//this is so the programme doesn't slow down too much
		}
		
		//check the number of plants is valid and less than 50
		boolean numPlantsValid=isInteger(numPlants.getText());
		int numPlantInt=numPlantDefault;		
		if(numPlantsValid) {
			numPlantInt=Integer.valueOf(numPlants.getText());
			if(numPlantInt>50) {
				numPlantInt=50;
			}
		}
		
		//display the decided upon numbers in the textfields
		numBugs.setText(String.valueOf(numBugInt));
		numPlants.setText(String.valueOf(numPlantInt));
		
		//pass the bug and plant numbers to the world object
		startWorld.setNumbers(numBugInt, numPlantInt);
	}
	
	//check the String num is a valid number (positive integer)
	public boolean isInteger(String num) { 
		//if num is null
		if(num.equals(null)) {
			return false;
		}
		//if nothing entered
		if(num.isEmpty()) {
			return false;
		}
		//convert string to individual chars, checks if any char is not a number
		//also prevents negative numbers
		for(int i=0;i<num.length();i++) {
			char c = num.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
		}
		return true;	
	}

	
	public static void main(String[] args) {
		launch();
	}

}
