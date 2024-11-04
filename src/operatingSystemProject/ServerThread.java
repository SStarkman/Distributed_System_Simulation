package operatingSystemProject;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerThread implements Runnable{
	ArrayList<String> jobs = new ArrayList<>();
	ArrayList<String> complete = new ArrayList<>();
	
	private ServerSocket serverSocket = null;
	int id;
	
	public ServerThread(ServerSocket s, int id) {
		serverSocket = s;
		this.id = id;
	}

	@Override
	public void run() {
		// Within an individual client connection
		try(Socket clientSocket = serverSocket.accept();
				PrintWriter responseWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));){
			/*
			String requestString;
			
			while((requestString = requestReader.readLine()) != null) {
				System.out.println(requestString + " received by listener: " + id);
				responseWriter.println(magicEightBall.getNextAnswer());
			}
			*/
			// For each client, create a thread to read and a separate one to write
			// There is also a thread that is doing the work, called TemporarySlave
			// I'm not sure if Temporary Slave should really be in the main server
			// Jobs and Complete should also have locks so they aren't overwriting each other
        	Thread[] threads = {new Thread(new ServerThreadRequestReader(requestReader, jobs)),
        			new Thread(new ServerThreadResponseWriter(responseWriter, complete)),
        			new Thread(new TemporarySlave(jobs, complete))};
        	
        	for(int i = 0; i < threads.length; i++) {
        		threads[i].start();
        	}

    		for(int i = 0; i < threads.length; i++) {
    			try {
    				threads[i].join();
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
        
		}catch(IOException e) {
			System.out.println("Exception caught when trying to listen on port " + serverSocket.getLocalPort() + " or listening for connection");
			System.out.println(e.getMessage());
		}
		
	}			
	
}
