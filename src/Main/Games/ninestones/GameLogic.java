/**
 * GameLogic.java
 * 
 * Handles game variables and game logic. Used to store current money
 * and weight on scale.
 * 
 */

package Main.Games.ninestones;

public class GameLogic {

	private int money = 27; // default amount of money
	private int weightLeft = 0, weightRight = 0;

	/**
	 * Default constructor
	 */
	public GameLogic() {

	}

	/**
	 * Get current amount of money
	 * 
	 * @return
	 */
	int getMoney() {
		return this.money;
	}

	/**
	 * Reset money back to default
	 */
	public void resetMoney() {
		this.money = 27;
	}

	/**
	 * Remove money from total
	 */
	void deductMoney() {
		if (this.money > 0) {
			this.money -= 9; // fixed amount per action
		}
	}

	/**
	 * Get weigh of left scale
	 * 
	 * @return
	 */
	int getWeighLeft() {
		return this.weightLeft;
	}

	/**
	 * Get weigh of right scale
	 * 
	 * @return
	 */
	int getWeighRight() {
		return this.weightRight;
	}

	/**
	 * Set weigh for left or right side of scale
	 * 
	 * @param weight
	 */
	void setWeight(int weight, boolean isRight) {
		if(isRight){ // add to right side of scale
			this.weightRight += weight;
		}else{ // add to left
			this.weightLeft += weight;
		}
		
	}

	/**
	 * Clear scales to zero
	 */
	public void clearScale() {
		this.weightLeft = 0;
		this.weightRight = 0;
	}

	/**
	 * Weigh both sides of scales and return status
	 * 
	 * @return
	 */
	public int weighRocks() {
		if (this.weightLeft > this.weightRight) {
			return 1; // left side greater
		} else if (this.weightLeft < this.weightRight) {
			return 2; // right side greater
		}
		return 0; // equal
	}

	/**
	 * Check if both scales are empty
	 * 
	 * @return
	 */
	public boolean scalesEmpty() {
		return (this.weightLeft == 0) && (this.weightRight == 0);
	}
}