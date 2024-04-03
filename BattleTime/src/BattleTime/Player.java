package BattleTime;

public class Player extends Unit implements SkillAble{
	
	private int sp;
	
	public Player(String name, int maxHp, int power){
		super(name, maxHp, power);
		this.sp = GameManager.maxSp;
	}
	
	public int getSp() {
		return this.sp;
	}
	
	public void setSp() {
		this.sp--;
	}
	
	public void setSp(int maxSp) {
		this.sp += maxSp;
	}
	
	public int getHp() {
		return super.getHp();
	}
	
	public void setHp() {
		super.setHp();
	}
	
	public void setHp(int hp) {
		super.setHp(hp);
	}

	public int getMaxHp() {
		return super.getMaxHp();
	}
	
	public int getPower() {
		return super.getPower();
	}
	
	
	public String getName() {
		return super.getName();
	}


	@Override
	public void skill(Unit unit) {
		
	}

	@Override
	public void skill() {
		
	}
	
}
