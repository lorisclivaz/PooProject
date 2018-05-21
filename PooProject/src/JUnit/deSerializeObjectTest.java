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
		//On sérialise un objet de type contact vide
		cp.serializeObject(c);
		
		//Contrôle pour voir si on peut bien déserialiser
		String path = "serialisation/contact-1.ser";
		
		//Test
		Contact cnew;
		cnew = cp.deSerializeObject(path);
		
		//Check final
		if(!cnew.getNom().equals(v))
			fail("La déserialisation a un problème !");
	}

}
