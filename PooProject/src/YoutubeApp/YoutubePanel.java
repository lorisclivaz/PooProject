/*
* Author : Vivian Bridy
* Date creation : 21 mai 2018
*/
package YoutubeApp;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/**
 * The sample demonstrates how to create Browser instance, embed it, display and load
 * specified URL.
 */
public class YoutubePanel extends JPanel {
	
	static JFrame frame = new JFrame("JxBrowser - Hello World");
	static Browser browser = new Browser();
	
    public static void main(String[] args) {
        BrowserView view = new BrowserView(browser);
 
//        final JTextField addressBar = new JTextField(
//                "http://www.youtube.com");
//        addressBar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                browser.loadURL(addressBar.getText());
//            }
//        });
 
        JPanel addressPane = new JPanel(new BorderLayout());
        addressPane.add(new JLabel(" URL: "), BorderLayout.WEST);
//        addressPane.add(addressBar, BorderLayout.CENTER);
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(addressPane, BorderLayout.NORTH);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        
    }

	public JFrame getFrame() {
		return frame;
	}

	public void setOn() {
		frame.setVisible(true);
		browser.loadURL("http://www.youtube.com");
	}
    
    
  
}
