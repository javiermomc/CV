package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer implements Runnable{
	final int PORT=3158;
	DatagramSocket server;
	
	public UDPServer(){
		try {
			server = new DatagramSocket(PORT);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try{
			DatagramPacket packet = null;
			for(int i=0; i<2; i++){
				String message = "Hello: "+i;
				packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getLocalHost(), PORT);
			}
			if(packet!=null)
			server.send(packet);
		}catch(Exception e){
			
		}
	}
	

}
