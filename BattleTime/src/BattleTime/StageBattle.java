package BattleTime;

public class StageBattle extends Stage {

	private final int ATTACK = 1;
	private final int SKILL = 2;
	private final int INVENTORY = 3;

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
				if (monster_idx < GameManager.monsterList.size()) {
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
			monsterDead();
			GameManager.nextStage = "LOBBY";
		}

		if (playerDead <= 0) {
			playerDead();
			GameManager.nextStage = "HOUSE";
		}
		
		return false;
	}

	@Override
	public void init() {
		if (GameManager.monsterList == null) {
			GameManager.unitManager.monster_rand_set(4);
			GameManager.monsterList = null;
			GameManager.monsterList = UnitManager.monster_list;
			monsterDead = GameManager.monsterList.size();
			playerDead = GameManager.playerList.size();
		} else {
			for(int i=0; i<GameManager.monsterList.size(); i++) {
				Unit monster = GameManager.monsterList.get(i);
				monster.setHp(monster.getMaxHp());
			}
		}
	}
	
	private void monsterDead() {
		int coin = GameManager.rand.nextInt(100) + 200;
		System.out.println("┌────────────────────────────┐");
		System.out.println("   배틀에서 승리했다");
		System.out.printf("   보상으로 %d 코인 획득!\n", coin);
		System.out.println("└────────────────────────────┘");

		GameManager.battleNum--;
		GameManager.coin += coin;
		GameManager.ranHp += 3; // 배틀 승리로 몬스터 체력 상승
		GameManager.ranPower += 10; // 배틀 승리로 몬스터 파워 상승
	}
	
	private void playerDead() {
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
	}

	private void print_character() {
		System.out.println("\n===== [PLAYER] =====");
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			GameManager.playerList.get(i).printData();
		}
		System.out.println("\n===== [MONSTER] =====");
		for (int i = 0; i < GameManager.monsterList.size(); i++) {
			GameManager.monsterList.get(i).printData();
		}
	}

	private void givePotion() {
		System.out.println("┌────────────────┐");
		System.out.println("  누구에게 줄까?");
		System.out.println("└────────────────┘");

		int playerIdx = selectPlayer();
		System.out.println(GameManager.playerList.size());
		while (playerIdx < 0 || playerIdx >= GameManager.playerList.size()) {
			playerIdx = selectPlayer();
		}
		
		Player healPlayer = GameManager.playerList.get(playerIdx);
		healPlayer.setHp();
		int maxHp = healPlayer.getMaxHp();
		if (healPlayer.getHp() > maxHp)
			healPlayer.setHp(maxHp);
	}
	
	private void printPlayer(Player player) {
		System.out.println("┌──────────────┐");
		System.out.println("    🤴🏻" + player.getName() + "");
		System.out.println("└──────────────┘");
		System.out.println("┌──────────────┐");
		System.out.println("    ❶ 어택");
		System.out.println("    ❷ 스킬");
		System.out.println("    ❸ 가방");
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
	}
	
	public void player_attack(int index) {
		Player player = GameManager.playerList.get(index);
		if (player.getHp() <= 0)
			return;

		printPlayer(player);
		
		int size = GameManager.monsterList.size();
		int sel = GameManager.sc.nextInt();
		int idx = GameManager.rand.nextInt(size);
		Unit monster = GameManager.monsterList.get(idx);
		if (sel == ATTACK) {
			while (true) {
				idx = GameManager.rand.nextInt(size);
				monster = GameManager.monsterList.get(idx);
				if (monster.getHp() > 0) {
					player.attack(monster);
					break;
				}
			}
		} else if (sel == SKILL) {
			player.skill(monster);
		} else if (sel == INVENTORY) {
			if (inventory()) {
				givePotion();
			}
		}
	}

	public void monster_attack(int index) {
		Unit monster = GameManager.monsterList.get(index);
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
		int size = GameManager.monsterList.size();
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			if (GameManager.playerList.get(i).getHp() <= 0)
				num++;
		}
		playerDead = GameManager.playerList.size() - num;

		num = 0;
		for (int i = 0; i < size; i++) {
			if (GameManager.monsterList.get(i).getHp() <= 0)
				num++;
		}
		monsterDead = size - num;
	}

	public int selectPlayer() {
		System.out.println("┌──────────────┐");
		for (int i = 0; i < GameManager.playerList.size(); i++) {
			System.out.printf("    %d) %s\n", i + 1, GameManager.playerList.get(i).getName());
		}
		System.out.println("└──────────────┘");
		System.out.print("👉 ");
		int index = (GameManager.sc.nextInt()) - 1;
		
		return index;
	}

	public boolean inventory() {
		System.out.println();
		
		if(GameManager.potion == 0) {
			System.out.println("┌──────┐");
			System.out.println("  텅 - ");
			System.out.println("└──────┘");
			return false;
		}
		
		System.out.println("┌─────────────────────────────┐");
		System.out.println("  보유 물약: " + GameManager.potion + "개");
		System.out.println("  사용하시겠습니까? (y/n)");
		System.out.println("└─────────────────────────────┘");

		System.out.print("👉 ");
		if (GameManager.sc.next().equals("y")) {
			return true;
		}

		return false;

	}

}
