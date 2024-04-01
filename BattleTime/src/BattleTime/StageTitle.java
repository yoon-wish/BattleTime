package BattleTime;

public class StageTitle extends Stage {

	@Override
	public boolean update() {
		System.out.println(".｡*ﾟ+.*.｡ BATTLE TIME ﾟ+..｡*ﾟ+");
		System.out.println("[시작]을 입력하세요.");
		System.out.print("👉 ");
		String start = GameManager.sc.next();

		while (!start.equals("시작")) {
			System.out.print("👉 ");
			start = GameManager.sc.next();
		}

		GameManager.nextStage = "LOBBY";

		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
