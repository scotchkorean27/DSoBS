package DSoBS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
 
 
public class GameRunner extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new ArticleRenderer(), 1024, 960, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
        
        String[] imageFiles = FileManager.getRandomImagesPaths(10);
        for(String s : imageFiles) {
        	System.out.println(s);
        }
        
        SlideShowScene ss = new SlideShowScene(imageFiles);
        ss.advanceImage();
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(10),
        	    new EventHandler<ActionEvent>() {

        	        @Override
        	        public void handle(ActionEvent event) {
        	        	stage.setScene(ss.getScene()); 
        	        }
        	    }));
        
        for (int i = 1; i < imageFiles.length - 1; i++) {
        	timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(10 * (i + 1)),
        	    new EventHandler<ActionEvent>() {

        	        @Override
        	        public void handle(ActionEvent event) {
        	        	ss.advanceImage();
        	        }
        	    }));
        }
        timeline.play();        
    }
 
    public static void main(String[] args){
        launch(args);
    }

}

