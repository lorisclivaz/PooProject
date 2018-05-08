package GalerieApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
*  Package name : objects
*  Author: Bétrisey Maxime
*  Date creation: 19 mai 2016
*  Description : 
*/
/**
 * @author Loris
 * @version 1.0
 */
public class Picture {
	private String path ;
	private BufferedImage picture ;
	
	public Picture (String path, BufferedImage picture){
		this.path = path ;
		this.picture = picture ;
		
	}
	//Bouton supprimer, obliger de flush la buffer d'image, sinon elle disparait seulement à la fermeture du programme
	public void delete(){
		picture.flush();
		File file = new File(path);
		file.delete();
		
	}
	
	public static void copy(File file){
		Path path2 = Paths.get("imagesgallery/"+file.getName());
		try {
			Files.copy(file.toPath(),path2,StandardCopyOption.REPLACE_EXISTING );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BufferedImage getPicture() {
		return picture;
	}

	public void setImg(BufferedImage picture) {
		this.picture = picture;
	}
	
	public void copyToBg(){
		picture.flush();
		try {
			Files.copy(Paths.get(path), Paths.get("res/bg.png"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
