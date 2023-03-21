/**
 * 
 */
package us.jyni.cell.brain.entity;

/**
 * @author USER
 *
 */
public interface Strength {

	public static enum StrengthEnum implements Strength {
		
		P0, P1, P2, P3, P4, P5;

		@Override
		public String getName() {
			return this.name();
		}

		@Override
		public int getValue() {
			return this.ordinal();
		}
	}
	
	public String getName();
	
	public int getValue();
}
