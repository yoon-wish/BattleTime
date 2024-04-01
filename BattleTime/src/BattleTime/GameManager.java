package BattleTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GameManager {	
	public static Scanner sc = new Scanner(System.in);
	public static String nextStage = "";
	
	private String curStage = "";
	private Map<String, Stage> stageList = new HashMap<String, Stage>();
	
	public GameManager() {
		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("VILLAGE", new StageVillage());
		stageList.put("STORE", new StageVillage());
		
		nextStage = "TITLE";
	}
	
	public boolean changeStage() {
		if(curStage != "" && curStage != "TITLE") {
			System.out.printf("===== [%s] =====\n", curStage);
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
