/**
 * 
 */
package edu.bu.jsonl;

import java.util.ArrayList;

/**
 * @author Jason Lu
 *
 */
public class Agent {
	private ArrayList<Order> m_ordersQueue = new ArrayList<Order>();
	private FakeTime m_fakeTime;

    public Agent() {
    	m_fakeTime = FakeTime.getInstance();
    }
    
    void placeOrder(Order order) {
    	m_ordersQueue.add(order);
    	Order firstQueue = m_ordersQueue.remove(0);
    	if(isBusinessTime()) {
	    	try {
	    		System.out.println(m_fakeTime.getTimeStr() + " executed queue");
				firstQueue.execute();
			} catch (WrongOrderException e) {
				// TODO Auto-generated catch block
				System.out.println("Error: " + e.getMessage());
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
}
