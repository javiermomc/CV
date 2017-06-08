package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server implements Runnable{
	ServerSocket server;
	java.net.Socket connection;
	ObjectOutputStream output;
	Object data;
	public Server(){
		try{
			server = new ServerSocket(3158, 4);
		}catch(IOException error){
			System.out.println(error);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(connection==null){
			waitForConnection();
			if(connection!=null)
				setUpStreams();
		}
	}
	public void setData(String data){
		try {
			if(output!=null){
//				output.reset();
				output.writeObject(data);	
			}
//			else System.out.println("There is no connection");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void waitForConnection(){
		try {
			connection = server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void setUpStreams() {
		try {
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			output.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
