package cv;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import GUI.ImageFrame;
import GUI.Tools;
import GUI.WebcamFrame;

public class CV implements Runnable{
	
	WebcamFrame webcam;
	ImageFrame filtredImgFrame, hsvFrame, frame;
	Tools tools;
	Mat object;
	double wAp, hAp;
	SimpleDateFormat time;
	Double[] hsvMin = {95.0,0.0,80.0}, hsvMax = {110.0, 40.0, 255.0};
	double[] coordinates = {0,0};
	
	public CV(){
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		wAp = 60.0/640.0;
		hAp = 40.0/480.0;
//		System.out.println(wAp);
//		System.out.println(hAp);
		webcam = new WebcamFrame();
		tools = new Tools(webcam.getCloseOperation());
		filtredImgFrame = new ImageFrame("Filtered");
		hsvFrame = new ImageFrame("Contours");
//		frame = new ImageFrame("Cropped");
		object = new Mat();
	}
	@Override
	public void run() {
		try{
			while(webcam.getCloseOperation()==3){
//				System.out.println(System.currentTimeMillis());
				object=new Mat();
				Mat img = bufferedImage2Mat(webcam.getImage());
				time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Mat hsv = img.clone();
				Imgproc.cvtColor(hsv, hsv, Imgproc.COLOR_BGR2HSV);
				hsvMin = tools.getMinValues();
				hsvMax = tools.getMaxValues();				
				
				Scalar min = new Scalar(hsvMin[0],hsvMin[1],hsvMin[2]), max = new Scalar(hsvMax[0],hsvMax[1],hsvMax[2]);
				
				Core.inRange(hsv, min, max, hsv); //Transforms the HSV image to get only the colors in range between the min and max values.
				
				filtredImgFrame.update(hsv); // Displays frame in Filtered window after filtered colors.
				List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
				Imgproc.findContours(hsv, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
				Imgproc.drawContours(img, contours, -1, new Scalar(0,255,0), 3);
				if(contours.size()>0)
				for(int i=0; i<contours.size()&&contours.size()>0; i++){
					if(i==0)object=contours.get(i);
					if(Imgproc.contourArea(contours.get(i))>Imgproc.contourArea(object))object=contours.get(i);
				}
				if(object!=null&&object.width()>0&&object.height()>0){
					coordinates = getCentroid(object);
					Imgproc.circle(img, new Point(coordinates[0], coordinates[1]), 7, new Scalar(180,255,255), -1);
				}
				else{ coordinates[0] = 0; coordinates[1] = 0;}
				hsvFrame.update(img);
			}
			
		}catch(Exception e){
			System.out.println("Error while starting Thread... \nError:"+e);
		}
	}
	
	public String getX(){
		return (double)Math.round((coordinates[0]-319.5)*wAp*1000)/1000+" Time: "+time.toString();
	}
	public String getY(){
		return (double)Math.round((coordinates[1]-239.5)*hAp*1000)/1000+" Time: "+time.toString();
	}
	public void setMinHSV(double h, double s, double v){hsvMin = new Double[]{h,s,v};}
	public void setMaxHSV(double h, double s, double v){hsvMax = new Double[]{h,s,v};}
	public Double[] getMinHSV(){return hsvMin;}
	public Double[] getMaxHSV(){return hsvMax;}
	
	public Mat bufferedImage2Mat(BufferedImage bi) {
		  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
		  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		  mat.put(0, 0, data);
		  return mat;
	}
	
	public double[] getCentroid(Mat img){
		Moments moment = Imgproc.moments(img);
		double moment10 = moment.m10, moment01 = moment.m01, moment00 = moment.m00;
		double x = (int)moment10/moment00, y = (int)moment01/moment00;
		if(moment10==0&&moment00==0)
		System.out.println("Moments are 0!!!");
		double[] coord = {x, y};
		return coord;
	}

	
	
}


//Here starts getting colors with a cropped image *needs class Tools()
/*
 * if(webcam.area!=null&&!tools.isSelected()){
	if((webcam.area.width>0||webcam.area.width<0)&&(webcam.area.height>0||webcam.area.height<0)){
		int x=0,y=0,width=1,height=1;
		Rectangle area = webcam.area;
		if(area.width<0){
			x=area.x+area.width;
			width = Math.abs(area.width);
		}else{
			x=area.x;
			width=area.width;
		}
		if(area.height<0){
			y=area.y+area.height;
			height = Math.abs(area.height);
		}else{
			y=area.y;
			height=area.height;
		}
		Rect desiredArea = new Rect(x,y,width,height);
		Mat colorMat = new Mat(img, desiredArea);
		Imgproc.cvtColor(colorMat, colorMat, CvType.CV_8U);
		double m,v;
		try{
		MinMaxLocResult colorResult = Core.minMaxLoc(colorMat);
		System.out.println(colorResult.maxVal);
		}catch(Exception e){System.out.println(e);
		
		}
		
		frame.update(colorMat);
	}
}else if(!tools.isSelected()){
				hsvMin = tools.getMinValues();
				hsvMax = tools.getMaxValues();
	}
*/
