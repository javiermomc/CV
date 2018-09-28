package main;

import cv.CV;
import server.UDPServer;

public class Start {

	public static CV runnable;
	public static Thread cv;
	public static UDPServer server;
	
	public static void main(String[] args) {
		start();
	}
	
	public static void start(){
		System.out.println("Created by Javier Mondragon Martin del Campo");
		
		runnable = new CV();
		cv = new Thread(runnable);
		cv.start();
		server = new UDPServer();
		
		while(cv.isAlive()){
			server.sendData("X: "+runnable.getX()+" Y: "+runnable.getY()+" Time: "+runnable.getTime());
			System.out.println("x: "+runnable.getX()+" y: "+runnable.getY());
//			System.out.println(runnable.getIfReady());
//			System.out.println("Min h: "+runnable.getMinHSV()[0]+" s: "+runnable.getMinHSV()[1]+" v: "+runnable.getMinHSV()[2]+"\nMax h: "+runnable.getMaxHSV()[0]+" s: "+runnable.getMaxHSV()[1]+" v: "+runnable.getMaxHSV()[2]);
		}
		
		server.close();
	}	
}