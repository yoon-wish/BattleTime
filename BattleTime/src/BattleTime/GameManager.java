package BattleTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	public static final String ANSI_BOLD = "\u001B[1m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_GREEN = "\u001B[32m";

	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();
	public static String nextStage = "";

	public static UnitManager unitManager;
	public static FileManager fileManager;
	public static ArrayList<Player> playerList;
	public static ArrayList<Unit> monsterList;

	public static int day; // 날짜
	public static int potion; // 길드가 보유한 포션
	public static int coin; // 길드가 보유한 돈
	public static int battleNum; // 하루 배틀 횟수 제한
	public static int ranHp; // 몬스터 Hp
	public static int ranPower; // 몬스터 power
	public static int maxSp; // 플레이어 최대 스킬포인트

	private Map<String, Stage> stageList = new HashMap<String, Stage>();

	public GameManager() {
		battleNum = 1;

		// 기본 값 부여
		ranHp = 3;
		ranPower = 60;
		day = 1;
		coin = 100;
		maxSp = 1;

		unitManager = new UnitManager();
		fileManager = new FileManager();

		stageList.put("TITLE", new StageTitle());
		stageList.put("BATTLE", new StageBattle());
		stageList.put("LOBBY", new StageLobby());
		stageList.put("VILLAGE", new StageVillage());
		stageList.put("STORE", new StageStore());
		stageList.put("HOUSE", new StageHouse());

		nextStage = "TITLE";

		setGame();
	}

	private void setGame() {
		// 날짜/포션/코인/몬스터Hp/몬스터Power/플레이어sp
		// 플레이어1/플레이어2/플레이어3
		playerList = null;
		String info = fileManager.load();
		if (info == "") {
			setPlayer();
			return;
		}

		String[] allInfo = info.split("\n");
		String[] gameInfo = allInfo[0].split("/");

		day = Integer.parseInt(gameInfo[0]);
		potion = Integer.parseInt(gameInfo[1]);
		coin = Integer.parseInt(gameInfo[2]);
		ranHp = Integer.parseInt(gameInfo[3]);
		ranPower = Integer.parseInt(gameInfo[4]);
		maxSp = Integer.parseInt(gameInfo[5].trim());

		setPlayer(allInfo[1]);
	}

	private void setPlayer() {
		unitManager.setPlayer();
		playerList = UnitManager.player_list;
	}

	private void setPlayer(String info) {
		String[] playerInfo = info.split("/");

		int[] player = new int[playerInfo.length];
		for (int i = 0; i < playerInfo.length; i++) {
			player[i] = Integer.parseInt(playerInfo[i]);
		}

		unitManager.loadPlayer(player);
		playerList = UnitManager.player_list;
	}

	public boolean changeStage() {
		if (nextStage != "" && nextStage != "TITLE") {
			System.out.printf("»»——— [%s] ———««\n", nextStage);
		}

		Stage stage = stageList.get(nextStage);

		boolean run = true;
		while (true) {
			run = stage.update();
			if (run == false)
				break;
		}

		if (nextStage.equals(""))
			return false;
		else
			return true;
	}
}
