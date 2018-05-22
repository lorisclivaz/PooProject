/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 19 mai 2018
 * 
 */
/**
 * 
 */
package Camera;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import ContactApp.ContactPanel.SaveButton;
import Panels.PanelCamera;

/**
 * @author Loris_Clivaz
 *
 */
public class CameraPanel extends JPanel {
	NewCenter center1= new NewCenter();
	Timer timer;

	JButton stop = new JButton();

	PanelCamera panelcamera = new PanelCamera("Camera", "Camera");

	public CameraPanel(){

		setLayout(new BorderLayout());

		this.add(panelcamera, BorderLayout.NORTH);
		this.add(center1,BorderLayout.CENTER);
		this.add(stop, BorderLayout.SOUTH);
		this.setBackground(new Color(0,191,255));

		//setBounds(0, 0, 1280, 720);
		this.setVisible(true);

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		new MyThread().start();

	

		
		timer = new Timer(5, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//	ticktack();


			}
		});
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();

		
		
	}





	public void opencam()
	{
		if(this.isVisible()==true)
			center1.videoCap.openCam(0);
		else
			center1.repaint();
	}

	

	class MyThread extends Thread{
		@Override
		public void run() {
			for (;;){
				repaint();

				try { Thread.sleep(10);
			
				} catch (InterruptedException e) {    }
			}  
		} 
	}

}

class NewCenter extends JPanel {

	VideoCap videoCap = new VideoCap();
	public NewCenter() {

		setVisible(true);

	}


	public void paint(Graphics g){

		g.drawImage(videoCap.getOneFrame(),0, 0, this.getWidth(), this.getHeight(), this);
	}
}


class VideoCap {
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	VideoCapture cap;
	Mat2Image mat2Img = new Mat2Image();

	VideoCap(){
		cap = new VideoCapture();
		cap.open(0);
		

	} 
	void openCam(int i)
	{
		cap.open(i);

	}
	BufferedImage getOneFrame() {
		cap.read(mat2Img.mat);
		
		

		return mat2Img.getImage(mat2Img.mat);
	}
}


class Mat2Image {
	Mat mat = new Mat();
	BufferedImage img;
	byte[] dat;
	public Mat2Image() {
	}
	public Mat2Image(Mat mat) {
		getSpace(mat);
	}
	public void getSpace(Mat mat) {
		this.mat = mat;
		int w = mat.cols(), h = mat.rows();
		if (dat == null || dat.length != w * h * 3)
			dat = new byte[w * h * 3];
		if (img == null || img.getWidth() != w || img.getHeight() != h
				|| img.getType() != BufferedImage.TYPE_3BYTE_BGR)
			img = new BufferedImage(w, h, 
					BufferedImage.TYPE_3BYTE_BGR);
	}
	BufferedImage getImage(Mat mat){
		getSpace(mat);
		mat.get(0, 0, dat);
		img.getRaster().setDataElements(0, 0, 
				mat.cols(), mat.rows(), dat);
		return img;
	}
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}



}





