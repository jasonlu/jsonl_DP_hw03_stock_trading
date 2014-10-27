/**
 * 
 */
package edu.bu.jsonl;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jason Lu
 *
 */
public class Client {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// The fake system time object.
		FakeTime fakeTime = FakeTime.getInstance();
		// Set fake time to 9:00am;
		fakeTime.setTime(32400);
		// Set fake time to 4:00pm;
		// fakeTime.setTime(57600);
		
		fakeTime.setLapse(60);
		fakeTime.setInterval(500);

		// Agent must be initialize before clock start ticking...
		Agent agent = new Agent();
		fakeTime.addObserver(agent);
		
		// Detach fake clock from main thread.
		Thread tick = new Thread(fakeTime);
		// Clock's ticking...
        tick.start();
        
        /*
        while(fakeTime.getTime() < 86400) {
        	Thread.sleep(500);
        	System.out.println("Time is:" + fakeTime.getTimeStr());
        	
        }*/
        
        
        StockTrade stockTrade = new StockTrade();
        
        
        
        // Now, I should make a infinity loop to receive user input and place order...
        String symbol = null, command = null;
        int amount = 0;

        while(command != "e" || command != "E") {
        	amount = 0;
        	symbol = "";
        	command = "";
        	Scanner reader = new Scanner(System.in);
        	System.out.println("Please input operation: (b)uy or (s)ell or (e)xit");
        	command  = reader.nextLine().toLowerCase();
        	System.out.println("Your command:" + command);
        	boolean endMe = false, wrongCmd = false;
        	Order order = null;
        	switch(command) {
        	case "e":
        		fakeTime.stop();
        		tick.join();
        		endMe = true;
        		break;
        		
        	case "b":
        		System.out.println("Enter stock symbol:");
            	symbol = reader.nextLine().toUpperCase();
            	System.out.println("Enter amount");
            	amount = reader.nextInt();
        		order = new BuyStockOrder(stockTrade, new OrderDetail(symbol, amount));
        		break;
        	case "s":
        		System.out.println("Enter stock symbol:");
            	symbol = reader.nextLine();
            	System.out.println("Enter amount");
            	try {
            		amount = reader.nextInt();
            	} catch (Exception e) {
            		amount = 0;
            	}
        		order = new SellStockOrder(stockTrade, new OrderDetail(symbol, amount));
        		break;
        	default:
        		wrongCmd = true;
        		break;
        	}
        	
        	if(endMe) {
        		reader.close();
        		break;
        	}
        	if(!wrongCmd) {
        		System.out.println(fakeTime.getTimeStr() + " order placed...");
        		agent.placeOrder(order);
        	}
        }
        
        System.out.println("Program ended... Good bye.");
		
        // Exception
        // SellStockOrder sellOrderWrong = new SellStockOrder (stockTrade, new OrderDetail("GOOG", 0));
    }
	
	
}

