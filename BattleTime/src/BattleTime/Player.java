package BattleTime;

public class Player extends Unit {
	
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
	
	public void skill(Unit target) {
		if(this.getName().equals("전사")) {
			worriorSkill(target);
		} else if(this.getName().equals("마법사")) {
			wizardSkill();
		} else if(this.getName().equals("힐러")) {
			healerSkill();
		}
	}
	
	// 죽음의 일격(2배 공격)
	private void worriorSkill(Unit target) {
		System.out.println("✥﹤┈┈┈┈ 죽음의 일격 ┈┈┈┈﹥✥");
		target.setHp(target.getHp() - this.getPower() * 2);
		System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), target.getName(), this.getPower() * 2);
		if(target.getHp() <= 0) {
			target.setHp(0);
			System.out.printf("[%s]를 처치했습니다.\n", target.getName());
		}
	}
	
	// 마법의 태풍(여러 마리 한 번에 공격) 
	private void wizardSkill() {
		System.out.println("✥﹤┈┈┈┈ 마법의 태풍 ┈┈┈┈﹥✥");
		for(int i=0; i<GameManager.monsterList.size(); i++) {
			Unit monster = GameManager.monsterList.get(i);
			monster.setHp(monster.getHp() - this.getPower() / 2);
			System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), monster.getName(), this.getPower() / 2);
			if(monster.getHp() <= 0) {
				monster.setHp(0);
				System.out.printf("[%s]를 처치했습니다.\n", monster.getName());
			}
		}
	}
	
	// 안정의 선율(팀원 전체 치유 [플레이어 maxHp의 30% 치유])
	private void healerSkill() {
		System.out.println("✥﹤┈┈┈┈ 안정의 선율 ┈┈┈┈﹥✥");
		for(int i=0; i<GameManager.playerList.size(); i++) {
			Player player = GameManager.playerList.get(i);
			double heal = player.getMaxHp() * 0.3;
			if(player.getHp() == 0) {
				return;
			}
			
			if(player.getHp() + heal > player.getMaxHp()) {
				heal = player.getMaxHp() - player.getHp();
			}
			
			player.setHp(player.getHp() + (int) heal);
			System.out.printf("❤️ [%s]가 %d의 HP를 회복합니다\n", player.getName(), (int) heal);
		}
	}

}
