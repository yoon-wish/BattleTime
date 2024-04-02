package BattleTime;

public abstract class Unit {
	private int hp;
	private int maxHp;
	private int power;
	private String name;
	
	private int[] hpBar;
	private final int MY_HP = 1;
	
	public Unit(){}
	
	public Unit(String name, int maxHp, int power){
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.hpBar = new int[hp/50];
	}
	
	public void init(int maxHp, int power) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.power = power;
		this.hpBar = new int[hp/50];
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getPower() {
		return this.power;
	}
	
	// 포션 섭취시
	public void setHp() {
		this.hp += 200; 
	}
	
	public void setHp(int hp) {
		this.hp = hp; 
	}
	
	public void attack(Unit target) {
		target.hp -= power;
		System.out.printf("🧨[%s](이)가 [%s]에게 %d의 데미지를 입힙니다.", this.name, target.name, power);
		if(target.hp <= 0) {
			target.hp = 0;
			System.out.printf("[%s]를 처치했습니다.\n", target.name);
		}
	}
	
	
	
	public void printData() {
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
				System.out.print("■");
			else 
				System.out.print("□");
		}
		
		System.out.printf("[%d/%d]\n", this.hp, this.maxHp);
		
	}
	
}
