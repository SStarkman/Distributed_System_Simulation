package operatingSystemProject;
import java.util.*;
import java.net.*;
import java.util.Random;
import java.io.*;
//CORRECT VERSION
public class ServerThreadResponseWriter implements Runnable{
	
	private PrintWriter responseWriter = null;
	private ArrayList<String> complete = null;
	
	public ServerThreadResponseWriter(PrintWriter writer, ArrayList<String> arrayList) {
		this.responseWriter = writer;
		this.complete = arrayList;
	}

	@Override
	public void run() {
		// When the complete list is empty, sleep for 3 seconds to wait until something comes up
		// When it is not empty, write it to the responseWriter and remove from list
		// This should also have synchronized blocks and locks
			while(true) {
				if(this.complete.isEmpty()){
					// while connected? 
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}else {
	              
	            	   //System.out.println("In Server Thread Response Writer.");
	            	   responseWriter.println(complete.get(0));
	            	   System.out.println("Writing Response" + complete.get(0));
	            	   complete.remove(0);
				}
				  
			}
		
	}			
	
}
