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
	public static FileManager fileManager = new FileManager();
	public static ArrayList<Player> playerList;
	
	public static int day;			// 날짜
	public static int potion;		// 길드가 보유한 포션
	public static int coin;			// 길드가 보유한 돈
	public static int battleNum;	// 하루 배틀 횟수 제한
	public static int ranHp;		// 몬스터 Hp
	public static int ranPower;		// 몬스터 power
	
	private Map<String, Stage> stageList = new HashMap<String, Stage>();
	
	public GameManager() {
		playerList = null;
		playerList = UnitManager.player_list;
		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("VILLAGE", new StageVillage());
		stageList.put("STORE", new StageStore());
		stageList.put("HOUSE", new StageHouse());
		
		battleNum = 1;
		
		// 기본 값 부여
		ranHp = 8;
//		ranPower = 60;
		ranPower = 500;
		day = 1;
		coin = 100;
		
		nextStage = "TITLE";

		setGame();
	}
	
	private void setGame() {
		// 날짜/포션/코인/몬스터Hp
		String info = fileManager.load();
		if(info == "") {
			return;
		}
		
		String[] allInfo = info.split("/");

		day = Integer.parseInt(allInfo[0]);
		potion = Integer.parseInt(allInfo[1]);
		coin = Integer.parseInt(allInfo[2]);
		ranHp = Integer.parseInt(allInfo[3].trim());
	}
	
	public boolean changeStage() {
		if(nextStage != "" && nextStage != "TITLE") {
			System.out.printf("»»——— [%s] ———««\n", nextStage);
		}
		
		Stage stage = stageList.get(nextStage);
		
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
