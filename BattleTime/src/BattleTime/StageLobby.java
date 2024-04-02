package BattleTime;

public class StageLobby extends Stage {
	private final int VILLAGE = 1;
	private final int BATTLE = 2;
	private final int SAVE = 3;
	private final int EXIT = 4;

	@Override
	public boolean update() {
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 마을");
		System.out.println("    ❷ 전투");
		System.out.println("    ❸ 저장");
		System.out.println("    ❹ 종료");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();

		while (!((sel == VILLAGE) || (sel == BATTLE) || (sel == SAVE) || (sel == EXIT))) {
			System.out.print("👉 ");
			sel = GameManager.sc.nextInt();
		}

		if (sel == VILLAGE)
			GameManager.nextStage = "VILLAGE";
		else if (sel == BATTLE) {
			
			if (GameManager.battleNum == 0) {
				System.out.println("┌────────────────────────────────────┐");
				System.out.println("   오늘 이미 전투를 치뤘다");
				System.out.println("   다들 지친 상태이다");
				System.out.println("   휴식을 취하고 다음 날 다시 오자");
				System.out.println("└────────────────────────────────────┘");
				GameManager.nextStage = "LOBBY";
			} else {
				if(StageBattle.allDead) {
					System.out.println("┌────────────────────────────────────┐");
					System.out.println("   다들 지친 상태이다");
					System.out.println("   휴식을 취하고 다시 도전하자");
					System.out.println("└────────────────────────────────────┘");
					GameManager.nextStage = "LOBBY";
				} else 
					GameManager.nextStage = "BATTLE";
			}
			
		} else if (sel == SAVE) {
			GameManager.day += 1;
			GameManager.fileManager.save(saveInfo());
			GameManager.nextStage = "LOBBY";
		} else if (sel == EXIT) {
			System.out.println("종료하실건가요? (y/n)");
			System.out.print("👉 ");
			if(GameManager.sc.next().equals("y")) {
				System.out.println("아 참, 저장은 하셨나요? (y/n)");
				System.out.print("👉 ");
				if(GameManager.sc.next().equals("y")) {
					System.out.println("다음에 다시 만나요 ~");
					GameManager.nextStage = "";
				} else {
					System.out.println("저장부터 하자....");
					GameManager.nextStage = "LOBBY";
				} 
			} else {
				System.out.println("조금 더 둘러보자...");
				GameManager.nextStage = "LOBBY";
			}
		}

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
