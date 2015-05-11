package Main.Games.ninestones;

public class GameLogic {
	
	private int money = 27;
	private int weightLeft = 0;
	private int weightRight = 0;
	
	public GameLogic() {
		
	}
	
	// getters
	int getMoney() {
		return this.money;
	}
	
	public void resetMoney(){
		this.money= 27;
	}
	
	int getWeighLeft() {
		return this.weightLeft;
	}
	
	int getWeighRight() {
		return this.weightRight;
	}
	
	// setters
	void setWeightLeft(int weightLeft) {
		this.weightLeft += weightLeft;
	}
	
	void setWeightRight(int weightRight) {
		this.weightRight += weightRight;
	}
	
	public void clearScale(){
		this.weightLeft = 0;
		this.weightRight = 0;
	}
	
	void deductMoney() {
		if(this.money > 0) {
			this.money -= 9;
		}
	}
	
	public int weighRocks() {
		if(this.weightLeft > this.weightRight){
			return 0;
		}
		else if(this.weightLeft < this.weightRight){
			return 1;
		}
		else{
			return 2;
		}
	}
	
}
