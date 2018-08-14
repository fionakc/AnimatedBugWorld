/**Animated Bug World
 * by Fiona Crook
 * 300442873
 * 
 * There are 5 classes in this programme:
 * AnimationUI, World, Entity, Bug, Plant
 * 
 * This programme with generate a window where you can view a bugworld.
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
	int width=600, height=500;
	
	Pane panel=new Pane();
	
	BorderPane layoutpane = new BorderPane();
	final Scene scene=new Scene(layoutpane,width,height);
	World startWorld=new World();
	
	TextField numBugs=new TextField();
	TextField numPlants=new TextField();
	boolean running =false;
	

	
	
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
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				if(!running) {
					startBugWorld();
					running=true;
					
				} else {
					clearBugWorld();
					startBugWorld();
					running=true;
					
				}
			}
			
		}); //end startBtn set action
		
		
		
		
		//create a reset button
		Button resetBtn=new Button();
		resetBtn.setText("Clear");
		
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				
				clearBugWorld();
				running=false;
				
			}
			
		}); //end resettBtn set action
		
		
		
		
//		Button pauseBtn=new Button();
//		pauseBtn.setText("Pause");
//		
//		pauseBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
//				tl.pause();
//			}
//			
//		}); //end pauseBtn set action
		
		//make window look nicer

		//create heading
		Label title=new Label("Welcome to Bug World");
		title.setAlignment(Pos.CENTER);
		BackgroundFill backFill=new BackgroundFill(Color.WHITE,null, null);
		//title.setBackground(new Background(backFill));
		title.setFont(Font.font ("Verdana", 20));
		title.setPadding(new Insets(15));
		
		//create labels for the text fields
		Label numBugLabel=new Label("Number of Bugs (max 50)");
		Label numPlantLabel=new Label("Number of Plants (max 50)");
		numBugs.setText("10");
		numPlants.setText("20");
		
		
		//create border around bug movement window
		panel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		
		//menu for buttons/textfields down the left side
		VBox leftside=new VBox();
		leftside.getChildren().addAll(numBugLabel, numBugs, numPlantLabel, numPlants, startBtn,/**pauseBtn,*/resetBtn);
		leftside.setPadding(new Insets(10));
		leftside.setSpacing(10);
		
		//add things to borderpane
		layoutpane.setBackground(new Background(backFill));  //set background to white
		layoutpane.setPadding(new Insets(15, 20, 10, 10));
		layoutpane.setTop(title);
		layoutpane.setLeft(leftside);
		layoutpane.setCenter(panel);

		//display everything
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
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
		boolean numBugsValid=isInteger(numBugs.getText());
		int numBugInt=10;		
		if(numBugsValid) {
			numBugInt=Integer.valueOf(numBugs.getText());
			if(numBugInt>50) {
				numBugInt=50;
				numBugs.setText("50");
			}
		}
		
		//check the number of plants is valid and less than 50
		boolean numPlantsValid=isInteger(numPlants.getText());
		int numPlantInt=10;		
		if(numPlantsValid) {
			numPlantInt=Integer.valueOf(numPlants.getText());
			if(numPlantInt>50) {
				numPlantInt=50;
				numPlants.setText("50");
			}
		}
		
		startWorld.setNumbers(numBugInt, numPlantInt);
		
	}
	
	//check the String is a valid number
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
