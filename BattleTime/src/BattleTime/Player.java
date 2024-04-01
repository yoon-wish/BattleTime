package BattleTime;

public class Player extends Unit {
	private int potion;
	
	Player(String name, int maxHp, int power){
		super(name, maxHp, power);
		this.potion = 0;
	}
	
	public int getPotion() {
		return this.potion;
	}
	
	public void setPotion() {
		this.potion ++;
	}
	
	void skill() {
		
	}

}
