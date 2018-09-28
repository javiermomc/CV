package GUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamFrame {
	Webcam webcam;
	JFrame window;
	JPanel webcamPanel;
	JLabel selectionLbl;
	Point startPoint, endPoint;
	public Rectangle area;
	VideoCapture camera;
	KeyListener listener;
	File imageFile;
	BufferedImage image;
	
	public WebcamFrame(){
		if(Webcam.getWebcams().size()>1){
		System.out.println("Select a webcam:");
		System.out.println(Webcam.getWebcams());
		Scanner keyboard = new Scanner(System.in);
		int webcamPort = keyboard.nextInt();
		while(webcamPort<0||webcamPort>Webcam.getWebcams().size()-1){
			System.out.println("Please type the port of the webcam:");
			System.out.println(Webcam.getWebcams());
			webcamPort = keyboard.nextInt();
		}
		webcam = Webcam.getWebcams().get(webcamPort);
		}else webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		System.out.println(webcam.getViewSize());
		startWebcam();
		imageFile = new File("Test.jpg");
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startWindow();
		listener = new myKey();
		window.addKeyListener(listener);
		
	}
	public void startWebcam(){
		try{
			webcamPanel = new WebcamPanel(webcam);
			}catch(WebcamException e){
				System.out.println(e);
			}
		
	}
	public Dimension getImageDimension(){
		return webcam.getViewSize();
	}
	public void startWindow(){
		window = new JFrame("Webcam");
		window.add(webcamPanel);
//		window.add(new JLabel(new ImageIcon(image)));
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public int getCloseOperation(){
		return window.EXIT_ON_CLOSE;
	}
	public BufferedImage getImage(){
		return webcam.getImage();
//		return image;
		
	}
	
	public class myKey implements KeyListener {

		int pictures=0;
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e);
			
			File image = new File("Picture_"+pictures+"_"+"Webcam"+".jpg");
			pictures++;
			try {
				image.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(e.getKeyChar()=='p')
				try {
					ImageIO.write(toBufferedImage(webcam.getImage()), "jpg", image);
					System.out.println("Exito! "+image.getName()+" ha sido guardada...");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		public BufferedImage toBufferedImage(Image img)
		{
		    if (img instanceof BufferedImage)
		    {
		        return (BufferedImage) img;
		    }

		    // Create a buffered image with transparency
		    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();

		    // Return the buffered image
		    return bimage;
		}
		
	}
}
