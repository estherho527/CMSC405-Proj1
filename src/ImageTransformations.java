/*
 * Name: Esther Ho
 * CMSC 405 (Computer Graphics)
 * Assignment: Project 1
 * Due: November 4, 2018
 * File: ImageTransformation.java
 * 
 * Description:
 * 
 *  BASED OFF OF Sample  file provided for all students.
 * 
 *  - An ImageTransformations object contains the series of transformations to be
 *   applied to the images in ImageCollection.  
 * 
 */
package Proj1_EstherHo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ImageTransformations extends JPanel {
	
	 // A counter that increases by one in each frame.
    private int frameNumber;
    // The time, in milliseconds, since the animation started.
    private long elapsedTimeMillis;
    // This is the measure of a pixel in the coordinate system
    // set up by calling the applyLimits method.  It can be used
    // for setting line widths, for example.
    private float pixelSize;

    static int translateX = 0;
    static int translateY = 0;
    static double rotation = 0.0;
    static double scaleX = 1.0;
    static double scaleY = 1.0;
    
    ImageCollection myImages = new ImageCollection();
    BufferedImage eImage = myImages.getImage(ImageCollection.letterE);
    BufferedImage hImage = myImages.getImage(ImageCollection.letterH);
    BufferedImage heartImage = myImages.getImage(ImageCollection.shapeHeart);


    /* 
     * Constructor, that starts timer
     */
	public ImageTransformations() {
		
		 Timer animationTimer;  // A Timer that will emit events to drive the animation.
	        final long startTime = System.currentTimeMillis();
	        // Taken from AnimationStarter
	        // Modified to change timing and allow for recycling
	        animationTimer = new Timer(1600, new ActionListener() {
	        	
	        	// 7 frames for 1 original frame, and 7 transformations
	            public void actionPerformed(ActionEvent arg0) {
	                if (frameNumber > 7) {
	                    frameNumber = 0;
	                } else {
	                    frameNumber++;
	                }
	                elapsedTimeMillis = System.currentTimeMillis() - startTime;
	                repaint();
	            }
	        });
	        
	        animationTimer.start();  // Start the animation running.
	        
	        // show each image's arrays
	        myImages.showImageArray(ImageCollection.letterE);
	        myImages.showImageArray(ImageCollection.letterH);
	        myImages.showImageArray(ImageCollection.shapeHeart);
	       
	}

	
	 
    // This is where all of the action takes place
    // Code taken from AnimationStarter.java but modified to add the specific Images
    // Also added looping structure for Different transformations
    protected void paintComponent(Graphics g) {

        /* First, create a Graphics2D drawing context for drawing on the panel.
         * (g.create() makes a copy of g, which will draw to the same place as g,
         * but changes to the returned copy will not affect the original.)
         */
        Graphics2D g2 = (Graphics2D) g.create();

        /* Turn on antialiasing in this graphics context, for better drawing.
         */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Fill in the entire drawing area with white.
         */
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight()); // From the old graphics API!

        /* Here, I set up a new coordinate system on the drawing area, by calling
         * the applyLimits() method that is defined below.  Without this call, I
         * would be using regular pixel coordinates.  This function sets the value
         * of the global variable pixelSize, which I need for stroke widths in the
         * transformed coordinate system.
         */
        // Controls your zoom and area you are looking at
        applyWindowToViewportTransformation(g2, -75, 75, -75, 75, true);

        AffineTransform savedTransform = g2.getTransform();
        //System.out.println("Frame is " + frameNumber);
        
        
        switch (frameNumber) {
            case 1: // First frame is unmodified/ original state.
                 translateX = 0;
                 translateY = 0;
                 scaleX = 1.0;
                 scaleY = 1.0;
                 rotation = 0;
                break;
            case 2: // 1st: translate -5 in X direction
                translateX += -5;
                break;
            case 3: // 2nd: translate +7 in Y direction
                translateY += 7;
                break;
            case 4: // 3rd: rotate 45 degrees counter-clockwise
                rotation += 45 * Math.PI / 180.0;
                break;
            case 5: // 4th: rotate 90 degrees clockwise
                rotation += -90 * Math.PI / 180.0;
                break;
            case 6: // 5th: scale 2 times the X component
            	scaleX = 2.0;
            	break;
            case 7: // 6th: scale 0.5 times the Y component
            	scaleY = 0.5;
            	break;
            default: 
                break;
        } // End switch
        
        // Image: Letter E
        g2.translate(translateX, translateY); // Move image.
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(eImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Image: Letter H
         g2.translate(translateX, translateY); // Move image.
        // To offset translate again
        // This allows you to place your images across your graphic
        g2.translate(-30,-30);
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(hImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Image: Heart
        g2.translate(translateX, translateY); // Move image.
        // To offset translate again
        // This allows you to place your images across your graphic
        g2.translate(30,30);
        g2.rotate(rotation); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(heartImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);

    }// end paintComponent
    

    // Method taken directly from AnimationStarter.java Code
    private void applyWindowToViewportTransformation(Graphics2D g2,
            double left, double right, double bottom, double top,
            boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double) height / width);
            double requestedAspect = Math.abs((bottom - top) / (right - left));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
                bottom += excess / 2;
                top -= excess / 2;
            } else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
                double excess = (right - left) * (requestedAspect / displayAspect - 1);
                right += excess / 2;
                left -= excess / 2;
            }
        }
        g2.scale(width / (right - left), height / (bottom - top));
        g2.translate(-left, -top);
        double pixelWidth = Math.abs((right - left) / width);
        double pixelHeight = Math.abs((bottom - top) / height);
        pixelSize = (float) Math.max(pixelWidth, pixelHeight);
        
    }// end applyWindowToViewportTransformation

}
