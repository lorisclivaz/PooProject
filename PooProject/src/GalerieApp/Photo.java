/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 2 mai 2018
* 
*/
/**
 * 
 */
package GalerieApp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Images.ImagePhoto;

/**
 * @author Loris_Clivaz
 *
 */
public class Photo implements Serializable
{

	private String url;
	

	private String nom;
	
	
	public Photo(String url, String nom)
	{
		this.nom = nom;
		this.url = url;
	}
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
