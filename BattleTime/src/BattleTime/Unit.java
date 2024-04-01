package BattleTime;

public abstract class Unit {
	private int hp;
	private int maxHp;
	private int power;
	private String name;
	
	private int[] hpBar;
	private final int MY_HP = 1;
	
	Unit(){}
	
	Unit(String name, int maxHp, int power){
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.hpBar = new int[hp/50];
	}
	
	void attack(Unit target) {
		target.hp -= power;
		System.out.printf("[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.\n", this.name, target.name, power);
		if(target.hp <= 0) {
			target.hp = 0;
			System.out.printf("[%s]를 처치했습니다.\n", target.name);
		}
	}
	
	void printData() {
		double temp = hp;
		temp = Math.round(temp / 50);
		for(int i=0; i<hpBar.length; i++) {
			hpBar[i] = 0;
		}

		for(int i=0; i<temp; i++) {
			hpBar[i] = MY_HP;
		}
		
		System.out.printf("[%s] ", this.name);
		
		for(int i=0; i<hpBar.length; i++) {
			if(hpBar[i] == MY_HP)
				System.out.println("■");
			else 
				System.out.println("□");
		}
		
		System.out.printf("[%d/%d]\n", this.hp, this.maxHp);
		
	}
	
}
