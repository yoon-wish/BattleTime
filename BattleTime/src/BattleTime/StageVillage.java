package BattleTime;

public class StageVillage extends Stage {

	private final int STORE = 1;
	private final int HOUSE = 2;
	private final int LOBBY = 3;

	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 상점");
		System.out.println("    ❷ 아지트");
		System.out.println("    ❸ 로비");
		System.out.println("└──────────────┘");

		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();

		while (!((sel == STORE) || (sel == LOBBY) || (sel == HOUSE))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		if (sel == STORE)
			GameManager.nextStage = "STORE";
		else if(sel == HOUSE)
			GameManager.nextStage = "HOUSE";
		else if (sel == LOBBY)
			GameManager.nextStage = "LOBBY";

		return false;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
