package BattleTime;

import java.util.ArrayList;

public class UnitManager {
	private final int SIZE = 3;

	private final int WARRIOR = 1;
	private final int WIZARD = 2;
	private final int HEALER = 3;
	public static ArrayList<Unit> monster_list;
	public static ArrayList<Player> player_list;
	public static int[] player;

	private String path = "BattleTime.Unit";
	private String monNames[] = { "Bat", "Orc", "Troll" };

	public UnitManager() {
		monster_list = new ArrayList<>();
	}

	public void setPlayer() {
		player_list = new ArrayList<>();
		player = new int[SIZE];
		
		printStart();
		playerInfo();
		for (int i = 0; i < SIZE; i++) {
			System.out.printf("%d번째 길드원 👉 ", i+1);
			int sel = GameManager.sc.nextInt();
			player[i] = sel;

			if (sel == WARRIOR) {
				player_list.add(new PlayerWarrior());
			} else if (sel == WIZARD) {
				player_list.add(new PlayerWizard());
			} else if (sel == HEALER) {
				player_list.add(new PlayerHealer());
			}
		}
		System.out.println("┌───────────────────────┐");
		System.out.println("    🛡️길드 결성 완료🛡️");
		System.out.println("└───────────────────────┘");
	}

	private void printStart() {
		try {
			Thread.sleep(700);
			System.out.println("╔═════════════ ⚔️ 𝓑𝓪𝓽𝓽𝓵𝓮 𝓣𝓲𝓶𝓮 ⚔️ ═════════════╗");
			Thread.sleep(700); 
			System.out.println("            배틀타임에 온 것을 환영하네!            ");
			Thread.sleep(700);
			System.out.println("        오늘은 최고의 길드원 3명을 선택해야 해.       ");
			Thread.sleep(700);
			System.out.println("      함께라면 모든 도전을 이겨낼 힘을 가질 것이야.    ");
			Thread.sleep(700);
			System.out.println("                  지금 선택하게.                  ");
			Thread.sleep(700);
			System.out.println("       우리 팀의 승리를 이끌어 낼 최고의 동료들을!!   ");
			Thread.sleep(700);
			System.out.println("            함께 우리의 모험을 시작하자!            ");
			Thread.sleep(700);
			System.out.println("╚═════════════════════════════════════════════╝");
			Thread.sleep(700);
		} catch (Exception e) {
		}

		System.out.println("┌────────────────────────────────────────┐");
		System.out.println("  지금부터 3명의 길드원을 선택합니다");
		System.out.println("└────────────────────────────────────────┘");

	}

	private void playerInfo() {
		System.out.println("┌──────────────────────────────┐");
		System.out.println("    ❶ 전사 (♥1000/🗡️100)");
		System.out.println("    ❷ 마법사 (♥700/🗡️80)");
		System.out.println("    ❸ 힐러 (♥500/🗡️50)");
		System.out.println("└──────────────────────────────┘");
	}

	public void loadPlayer(int[] player) {
		player_list = new ArrayList<>();
		
		for (int i = 0; i < player.length; i++) {
			if (player[i] == WARRIOR) {
				System.out.println("진입");
				player_list.add(new PlayerWarrior());
			} else if (player[i] == WIZARD) {
				player_list.add(new PlayerWizard());
			} else if (player[i] == HEALER) {
				player_list.add(new PlayerHealer());
			}
		}
	}

	public void monster_rand_set(int size) {
		for (int i = 0; i < size; i++) {
			int num = GameManager.rand.nextInt(monNames.length);
			try {
				Class<?> clazz = Class.forName(path + monNames[num]);
				Object obj = clazz.getDeclaredConstructor().newInstance();

				Unit temp = (Unit) obj;
				int hp = (GameManager.rand.nextInt(GameManager.ranHp) + 2) * 50;
				int power = GameManager.rand.nextInt(GameManager.ranPower) + 10;

				temp.init(hp, power);
				monster_list.add(temp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
