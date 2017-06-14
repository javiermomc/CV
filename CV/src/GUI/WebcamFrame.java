package GUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
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
		startWindow();
		
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
		//Here is to select the colors from part of a frame
		/*webcamPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent mouse){
				startPoint = mouse.getPoint();
			}
			public void mouseDragged(MouseEvent mouse){
				endPoint = mouse.getPoint();
			}
		});
		webcamPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				area = new Rectangle(startPoint, new Dimension(endPoint.x-startPoint.x, endPoint.y-startPoint.y));
//				System.out.println(area);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public int getCloseOperation(){
		return window.EXIT_ON_CLOSE;
	}
	public BufferedImage getImage(){
		return webcam.getImage();
	}
	public Mat getImageMat(){
		Mat frame = new Mat();
		camera.read(frame);
		return frame;
	}

}
