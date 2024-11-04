package operatingSystemProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
//CORRECT VERSION
public class ThreadedServer {
	public static void main(String[] args) {
		// Hardcode in IP and Port here if required
		ArrayList<String> jobsToBeDone = new ArrayList<>(); // ignore this for now
		// The ArrayList should be the jobs to be done and sent to the slaves from here?
		// For this project, I've been doing them inside an individual client, cuz it was easier
		// Please ask me for clarification if this made no sense... 
    	args = new String[]{"30121"};
    	
        if (args.length != 1) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        final int THREADS = 3;

        // Allow 3 different clients to connect
        try (ServerSocket serverSocket = new ServerSocket(portNumber);){	
        	ArrayList<Thread> threads = new ArrayList<Thread>();
        		for(int i = 0; i < THREADS; i++) {
        			threads.add(new Thread(new ServerThread(serverSocket, i)));
        		}
        		for(Thread t: threads) {
        			t.start();
        		}
        		for(Thread t:threads) {
        			try {
        				t.join();
        			}catch(InterruptedException e) {
        				e.printStackTrace();
        			}
        		}
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to 30122");
            System.out.println(e.getMessage());
            System.exit(1);
        } 
    }

	}

