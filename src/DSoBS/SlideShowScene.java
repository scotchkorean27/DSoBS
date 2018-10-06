package DSoBS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SlideShowScene{
	private ImageView iv1;
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
		Group root = new Group();
		// load the image
		//        "Images/alien-226244_1280.jpg"


		// simple displays ImageView the image as is
		iv1 = new ImageView();

		//			// resizes the image to have width of 100 while preserving the ratio and using
		//			// higher quality filtering method; this ImageView is also cached to
		//			// improve performance
		//			ImageView iv2 = new ImageView();
		//			iv2.setImage(image);
		//			iv2.setFitWidth(100);
		//			iv2.setPreserveRatio(true);
		//			iv2.setSmooth(true);
		//			iv2.setCache(true);
		//
		//			// defines a viewport into the source image (achieving a "zoom" effect) and
		//			// displays it rotated
		//			ImageView iv3 = new ImageView();
		//			iv3.setImage(image);
		//			Rectangle2D viewportRect = new Rectangle2D(40, 35, 110, 110);
		//			iv3.setViewport(viewportRect);
		//			iv3.setRotate(90);
		HBox box = new HBox();
		box.getChildren().add(iv1);
		root.getChildren().add(box);


		return (new Scene(root, 1280, 720));
	}

	public void changeImage(String filepath) {
		try {
			InputStream inputStream = new FileInputStream(filepath);
			Image image = new Image(inputStream);
			iv1.setImage(image);
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
