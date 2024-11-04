package operatingSystemProject;

import java.net.*;
import java.util.Random;
import java.io.*;
//CORRECT VERSION
public class ClientThreadResponseReader implements Runnable{
	
	private BufferedReader responseReader = null;
	
	public ClientThreadResponseReader(BufferedReader responseReader) {
		this.responseReader = responseReader;
	}

	@Override
	public void run() {
		// Wait for response from server and display to screen when provided
			String responseString;
			try {
				while((responseString = responseReader.readLine()) != null) {
				//	System.out.println("In server response");
					System.out.println("SERVER SAYS: " + responseString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}			
	
}
