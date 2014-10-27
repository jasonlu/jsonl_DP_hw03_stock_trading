/**
 * 
 */
package edu.bu.jsonl;

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
		// Set fake time to 9:30am;
		fakeTime.setTime(34200);
		
		// Set fake time to 4:00pm;
		// fakeTime.setTime(57600);
		
		fakeTime.setLapse(30);
		fakeTime.setInterval(500);
		
		// Detach fake clock from main thread.
		Thread tick = new Thread(fakeTime);
		// Clock's ticking...
        tick.start();
        
        /*
        while(fakeTime.getTime() < 86400) {
        	Thread.sleep(500);
        	System.out.println("Time is:" + fakeTime.getTime() +  ", " +intToTimeStr(fakeTime.getTime()));
        	
        }
        */
        StockTrade stockTrade = new StockTrade();
        Agent agent = new Agent();
        
        
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
        	boolean endMe = false;
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
            	amount = reader.nextInt();
        		order = new SellStockOrder(stockTrade, new OrderDetail(symbol, amount));
        		break;
        	}
        	
        	if(endMe) {
        		reader.close();
        		break;
        	}
        	System.out.println(fakeTime.getTimeStr() + " order placed...");
        	agent.placeOrder(order);
        }
        
        System.out.println("Program ended... Good bye.");
		
        // Exception
        // SellStockOrder sellOrderWrong = new SellStockOrder (stockTrade, new OrderDetail("GOOG", 0));
    }
	
	
}

