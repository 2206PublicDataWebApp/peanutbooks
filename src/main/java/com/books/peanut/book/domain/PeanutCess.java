package com.books.peanut.book.domain;

public class PeanutCess {
	
	//기본정보
	private String memberId;
	private String name;
	private int birthMonth;
	private int birthDay;
	
	//파라메터
	private int money;
	private int power;
	private int music;
	private int strong;
	private int cook;
	private int manner;
	private int art;
	private int study;
	private int stress;
	
	//아르바이트
	private int farm;
	private int cafe;
	private int child;
	
	//공부
	private int material;
	private int artschool;
	private int musicschool;
	
	//게임시스템
	private String ending;
	private int turn;
	private int age;
	
	
	
	
	

	public int getStress() {
		return stress;
	}
	public void setStress(int stress) {
		this.stress = stress;
	}
	public int getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	public int getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getMusic() {
		return music;
	}
	public void setMusic(int music) {
		this.music = music;
	}
	public int getStrong() {
		return strong;
	}
	public void setStrong(int strong) {
		this.strong = strong;
	}
	public int getCook() {
		return cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
	public int getManner() {
		return manner;
	}
	public void setManner(int manner) {
		this.manner = manner;
	}
	public int getArt() {
		return art;
	}
	public void setArt(int art) {
		this.art = art;
	}
	public int getStudy() {
		return study;
	}
	public void setStudy(int study) {
		this.study = study;
	}
	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}
	public int getCafe() {
		return cafe;
	}
	public void setCafe(int cafe) {
		this.cafe = cafe;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	public int getMaterial() {
		return material;
	}
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getArtschool() {
		return artschool;
	}
	public void setArtschool(int artschool) {
		this.artschool = artschool;
	}
	public int getMusicschool() {
		return musicschool;
	}
	public void setMusicschool(int musicschool) {
		this.musicschool = musicschool;
	}
	public String getEnding() {
		return ending;
	}
	public void setEnding(String ending) {
		this.ending = ending;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PeanutCess [memberId=" + memberId + ", name=" + name + ", birthMonth=" + birthMonth + ", birthDay="
				+ birthDay + ", money=" + money + ", power=" + power + ", music=" + music + ", strong=" + strong
				+ ", cook=" + cook + ", manner=" + manner + ", art=" + art + ", study=" + study + ", stress=" + stress
				+ ", farm=" + farm + ", cafe=" + cafe + ", child=" + child + ", material=" + material + ", artschool="
				+ artschool + ", musicschool=" + musicschool + ", ending=" + ending + ", turn=" + turn + ", age=" + age
				+ "]";
	}
	

	
	
}
