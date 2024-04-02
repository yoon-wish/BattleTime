package BattleTime;

public class StageLobby extends Stage {
	private final int VILLAGE = 1;
	private final int BATTLE = 2;
	private final int EXIT = 3;

	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 마을");
		System.out.println("    ❷ 전투");
		System.out.println("    ❸ 종료");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();

		while (!((sel == VILLAGE) || (sel == BATTLE) || (sel == EXIT))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		if (sel == VILLAGE)
			GameManager.nextStage = "VILLAGE";
		else if (sel == BATTLE) {
			if(GameManager.battleNum == 0) {
				System.out.println("┌────────────────────────────────────┐");
				System.out.println("   오늘 이미 전투를 치뤘다");
				System.out.println("   다들 지친 상태이다");
				System.out.println("   휴식을 취하고 다음 날 다시 오자");
				System.out.println("└────────────────────────────────────┘");
				GameManager.nextStage = "LOBBY";
			}else
				GameManager.nextStage = "BATTLE";
		}
		else if (sel == EXIT)
			GameManager.fileManager.save(saveInfo());
			GameManager.nextStage = "";

		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
	
	private String saveInfo() {
		// 날짜/포션/코인/몬스터Hp
		return GameManager.day + "/" + GameManager.potion + "/" + GameManager.coin + "/" + GameManager.ranHp;
	}

}
