package main;

import javax.swing.SwingUtilities;

import cv.CV;
import server.Server;
import server.UDPServer;

public class Start {

	public static CV runnable;
	public static Thread cv, serverThread;
	public static UDPServer server;
	public static void main(String[] args) {
		start();
	}
	public static void start(){
		System.out.println("Created by Javier Mondragon Martin del Campo");
		System.out.println("Created with OpenCV-3.1.0 and Sarxos Webcam");
		
		runnable = new CV();
		cv = new Thread(runnable);
		cv.start();
		server = new UDPServer();
		serverThread = new Thread(server);
		serverThread.start();
		
		while(cv.isAlive()){
//			server.setData("X: "+runnable.getX()+" Y: "+runnable.getY());
			System.out.println("x: "+runnable.getX()+" y: "+runnable.getY());
			System.out.println("Min h: "+runnable.getMinHSV()[0]+" s: "+runnable.getMinHSV()[1]+" v: "+runnable.getMinHSV()[2]+"\nMax h: "+runnable.getMaxHSV()[0]+" s: "+runnable.getMaxHSV()[1]+" v: "+runnable.getMaxHSV()[2]);
		}
//		server.close();
	}	
}