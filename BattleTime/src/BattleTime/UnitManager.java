package BattleTime;

import java.util.ArrayList;

public class UnitManager {
	public static ArrayList<Unit> monster_list;
	public static ArrayList<Player> player_list;
	
	private String path = "BattleTime.Unit";
	private String monNames[] = { "Bat", "Orc", "Troll" };
	
	public UnitManager() {
		monster_list = new ArrayList<>();
		player_list = new ArrayList<>();
		
		player_list.add(new Player("전사", 1000, 100));
		player_list.add(new Player("마법사", 700, 80));
		player_list.add(new Player("힐러", 500, 50));
	}
	
	public void monster_rand_set(int size) {
		for(int i=0; i<size; i++) {
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
