package BattleTime;

public class PlayerHealer extends Player{

	public PlayerHealer() {
		super("힐러", 500, 50);
	}

	@Override
	public void skill() {
		// 안정의 선율(팀원 전체 치유 [플레이어 maxHp의 30% 치유])
		System.out.println("🌿💫✨~༺༄ 안정의 선율 ༄༻~✨💫🌿");
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

	@Override
	public void skill(Unit target) {
		
	}

}
