import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationUI extends Application {

	//Fields
	int width=600, height=500;
	World startWorld=new World(this.width, this.height);
	Group group=new Group();
	final Scene scene=new Scene(group,width,height);
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		startWorld.populate();
		
		//put plants into group individually
		int plantListSize=startWorld.getPlantListSize();
		for(int i=0;i<plantListSize;i++) {
			group.getChildren().add(startWorld.getPlantList().get(i).getCircle());
		}
		
		//put bugs into group individually
		int bugListSize=startWorld.getBugListSize();
		for(int i=0;i<bugListSize;i++) {
			group.getChildren().add(startWorld.getBugList().get(i).getCircle());
		}
		
		//group.getChildren().add(startWorld);
		//scene=new Scene(group,width,height);
		
		
		
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateBugWorld();
				
			}
			
		});
		
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();
		
		//make screen look nicer
		BorderPane pane = new BorderPane();
		Label title=new Label("Welcome to Bug World");
		
		
		
		
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
	public void updateBugWorld() {
		
		startWorld.updateWorldSize(scene.getWidth(), scene.getHeight());
		startWorld.update();
		//startWorld.draw();
		
		//remove dead plants and bugs from scene		
		for(int i=0;i<startWorld.getDeadPlants().size();i++) {
			group.getChildren().remove(startWorld.getDeadPlants().get(i).getCircle());
		}
		for(int i=0;i<startWorld.getDeadBugs().size();i++) {
			group.getChildren().remove(startWorld.getDeadBugs().get(i).getCircle());
		}
		
		
	}
	
//	public int getWidth() {
//		return this.width;
//	}
//	
//	public int getHeight() {
//		return this.height;
//	}

	public static void main(String[] args) {
		launch();

	}

}
