import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationUI extends Application {

	//Fields
	int width=400, height=300;
	World startWorld=new World(this.width, this.height);
	Group group=new Group();
	
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		startWorld.populate();
		
		//put bugs into group individually
		int bugListSize=startWorld.getBugListSize();
		for(int i=0;i<bugListSize;i++) {
			group.getChildren().add(startWorld.getBugList().get(i).getCircle());
		}
		
		//group.getChildren().add(startWorld);
		final Scene scene=new Scene(group,width,height);
		
		KeyFrame frame =new KeyFrame(Duration.millis(16),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				updateBugWorld();
				
			}
			
		});
		
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();
		
		
		primaryStage.setTitle("Bug World Animation");
		primaryStage.setScene(scene);
		primaryStage.show();

	} //end start
	
	public void updateBugWorld() {
		startWorld.update();
		startWorld.draw();
		
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public static void main(String[] args) {
		launch();

	}

}
