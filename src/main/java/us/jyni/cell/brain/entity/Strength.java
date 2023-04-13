/**
 * 
 */
package us.jyni.cell.brain.entity;

/**
 * @author USER
 * @since 2023-03-18
 */
public interface Strength {

	/**
	 * inner enum for Strength
	 */
	public static enum StrengthEnum implements Strength {
		
		/** 별점 0 */
		P0,
		/** 별점 1 */
		P1,
		/** 별점 2 */
		P2,
		/** 별점 3 */
		P3,
		/** 별점 4 */
		P4,
		/** 별점 5 */
		P5;

		@Override
		public String getName() {
			return this.name();
		}

		@Override
		public int getValue() {
			return this.ordinal();
		}
	}
	
	/**
	 * @return 이름
	 */
	public String getName();
	
	/**
	 * @return 값
	 */
	public int getValue();
}
