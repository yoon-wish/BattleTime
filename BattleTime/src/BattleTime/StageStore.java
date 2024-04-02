package BattleTime;

public class StageStore extends Stage {

	private final int PRICE = 200;
	private int potion;

	public StageStore() {
		this.potion = GameManager.rand.nextInt(3);
	}

	@Override
	public boolean update() {

		System.out.println("┌──────┐");
		System.out.println("  👤💭 ");
		System.out.println("└──────┘");
		System.out.println("인자한 미소의 주인이 길드원들을 맞이한다!");
		try {
			System.out.println("┌────────────────────────────────────┐");
			Thread.sleep(500);
			System.out.println("   허허허.. 반갑네 모두들 ");
			Thread.sleep(500);
			if (this.potion == 0) {
				System.out.println("   아쉽게도 오늘은 들어온 물건이 없어");
				Thread.sleep(500);
				System.out.println("└────────────────────────────────────┘");

				System.out.println("마을로 돌아가야겠다.....");
				GameManager.nextStage = "VILLAGE";
				return false;
			} else {
				System.out.println("   마침 오늘 포션이 들어왔지");
				Thread.sleep(500);
				System.out.println("   개수는 " + this.potion + "개야");
				Thread.sleep(500);
				System.out.println("   구매하겠는가?");
				System.out.println("└────────────────────────────────────┘");
			}
		} catch (Exception e) {
		}

		System.out.println("주인이 구매 의사를 묻는다 (y/n)");
		if (GameManager.sc.next().equals("y")) {
			if (buyPotion()) {
				System.out.println("기분 좋은 쇼핑이었다");
				System.out.println("이제 마을로 돌아가자");
				GameManager.nextStage = "VILLAGE";
				return false;
			} else {
				Back();
				return false;
			}
		} else {
			try {
				System.out.println("┌─────────────────────────────┐");
				Thread.sleep(500);
				System.out.println("  어? 구매하지 않는건가");
				Thread.sleep(500);
				System.out.println("  알겠네");
				Thread.sleep(500);
				System.out.println("  조심히 돌아가게");
				Thread.sleep(500);
				System.out.println("└─────────────────────────────┘");
				Back();
				return false;
			} catch (Exception e) {
			}
		}

		return false;
	}

	private boolean buyPotion() {

		System.out.println("┌─────────────────────────────┐");
		System.out.println("  몇 개 구매 하겠는가?");
		System.out.println("  개 당 200원일세");
		System.out.println("└─────────────────────────────┘");

		System.out.print("👉 ");
		int number = GameManager.sc.nextInt();

		while (number < 0 || number > this.potion) {
			System.out.println("┌─────────────────────────────┐");
			System.out.println("  아냐아냐 다시 말해보게");
			System.out.printf("  %d개 구매할 수 있네\n", this.potion);
			System.out.println("└─────────────────────────────┘");
			System.out.print("👉 ");
			number = GameManager.sc.nextInt();
		}

		if (number * PRICE > GameManager.money) {
			try {
				System.out.println("┌───────────────────────────────┐");
				Thread.sleep(500);
				System.out.println("  허허.... 돈이 부족한 것 같네");
				Thread.sleep(500);
				System.out.println("  다음에 다시오게나");
				Thread.sleep(500);
				System.out.println("└───────────────────────────────┘");
				Thread.sleep(500);
			} catch (Exception e) {
			}
			System.out.println("쫓겨나듯이 상점을 빠져나왔다");
			return false;
		}

		GameManager.potion += number;
		System.out.println("보유 포션 : " + GameManager.potion + "개");

		this.potion -= number;

		return true;
	}

	private void Back() {
		System.out.println("마을로 돌아가야겠다.....");
		GameManager.nextStage = "VILLAGE";
	}

	@Override
	public void init() {

	}

}
