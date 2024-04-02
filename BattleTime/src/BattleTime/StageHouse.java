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
				System.out.println("아 참, 저장은 하셨나요? (y/n)");
				System.out.print("👉 ");
				if(GameManager.sc.next().equals("y")) {
					System.out.println("다음 날 다시 만나요 ~");
					GameManager.nextStage = "";
				} else {
					System.out.println("저장하고 자야겠다....");
					GameManager.nextStage = "LOBBY";
				} 
			} else {
				System.out.println("조금 더 깨어있자...");
				GameManager.nextStage = "LOBBY";
			}
			
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
