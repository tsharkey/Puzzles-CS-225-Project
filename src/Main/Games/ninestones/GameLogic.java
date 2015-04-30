package Main.Games.ninestones;

public class GameLogic {
	
	private int money = 27;
	private int weightLeft = 0;
	private int weightRight = 0;
	
	public GameLogic() {
		//
	}
	
	// getters
	int getMoney() {
		return this.money;
	}
	
	int getWeighLeft() {
		return this.weightLeft;
	}
	
	int getWeighRight() {
		return this.weightRight;
	}
	
	// setters
	void setWeightLeft(int weightLeft) {
		this.weightLeft = weightLeft;
	}
	
	void setWeightRight(int weightRight) {
		this.weightRight = weightRight;
	}
	
	// 
	void deductMoney() {
		if(this.money > 0) {
			this.money -= 9;
		}
	}
	
	public void weighRocks() {
		//
	}
	
	public void buyRock() {
		//
	}
	
}
