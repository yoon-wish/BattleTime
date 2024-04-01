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
		else if (sel == BATTLE)
			GameManager.nextStage = "BATTLE";
		else if (sel == EXIT)
			GameManager.nextStage = "";

		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
