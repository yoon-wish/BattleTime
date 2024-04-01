package BattleTime;

import java.util.ArrayList;

public class UnitManager {
	private ArrayList<Unit> monster_list;
	public static ArrayList<Player> player_list;
	
	private String path = "BattleTime.";
	private String monNames[] = { "Bat", "Dracula", "Orc" };
	
	public UnitManager() {
		monster_list = new ArrayList<>();
		player_list = new ArrayList<>();
		
		player_list.add(new Player("전사", 1000, 45));
		player_list.add(new Player("마법사", 800, 60));
		player_list.add(new Player("힐러", 500, 70));
	}
	
	public void monster_rand_set(int size) {
		for(int i=0; i<size; i++) {
			int num = GameManager.rand.nextInt(monNames.length);
			try {
				Class<?> clazz = Class.forName(path + monNames[num]);
				Object obj = clazz.getDeclaredConstructor().newInstance();
				
				Unit temp = (Unit) obj;
				int hp = GameManager.rand.nextInt(100) + 100;
				int power = GameManager.rand.nextInt(10) + 10;
				
				temp.init(hp, power);
				monster_list.add(temp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
