/*
* Author : Vivian Bridy & Loris Clivaz
* Date creation : 14 mai 2018
*/

package GalerieApp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;


/**
 * Classe qui va g�rer la picture de la galerie
 * 
 * @author Loris
 *
 */

public class Picture implements Serializable{

	private String path ;
	private BufferedImage picture ;

	/**
	 * Contructeur  qui va r�cup�rer le path
	 * 
	 * @param path : r�cup�ration du path
	 * @param picture: l'image dans la galerie
	 * 
	 * @author Loris
	 */
	
	
	
	public Picture (String path, BufferedImage picture)
	{
		this.path = path ;
		this.picture = picture ;

	}
	

	/**
	 * 
	 * M�thode qui supprime, obliger de flush l'image sinon elle disparait seulement � la fermeture du programme
	 * 
	 * @author Loris
	 */
	
	public void delete()
	{
		picture.flush();
		File file = new File(path);
		file.delete();

	}

	/**
	 * 
	 * M�thode qui copie l'image dans le folder imagesgallery
	 * 
	 * @param file le fichier copi�
	 * @return newName renommer l'image
	 * @author Loris
	 */
	public static String copy(File file)
	{
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String newName = file.getName()+" "+timestamp.getTime();
		
		Path path2 = Paths.get("imagesgallery/"+newName);
		try {
			Files.copy(file.toPath(),path2,StandardCopyOption.REPLACE_EXISTING );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newName;
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


}






