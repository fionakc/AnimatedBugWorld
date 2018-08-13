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
	
	//Group group=new Group();
	BorderPane pane = new BorderPane();
	final Scene scene=new Scene(pane,width,height);
	//World startWorld=new World();
	World startWorld=new World(this.width, this.height);
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		//panel.setPrefSize(600, 500);
		pane.setCenter(panel);
		//System.out.println(panel.getWidth()); //still don't have a size <<seems to work okay now
		//startWorld.updateWorldSize(panel.getWidth(), panel.getHeight());
		
		
		//group.getChildren().add(startWorld);
		//scene=new Scene(group,width,height);
		
		//create a start button for bugworld
		Button startBtn=new Button();
		startBtn.setText("Start");
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				startWorld.populate();
				
				//put plants into group individually
				int plantListSize=startWorld.getPlantListSize();
				for(int i=0;i<plantListSize;i++) {
					panel.getChildren().add(startWorld.getPlantList().get(i).getCircle());
					//group.getChildren().add(startWorld.getPlantList().get(i).getCircle());
				}
				
				//put bugs into group individually
				int bugListSize=startWorld.getBugListSize();
				for(int i=0;i<bugListSize;i++) {
					panel.getChildren().add(startWorld.getBugList().get(i).getCircle());
					//group.getChildren().add(startWorld.getBugList().get(i).getCircle());
				}		
			}
			
		}); //end startBtn set action
		
		Button resetBtn=new Button();
		resetBtn.setText("Reset");
		
		resetBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) { //what we want to happen when button pressed
				//startWorld.restart();
				//panel.getChildren().removeAll(c);
				//startWorld.populate();
				
				//put plants into group individually
				
				for(int i=0;i<startWorld.getPlantList().size();i++) {
					panel.getChildren().remove(startWorld.getPlantList().get(i).getCircle());
					//group.getChildren().add(startWorld.getPlantList().get(i).getCircle());
				}
				
				//put bugs into group individually
				//int bugListSize=startWorld.getBugListSize();
				for(int i=0; i<startWorld.getBugList().size();i++) {
					panel.getChildren().remove(startWorld.getBugList().get(i).getCircle());
					//group.getChildren().add(startWorld.getBugList().get(i).getCircle());
				}	
				
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
		//BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(15, 20, 10, 10));
		//BorderPane.setAlignment(panel, Pos.TOP_LEFT);
		//BorderPane.setAlignment(group, Pos.TOP_LEFT);
		
		Label title=new Label("Welcome to Bug World");
		title.setAlignment(Pos.CENTER);
		//title.setBackground(Color.WHITE);
		BackgroundFill backFill=new BackgroundFill(Color.WHITE,null, null);
		title.setBackground(new Background(backFill));
		title.setFont(Font.font ("Verdana", 20));
		title.setPadding(new Insets(15));
		
		
		panel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		pane.setBackground(new Background(backFill));
		
		//menu button down the side
		VBox leftside=new VBox();
		leftside.getChildren().addAll(startBtn,resetBtn);
		leftside.setPadding(new Insets(10));
		leftside.setSpacing(10);
		
		pane.setTop(title);
		pane.setLeft(leftside);
		//pane.setCenter(panel);
		//pane.setCenter(group);
		
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
	public void updateBugWorld() {
		
		startWorld.updateWorldSize(panel.getWidth(), panel.getHeight());
		//startWorld.updateWorldSize(scene.getWidth(), scene.getHeight());
		startWorld.update();
		
		
		//remove dead plants and bugs from scene		
		for(int i=0;i<startWorld.getDeadPlants().size();i++) {
			panel.getChildren().remove(startWorld.getDeadPlants().get(i).getCircle());
			//group.getChildren().remove(startWorld.getDeadPlants().get(i).getCircle());
		}
		for(int i=0;i<startWorld.getDeadBugs().size();i++) {
			panel.getChildren().remove(startWorld.getDeadBugs().get(i).getCircle());
			//group.getChildren().remove(startWorld.getDeadBugs().get(i).getCircle());
		}
		
		
	}
	

	public static void main(String[] args) {
		launch();

	}

}
