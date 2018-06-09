/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 14 mai 2018
 */

package Batterie;

import java.util.ArrayList;
import java.util.List;



import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;


/***
 * Classe qui va gérer la batterie du smartphone
 * 
 * @author Loris
 *
 */
public interface Kernel32 extends StdCallLibrary {

	public Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("Kernel32", Kernel32.class);

	
	public class SYSTEM_POWER_STATUS extends Structure {
		public byte ACLineStatus;
		public byte BatteryFlag;
		public byte BatteryLifePercent;
		public byte Reserved1;
		public int BatteryLifeTime;
		public int BatteryFullLifeTime;

		@Override
		protected List<String> getFieldOrder() {
			ArrayList<String> fields = new ArrayList<String>();
			fields.add("ACLineStatus");
			fields.add("BatteryFlag");
			fields.add("BatteryLifePercent");
			fields.add("Reserved1");
			fields.add("BatteryLifeTime");
			fields.add("BatteryFullLifeTime");
			return fields;
		}



		/**
		 * Méthode permettant de regarder le status de la batterie
		 * 
		 * @return offline
		 * @return online
		 * @author Loris
		 */
		public String getACLineStatusString() {
			switch (ACLineStatus) {
			case (0): return "Offline";
			case (1): return "Online";
			default: return "Unknown";
			}
		}

		/**
		 * Méthode permettant de récupérer les différentes images de la batterie
		 * 
		 * @return null
		 * @author Loris
		 */
		public String getBatterystate(){
			if(BatteryLifePercent<((byte)25)) return "images/icones/batterie1.jpg";
			if(BatteryLifePercent<((byte)50)) return "images/icones/batterie2.jpg";
			if(BatteryLifePercent<((byte)75)) return "images/icones/batterie3.jpg";
			if(BatteryLifePercent<=((byte)100)) return "images/icones/batterie4.jpg";
			return null ;	

		}
		
		/**
		 * Méthode permettant de récupérer le pourcentage de la batterie pour le verroupanel
		 * 
		 * @return null
		 * @author Loris
		 */
		public String getBatterystate2(){
			if(BatteryLifePercent<((byte)25)) return "25%";
			if(BatteryLifePercent<((byte)50)) return "50%";
			if(BatteryLifePercent<((byte)75)) return "75%";
			if(BatteryLifePercent<=((byte)100)) return "100%";
			return null ;	

		}



		/**
		 * Méthode permettant de récupérer le pourcentage de la batterie pour le verroupanel
		 * 
		 * @return BatteryLifePercent
		 * @author Loris
		 */
		public String getBatteryLifePercent() {
			return (BatteryLifePercent == (byte) 255) ? "Unknown" : BatteryLifePercent + "%";
		}

		
		/**
		 * Méthode de la duree de la batterie
		 * 
		 * @return BatteryLifeTime
		 * @author Loris
		 */
		public String getBatteryLifeTime() {
			return (BatteryLifeTime == -1) ? "Unknown" : BatteryLifeTime + " seconds";
		}

		/**
		 * Méthode pour la durée total de la batterie
		 * 
		 * @return BatteryLifePercent
		 * @author Loris
		 */
		public String getBatteryFullLifeTime() {
			return (BatteryFullLifeTime == -1) ? "Unknown" : BatteryFullLifeTime + " seconds";
		}

	}

	
	
	/**
	 * Méthode permettant de récupérer le pourcentage de la batterie pour le verroupanel
	 * 
	 * @param result etat de la batterie
	 * @author Loris
	 */
	public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
}