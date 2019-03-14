/*
 * Name: Esther Ho
 * CMSC 405 (Computer Graphics)
 * Assignment: Project 1
 * Due: November 4, 2018
 * File: Java2D_Proj1Panel.java
 * 
 * Description:
 * 
 *  BASED OFF OF Sample CMSC405P1Template.java file provided for all students.
 * 
 *  - Proj1Panel.java will display a GUI that shows the 3 images as they are being transformed.  
 */

package Proj1_EstherHo;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Proj1Panel extends JFrame {

	// constructor
	// sets up a JFrame to show the multiple transformations
    public Proj1Panel() {
    	
    	ImageTransformations animation = new ImageTransformations(); 
    	add(animation);
    
    	// set up GUI 
        setPreferredSize(new Dimension(800, 600));
        setTitle("Java 2D Animation - Project 1");
        pack();  // Set window size based on the preferred sizes of its contents.
        setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( // Center window on screen.
                (screen.width - getWidth()) / 2,
                (screen.height - getHeight()) / 2);
        
    } // end constructor

	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	Proj1Panel proj1GUI = new Proj1Panel();
    	proj1GUI.setVisible(true);
    }

} // end Proj1Panel.java
