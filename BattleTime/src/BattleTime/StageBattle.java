package BattleTime;

import java.util.ArrayList;

public class StageBattle extends Stage {

	private final int ATTACK = 1;
	private final int SKILL = 2;
	private final int INVENTORY = 3;

	private ArrayList<Unit> monsterList;
	int monsterDead = 0;
	int playerDead = 0;

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
		GameManager.nextStage = "LOBBY";
		return false;
	}

	@Override
	public void init() {
		GameManager.unitManager.monster_rand_set(4);
		monsterList = null;
		monsterList = UnitManager.monster_list;
		monsterDead = monsterList.size();
		playerDead = GameManager.playerList.size();
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
		System.out.println("     " + player.getName() + "");
		System.out.println("└──────────────┘");
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 어택");
		System.out.println("    ❷ 스킬");
		System.out.println("    ❸ 가방");
		System.out.println("└──────────────┘");
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
				int idx = selectPlayer();
				GameManager.playerList.get(idx).setHp();
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
