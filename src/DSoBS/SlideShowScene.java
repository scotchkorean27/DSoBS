package DSoBS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class SlideShowScene{
	private ImageView imageView;
	private Scene scene;
	private int imageIndex;
	private String[] imagePaths;
	
	public SlideShowScene(String[] imagePaths) {
		imageIndex = 0;
		scene = createScene();
		this.imagePaths = imagePaths;
	}

	public Scene getScene() {
		return scene;
	}
	
	public Scene createScene() {
		imageView = new ImageView();

		BorderPane root = new BorderPane();

		root.setCenter(imageView);

		return (new Scene(root, 1280, 720));
	}

	public void changeImage(String filepath) {
		try {
			InputStream inputStream = new FileInputStream(filepath);
			Image image = new Image(inputStream);
			imageView.setImage(image);
			imageView.setFitWidth(1024);
			imageView.setFitHeight(576);
			inputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void advanceImage() {
		changeImage(imagePaths[imageIndex]);
		imageIndex++;
	}
}
