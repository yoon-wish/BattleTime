package BattleTime;

public class StageHouse extends Stage{

	private final int SLEEP = 1;
	private final int WALLET = 2;
	private final int VILLAGE = 1;
	
	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 취침");
		System.out.println("    ❷ 지갑");
		System.out.println("    ❸ 마을");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();
		
		while (!((sel == SLEEP) || (sel == WALLET))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}
		
		if(sel == SLEEP) {
			
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
