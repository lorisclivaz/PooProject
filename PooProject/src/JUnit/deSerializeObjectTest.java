/*
* Author : Vivian Bridy
* Date creation : 21 mai 2018
*/
package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ContactApp.ContactPanel;
import ContactApp.ContactPanel.Contact;

class deSerializeObjectTest {

	@Test
	void testDeSerializeObject() {
		String v = "Bridy";
		ContactPanel cp = new ContactPanel();
		Contact c = cp.new Contact(v, null, null, null, null, null, null);
		//On s�rialise un objet de type contact vide
		cp.serializeObject(c);
		
		//Contr�le pour voir si on peut bien d�serialiser
		String path = "serialisation/contact-1.ser";
		
		//Test
		Contact cnew;
		cnew = cp.deSerializeObject(path);
		
		//Check final
		if(!cnew.getNom().equals(v))
			fail("La d�serialisation a un probl�me !");
	}

}
