package BattleTime;

public class StageTitle extends Stage {

	@Override
	public boolean update() {
		System.out.println("｡☆✼★━━━━━━ BATTLE TIME ━━━━━━★✼☆｡");
		System.out.println("[시작]을 입력하세요.");
		System.out.print("👉 ");
		String start = GameManager.sc.next();

		while (!start.equals("시작")) {
			System.out.print("👉 ");
			start = GameManager.sc.next();
		}

		System.out.printf("︵‿︵‿୨ << %d일차 >> ୧‿︵‿︵\n", GameManager.day);
		GameManager.nextStage = "LOBBY";

		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
