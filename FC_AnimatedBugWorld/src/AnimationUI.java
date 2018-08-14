import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
	
	BorderPane pane = new BorderPane();
	final Scene scene=new Scene(pane,width,height);
	World startWorld=new World();
	
	TextField numBugs=new TextField();
	TextField numPlants=new TextField();
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane.setCenter(panel);

		numBugs.setText("10");
		numPlants.setText("20");
		
		
		//create a start button for bugworld
		Button startBtn=new Button();
		startBtn.setText("Start");
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
								
				findStartNumbers();				
				startWorld.populate();
				
				//put plants into group individually
				int plantListSize=startWorld.getPlantListSize();
				for(int i=0;i<plantListSize;i++) {
					panel.getChildren().add(startWorld.getPlantList().get(i));
					//group.getChildren().add(startWorld.getPlantList().get(i).getCircle());
				}
				
				//put bugs into group individually
				int bugListSize=startWorld.getBugListSize();
				for(int i=0;i<bugListSize;i++) {
					panel.getChildren().add(startWorld.getBugList().get(i));
					//group.getChildren().add(startWorld.getBugList().get(i).getCircle());
				}		
			}
			
		}); //end startBtn set action
		
		//create a reset button
		Button resetBtn=new Button();
		resetBtn.setText("Reset");
		
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
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
			
		}); //end resettBtn set action
		
		//what to do at each keyframe
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateBugWorld();
				
			}
			
		});
		
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();
		
		//make screen look nicer
		pane.setPadding(new Insets(15, 20, 10, 10));

		Label title=new Label("Welcome to Bug World");
		title.setAlignment(Pos.CENTER);
		BackgroundFill backFill=new BackgroundFill(Color.WHITE,null, null);
		title.setBackground(new Background(backFill));
		title.setFont(Font.font ("Verdana", 20));
		title.setPadding(new Insets(15));
		
		
		Label numBugLabel=new Label("Number of Bugs (max 50)");
		Label numPlantLabel=new Label("Number of Plants (max 50)");
		
		
		panel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		pane.setBackground(new Background(backFill));
		
		//menu for buttons down the left side
		VBox leftside=new VBox();
		leftside.getChildren().addAll(numBugLabel, numBugs, numPlantLabel, numPlants, startBtn,resetBtn);
		leftside.setPadding(new Insets(10));
		leftside.setSpacing(10);
		
		pane.setTop(title);
		pane.setLeft(leftside);

		//display everything
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
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
		//convert string to individual chars, if any char is not a number
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
