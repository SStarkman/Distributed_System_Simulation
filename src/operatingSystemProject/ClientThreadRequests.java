package operatingSystemProject;

import java.net.*;
import java.util.Random;
import java.io.*;
//CORRECT VERSION
public class ClientThreadRequests implements Runnable{
	
	private BufferedReader stdIn = null;
	private PrintWriter requestWriter = null;
	
	public ClientThreadRequests(BufferedReader stdIn, PrintWriter requestWriter) {
		this.stdIn = stdIn;
		this.requestWriter = requestWriter;
	}

	@Override
	public void run() {
		// Wait for user input and when provided, write it as a request to server
			String userInput;
			try {
	            while ((userInput = stdIn.readLine()) != null) {
	            	System.out.println("Sending user input "+ userInput);
	                requestWriter.println(userInput); // send request to server
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}			
	
}
