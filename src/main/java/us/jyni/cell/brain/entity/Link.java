/*
 * 
 */
package us.jyni.cell.brain.entity;

/**
 * @author jynius
 * @since 2023-04-09
 */
public interface Link {

	public String getSource();
	
	public String getTarget();
	
	public Number getStrength();
}
