package DSoBS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameRunner extends Application {
	// How long the wikipedia article should be rendered for.
	private static int ARTICLE_RENDER_LENGTH_SECONDS = 10;
	// How long the title slide should be rendered for.
	private static int TITLE_SLIDE_LENGTH_SECONDS = 10;

	private Scene scene;

	@Override
	public void start(Stage stage) {
		// create the scene
		stage.setTitle("Web View");
		ArticleRenderer articleRenderer = new ArticleRenderer();
		scene = new Scene(articleRenderer, 1280, 720, Color.web("#666970"));
		stage.setScene(scene);
		stage.show();

		// load the images
		String[] imageFiles = FileManager.getRandomImagesPaths(10);

		SlideShowScene slideShow = new SlideShowScene(imageFiles);
		slideShow.advanceImage();

		int currentTimeSeconds = 0;

		Timeline timeline = new Timeline();
		// Our title screen
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTimeSeconds += ARTICLE_RENDER_LENGTH_SECONDS),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String[] tokenizedUrl = articleRenderer.webEngine.getLocation().split("/");
						String articleTitle = tokenizedUrl[tokenizedUrl.length - 1].replaceAll("_", " ");
						Text titleText = new Text(articleTitle);
						titleText.setFont(Font.font("Times New Roman", 72));
						titleText.setTextAlignment(TextAlignment.CENTER);

						StackPane root = new StackPane();
						root.getChildren().add(titleText);

						Scene scene = new Scene(root, 1280, 720, Color.WHITE);
						stage.setTitle(articleTitle);
						stage.setScene(scene);
						stage.show();
					}
				}));

		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(currentTimeSeconds += TITLE_SLIDE_LENGTH_SECONDS),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						stage.setScene(slideShow.getScene());
					}
				}));

		// Build a timeline that defines when we advance to the next image
		for (int i = 1; i < imageFiles.length - 1; i++) {
			timeline.getKeyFrames()
					.add(new KeyFrame(Duration.seconds(currentTimeSeconds += 10), new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							slideShow.advanceImage();
						}
					}));
		}
		timeline.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
