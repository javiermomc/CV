package GUI;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageFrame extends JFrame{
	
	ImageIcon imagePanel;
	JLabel comp;
	
	public ImageFrame(){
		try {
	        this.pack();
	        this.setSize(640, 480);
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public ImageFrame(String title){
		this.setTitle(title);
		try {
	        this.pack();
	        this.setSize(640, 480);
	        this.setLocationRelativeTo(null);
	        this.setVisible(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void update(Mat mat){
		imagePanel = mat2ImageIcon(mat);
		this.getContentPane().removeAll();
		JLabel comp = new JLabel(imagePanel);
		this.add(comp);
		this.revalidate();
		this.repaint();
	}
	public void update(BufferedImage bufferedImage){
		imagePanel = new ImageIcon(bufferedImage);
		this.getContentPane().removeAll();
		comp = new JLabel(imagePanel);
		this.add(comp);
		this.revalidate();
		this.repaint();
	}
	public ImageIcon mat2ImageIcon(Mat img) {
	    Imgproc.resize(img, img, new Size(640, 480));
	    MatOfByte matOfByte = new MatOfByte();
	    Imgcodecs.imencode(".jpg", img, matOfByte);
	    byte[] byteArray = matOfByte.toArray();
	    BufferedImage bufImage = null;
	    InputStream in = new ByteArrayInputStream(byteArray);
        try {
			bufImage = ImageIO.read(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        return new ImageIcon(bufImage);
	}

}
