package BattleTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GameManager {	
	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();
	public static String nextStage = "";
	
	public static UnitManager unitManager = new UnitManager();
	public static ArrayList<Player> playerList;
	public static int potion;	// 길드가 보유한 포션
	public static int money;	// 길드가 보유한 돈
	
	private String curStage = "";
	private Map<String, Stage> stageList = new HashMap<String, Stage>();
	
	public GameManager() {
		playerList = null;
		playerList = UnitManager.player_list;
		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("VILLAGE", new StageVillage());
		stageList.put("STORE", new StageStore());
		
		potion = 0;
		money = 100;
		nextStage = "TITLE";
	}
	
	public boolean changeStage() {
		if(nextStage != "" && nextStage != "TITLE") {
			System.out.printf("»»———— [%s] ————««\n", nextStage);
		}
		
		if(curStage.equals(nextStage))
			return true;
		
		curStage = nextStage;
		Stage stage = stageList.get(curStage);
		
		boolean run = true;
		while(true) {
			run = stage.update();
			if(run == false)
				break;
		}
		
		if(nextStage.equals(""))
			return false;
		else
			return true;
	}
}
