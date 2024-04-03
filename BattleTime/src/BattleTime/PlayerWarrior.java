package BattleTime;

public class PlayerWarrior extends Player{

	public PlayerWarrior() {
		super("전사", 1000, 100);
	}

	@Override
	public void skill(Unit target) {
		// 죽음의 일격(2배 공격)
		System.out.println(GameManager.ANSI_CYAN + GameManager.ANSI_BOLD +"🗡️💥🔥~༺༄ 죽음의 일격 ༄༻~🔥💥🗡️"+ GameManager.ANSI_RESET);
		target.setHp(target.getHp() - this.getPower() * 2);
		System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.getName(), target.getName(), this.getPower() * 2);
		if(target.getHp() <= 0) {
			target.setHp(0);
			System.out.printf("[%s]를 처치했습니다.\n", target.getName());
		}
	}

	@Override
	public void skill() {
		
	}

}
