/**
 * 
 */
package edu.bu.jsonl;

/**
 * @author Jason Lu
 *
 */
public interface Order {
	public void execute () throws WrongOrderException;
}
