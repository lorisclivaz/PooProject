/*
 * Picture
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
 */
package GalerieApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * @author Loris
 * @version 1.0
 */
public class Picture {

	private String path ;
	private BufferedImage picture ;

	//Constructeur qui va récupérer le path
	public Picture (String path, BufferedImage picture)
	{
		this.path = path ;
		this.picture = picture ;

	}

	//Bouton supprimer, obliger de flush image, sinon elle disparait seulement à la fermeture du programme
	public void delete()
	{
		picture.flush();
		File file = new File(path);
		file.delete();

	}

	//On va copier 
	public static void copy(File file)
	{
		Path path2 = Paths.get("imagesgallery/"+file.getName());
		try {
			Files.copy(file.toPath(),path2,StandardCopyOption.REPLACE_EXISTING );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Getters et setters
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


}






