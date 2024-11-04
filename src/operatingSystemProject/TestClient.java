package operatingSystemProject;
import java.io.*;
import java.net.*;

// CORRECT VERSION
public class TestClient {
    public static void main(String[] args) throws IOException {
        
		// Hardcode in IP and Port here if required
    	args = new String[] {"127.0.0.1", "30121"};
    	
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
        		// Client creates a socket, and it's individual PrintWriter and Readers
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter requestWriter = // stream to write text requests to server
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader responseReader= // stream to read text response from server
                new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())); 
            BufferedReader stdIn = // standard input stream to get user's requests
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
        	// Create one thread for reading responses and one thread for writing requests
        	Thread[] threads = {new Thread(new ClientThreadResponseReader(responseReader)),
        			new Thread(new ClientThreadRequests(stdIn, requestWriter))};
        	//Thread responseReadThread = new Thread(new ClientThreadResponseReader(responseReader));
        	//Thread requestMakingThread = new Thread(new ClientThreadRequests(stdIn, requestWriter));
        	
        	// Start both threads
        	for(int i = 0; i < threads.length; i++) {
        		threads[i].start();
        	}
        	
        	// Wait for them to finish before ending the client
    		for(int i = 0; i < threads.length; i++) {
    			try {
    				threads[i].join();
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
        	
        	
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}
