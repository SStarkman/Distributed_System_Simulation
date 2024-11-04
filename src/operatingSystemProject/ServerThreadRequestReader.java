package operatingSystemProject;
import java.util.*;
import java.net.*;
import java.util.Random;
import java.io.*;
//CORRECT VERSION
public class ServerThreadRequestReader implements Runnable{
	
	private BufferedReader requestReader = null;
	private ArrayList<String> jobs = null;
	
	public ServerThreadRequestReader(BufferedReader reader, ArrayList<String> arrayList) {
		this.requestReader = reader;
		this.jobs = arrayList;
	}

	@Override
	public void run() {
		// This waits for a request to come in, and adds it to the job list when provided
		// This should have synchronized block and locks
			String requestString;
			try {
				while((requestString = requestReader.readLine()) != null) {
					//System.out.println("In Server Thread Request Reader");
	                jobs.add(requestString);
	                //System.out.println("Added Job " + requestString);
	                //System.out.println(jobs);
	                //System.out.println("Finished adding job");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}			
	
}
