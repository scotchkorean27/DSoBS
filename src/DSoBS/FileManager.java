package DSoBS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

public class FileManager {

	private static final String FOLDER_PATH = "Images/";

	/**
	 * Find the name of all images in the Images folder
	 * @return
	 */
	public String[] findAllImages() {
		File file = new File(FOLDER_PATH);
		return file.list();
	}

	public BufferedImage[] getRandomImages(int size) {
		String[] filenames = findAllImages();
		if(size < filenames.length) {
			return null;
		}

		BufferedImage[] images = new BufferedImage[size];
		HashMap<Integer,Integer> setted = new HashMap<Integer, Integer>();

		try {
			int i = 0;
			Random random = new Random();
			while(i < images.length) {
				int index;

				do {
					index = random.nextInt(filenames.length);
				} while (setted.containsKey(Integer.valueOf(index)));


				images[i++] = ImageIO.read(new File(FOLDER_PATH + filenames[index]));

				setted.put(Integer.valueOf(index), 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return images;
	}

}
