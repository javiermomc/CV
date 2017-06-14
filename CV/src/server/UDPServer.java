package server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer{

	static byte[] dataInBytes;
	static InetAddress address;
	static DatagramSocket server;
	final static int PORT = 38;
	String localData;
	
	public UDPServer() {
		
		try {
			server = new DatagramSocket();
			address = InetAddress.getByName("10.31.58.2");
		} catch (SocketException error) {
			error.printStackTrace();
		} catch (UnknownHostException error) {
			error.printStackTrace();
		}
		
	}
	
	public void sendData(String data){
		dataInBytes = new byte[1024];
		dataInBytes = data.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(dataInBytes, dataInBytes.length, address, PORT);
		try {
			server.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
	public void close(){
		server.close();
	}

}
