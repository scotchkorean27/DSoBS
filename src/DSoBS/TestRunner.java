package DSoBS;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestRunner extends Application {
	private SlideShowScene scene = new SlideShowScene();
	private String[] selectedImages;
	private int size = 5;
	private Thread thread;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FileManager fm = new FileManager();
		selectedImages = fm.getRandomImagesPaths(size);
		primaryStage.setTitle("DS");

		primaryStage.setScene(scene.getScene());
		primaryStage.setWidth(768);
		primaryStage.setHeight(450);
		
		thread = new Thread(new HelloRunnable(scene,selectedImages));
		thread.start();
		primaryStage.setOnCloseRequest(event -> {
			try {
				if(thread != null)
					thread.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		primaryStage.show();
	}

	public class HelloRunnable implements Runnable {

		private SlideShowScene scene;
		private String[] selectedImages;

		public HelloRunnable(SlideShowScene scene, String[] selectedImages) {
			this.scene = scene;
			this.selectedImages = selectedImages;
		}

		public void run() {
			for (int i = 0; i < selectedImages.length;i++) {
				scene.changeImage(selectedImages[i]);
				
				try {
					//Pause for 4 seconds
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}



}
