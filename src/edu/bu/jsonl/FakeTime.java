/**
 * 
 */
package edu.bu.jsonl;

import java.util.Formatter;
import java.util.Locale;
import java.util.Observable;

/**
 * @author Jason Lu
 * The singleton class to store fake system time.
 *
 */
public class FakeTime extends Observable implements Runnable {
	
	private static FakeTime self;
	
	/**
	 * The fake system time stored in integer
	 * <p>
	 * System time by integer means the second of the day.
	 * 0 		=> 00:00:00
	 * 3600 	=> 01:00:00
	 * 34200	=> 09:30:00
	 * 57600	=> 16:00:00
	 * <p>
	 */
	private int time;
	
	/**
	 * The lapse each tick take.
	 */
	private int lapse;
	
	/**
	 * The interval between tick.
	 */
	private int interval;

	private boolean stop, ticking;
	
	/**
	 * 
	 */
	private FakeTime() {
		// 0: 00:00:00
		this.time = 0;
		this.lapse = 30;
		this.interval = 10;
		this.stop = false;
		this.ticking = false;
	}
	
	public void run() {
		if(this.ticking) {
			return;
		}
		this.ticking = true;
		self = getInstance();
		boolean notified = false;
		//this.time = 34100;
		while(!this.stop) {
			
			self.addTime(lapse);
			
			if(this.time >= 34200 && !notified) {
				setChanged();
				notifyObservers();
				notified = true;
			} else if(this.time >= 57600) {
				notified = false;
			}
			try {
				Thread.sleep(interval);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.stop = true;
		this.ticking = false;
	}
	
	public static synchronized FakeTime getInstance() {
		if(self == null) {
			self = new FakeTime();
		}
		return self;
	}
	
	/**
	 * The lapse each tick take.
	 */
	public void setLapse(int newLapse) {
		
		this.lapse = newLapse;
	}
	
	/**
	 * The interval between tick.
	 */
	public void setInterval(int newInterval) {
		this.interval = newInterval;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int newTime) {
		time = (newTime % 86400);
	}
	
	public String getTimeStr() {
		StringBuilder sb = new StringBuilder();
		// Send all output to the Appendable object sb
		Formatter formatter = new Formatter(sb, Locale.US);
		String res = "";
		int hInt = time / 3600;
		int mInt = (time % 3600) / 60;
		int sInt = time % 60;
		formatter.format("%02d:%02d:%02d", hInt, mInt, sInt);
		res = sb.toString();
		formatter.close();
		return res;
	}
	
	public void addTime(int howMany) {
		time += howMany;
		//System.out.println(howMany);
		if(time >= 86400) {
			time = (time % 86400);
		}
	}
	

}
