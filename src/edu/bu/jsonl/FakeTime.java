/**
 * 
 */
package edu.bu.jsonl;

import java.util.Formatter;
import java.util.Locale;

/**
 * @author Jason Lu
 * The singleton class to store fake system time.
 *
 */
public class FakeTime implements Runnable {
	
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

	private boolean stop;
	
	/**
	 * 
	 */
	private FakeTime() {
		// 0: 00:00:00
		time = 0;
		lapse = 30;
		interval = 10;
		this.stop = false;
	}
	
	public void run() {
		self = getInstance();
		while(!this.stop) {
			self.addTime(lapse);
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
	}
	
	public static FakeTime getInstance() {
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