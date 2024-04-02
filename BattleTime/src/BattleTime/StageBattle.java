package BattleTime;

import java.util.ArrayList;

public class StageBattle extends Stage {

	private final int ATTACK = 1;
	private final int SKILL = 2;
	private final int INVENTORY = 3;

	private ArrayList<Unit> monsterList;
	private int playerDead = 0;
	private int monsterDead = 0;
	
	public static boolean allDead;

	@Override
	public boolean update() {
		init();
		boolean run = true;
		int player_idx = 0;
		int monster_idx = 0;
		boolean turn = true;

		while (run) {
			if (turn) {
				print_character();
				if (player_idx < GameManager.playerList.size()) {
					player_attack(player_idx);
					player_idx += 1;
				} else {
					turn = !turn;
					player_idx = 0;
				}
			} else if (!turn) {
				if (monster_idx < monsterList.size()) {
					System.out.println();
					monster_attack(monster_idx);
					monster_idx += 1;
				} else {
					turn = !turn;
					monster_idx = 0;
				}
			}

			check_live();
			if (monsterDead <= 0 || playerDead <= 0)
				break;
		}

		if (monsterDead <= 0) {
			int coin = GameManager.rand.nextInt(100) + 200;
			System.out.println("┌────────────────────────────┐");
			System.out.println("   배틀에서 승리했다");
			System.out.printf("   보상으로 %d 코인 획득!\n", coin);
			System.out.println("└────────────────────────────┘");

			GameManager.battleNum--;
			GameManager.coin += coin;
			GameManager.ranHp += 3; // 배틀 승리로 몬스터 체력 상승
			GameManager.ranPower += 10; // 배틀 승리로 몬스터 파워 상승

			GameManager.nextStage = "LOBBY";
		}

		if (playerDead <= 0) {
			int coin = GameManager.rand.nextInt(100) + 50;
			int temp = GameManager.coin;
			GameManager.coin -= coin;
			if (GameManager.coin < 0) {
				coin = temp;
				GameManager.coin = 0;
			}

			allDead = true;
			try {
				System.out.println("┌────────────────────────────┐");
				Thread.sleep(500);
				System.out.println("   배틀에서 패배했다");
				Thread.sleep(500);
				System.out.printf("   %d 코인을 빼앗겼다!\n", coin);
				Thread.sleep(500);
				System.out.println("   눈 앞이 캄캄해졌다...!");
				Thread.sleep(500);
				System.out.println("   일단 아지트로 가자...");
				System.out.println("└────────────────────────────┘");
			} catch (Exception e) {
			}


			GameManager.nextStage = "HOUSE";
		}
		return false;
	}

	@Override
	public void init() {
		if (monsterList == null) {
			GameManager.unitManager.monster_rand_set(4);
			monsterList = null;
			monsterList = UnitManager.monster_list;
			monsterDead = monsterList.size();
			playerDead = GameManager.playerList.size();
		} else {
			for(int i=0; i<monsterList.size(); i++) {
				monsterList.get(i).setHp(monsterList.get(i).getMaxHp());
			}
		}
	}

	void print_character() {
		System.out.println("\n===== [PLAYER] =====");
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			GameManager.playerList.get(i).printData();
		}
		System.out.println("\n===== [MONSTER] =====");
		for (int i = 0; i < monsterList.size(); i++) {
			monsterList.get(i).printData();
		}
	}

	public void player_attack(int index) {
		Player player = GameManager.playerList.get(index);
		if (player.getHp() <= 0)
			return;
		System.out.println("┌──────────────┐");
		System.out.println("    🤴🏻" + player.getName() + "");
		System.out.println("└──────────────┘");
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 어택");
		System.out.println("    ❷ 스킬");
		System.out.println("    ❸ 가방");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int sel = GameManager.sc.nextInt();
		if (sel == ATTACK) {
			while (true) {
				int idx = GameManager.rand.nextInt(monsterList.size());

				if (monsterList.get(idx).getHp() > 0) {
					player.attack(monsterList.get(idx));
					break;
				}
			}
		} else if (sel == SKILL) {
			selectPlayer();
		} else if (sel == INVENTORY) {
			if (inventory()) {
				System.out.println("┌────────────────┐");
				System.out.println("  누구에게 줄까?");
				System.out.println("└────────────────┘");

				int idx = selectPlayer();
				GameManager.playerList.get(idx).setHp();
				int maxHp = GameManager.playerList.get(idx).getMaxHp();
				if (GameManager.playerList.get(idx).getHp() > maxHp)
					GameManager.playerList.get(idx).setHp(maxHp);

			}
		}
	}

	public void monster_attack(int index) {
		Unit monster = monsterList.get(index);
		if (monster.getHp() <= 0)
			return;
		while (true) {
			int idx = GameManager.rand.nextInt(GameManager.playerList.size());
			if (GameManager.playerList.get(idx).getHp() > 0) {
				monster.attack(GameManager.playerList.get(idx));
				break;
			}
		}
	}

	public void check_live() {
		int num = 0;
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			if (GameManager.playerList.get(i).getHp() <= 0)
				num++;
		}
		playerDead = GameManager.playerList.size() - num;

		num = 0;
		for (int i = 0; i < monsterList.size(); i++) {
			if (monsterList.get(i).getHp() <= 0)
				num++;
		}
		monsterDead = monsterList.size() - num;
	}

	public int selectPlayer() {
		System.out.println("┌──────────────┐");
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			System.out.printf("    %d) %s\n", i + 1, GameManager.playerList.get(i).getName());
		}
		System.out.println("└──────────────┘");
		int index = (GameManager.sc.nextInt()) - 1;
		return index;
	}

	public boolean inventory() {
		System.out.println();
		System.out.println("┌─────────────────────────────┐");
		System.out.println("  보유 물약: " + GameManager.potion + "개");
		System.out.println("  사용하시겠습니까? (y/n)");
		System.out.println("└─────────────────────────────┘");

		if (GameManager.sc.next().equals("y")) {
			return true;
		}

		return false;

	}

}
