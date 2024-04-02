package BattleTime;

public class StageHouse extends Stage{

	private final int SLEEP = 1;
	private final int WALLET = 2;
	private final int VILLAGE = 3;
	
	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 취침");
		System.out.println("    ❷ 지갑");
		System.out.println("    ❸ 마을");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();
		
		while (!((sel == SLEEP) || (sel == WALLET) || (sel == VILLAGE))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}
		
		if(sel == SLEEP) {			
			System.out.println("잠에 들건가요? (y/n)");
			System.out.print("👉 ");
			if(GameManager.sc.next().equals("y")) {
				for(int i=0; i<GameManager.playerList.size(); i++) {
					Player player = GameManager.playerList.get(i);
					player.setHp(player.getMaxHp());
					player.setSp(GameManager.maxSp);
				}
				StageBattle.allDead = false;
				System.out.println("모든 길드원들이 체력과 스킬포인트를 회복했다!!!");
			}
			
			GameManager.nextStage = "HOUSE";
		} else if(sel == WALLET) {
			System.out.println("┌────────────────────────────┐");
			System.out.println("   보유 코인: " + GameManager.coin + " coin");
			System.out.println("└────────────────────────────┘");
		} else if(sel == VILLAGE) {
			GameManager.nextStage = "VILLAGE";
		}
		
		return false;
	}

	@Override
	public void init() {
		
	}

}
