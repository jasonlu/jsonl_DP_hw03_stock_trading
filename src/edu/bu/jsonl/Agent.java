/**
 * 
 */
package edu.bu.jsonl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Jason Lu
 *
 */
public class Agent implements Observer {
	private ArrayList<Order> m_ordersQueue = new ArrayList<Order>();
	private FakeTime m_fakeTime;

    public Agent() {
    	m_fakeTime = FakeTime.getInstance();
    	//m_fakeTime.addObserver(this);
    }
    
    void placeOrder(Order order) {
    	if(order != null ) {
    		m_ordersQueue.add(order);
    	}
    	clearQueue();
    }
    
    void executeOrder(Order order) {
    	try {
    		System.out.println(m_fakeTime.getTimeStr() + " executed queue");
    		order.execute();
		} catch (WrongOrderException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());
		}
    }
    
    void clearQueue() {
    	if(isBusinessTime()) {
	    	while(m_ordersQueue.size() > 0) {
		    	executeOrder(m_ordersQueue.remove(0));
	    	}
    	} else {
    		System.out.println("Agent: Not business hour, request queued");
    	}
    	
    }
    
    private Boolean isBusinessTime() {
    	// If Weekday && 9:30 - 16:00
    	if(m_fakeTime.getTime() >= 34200 && m_fakeTime.getTime() < 57600 ) {
    		return true;
    	} else {
    		return false;
    	}
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Agent: It's " + m_fakeTime.getTimeStr() + " market opens now,");
		clearQueue();
		
	}
}
