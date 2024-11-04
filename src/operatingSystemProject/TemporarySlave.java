package operatingSystemProject;
import java.util.*;
import java.net.*;
import java.util.Random;
import java.io.*;
//CORRECT VERSION
public class TemporarySlave implements Runnable{
	// This is just temporary to allow for jobs to move from jobs to complete
	// Really, the slave is going to need to have threads to communicate back and forth, right?
	// Which I don't really get, so we need to discuss that part.... 
	private ArrayList<String> jobs = null;
	private ArrayList<String> complete = null;
	
	public TemporarySlave(ArrayList<String> jobs, ArrayList<String> arrayList) {
		this.jobs = jobs;
		this.complete = arrayList;
	}

	@Override
	public void run() {
				while(true){// while connected? 
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jobs.isEmpty()) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
					//System.out.println(jobs);
	             
	            	   System.out.println("Shuffling Jobs");
	            	   Collections.shuffle(jobs);
	            	   // MUST HAVE SYNCHONIZED BLOCK HERE!!
	            	   /*
	            	   try {
						Thread.sleep(3000); // sleep for 3 seconds
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					*/
	            	   System.out.println("Adding to complete list, removing from job list: " + jobs.get(0));
	            	   complete.add(jobs.get(0));
	            	   jobs.remove(0);
	            	   System.out.println(jobs);
	            	   System.out.println(complete);
					}
			
				}
			
	
}
}
