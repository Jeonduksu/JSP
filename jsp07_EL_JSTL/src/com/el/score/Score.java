package com.el.score;

public class Score {
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private double avg;
	private String grade;
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Score(String name, int kor, int eng, int math) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		setSum();
		setAvg();
		setGrade();
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return sum;
	}
	public void setSum() {
		this.sum = this.kor + this.eng+ this.math;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg() {
		this.avg = (double)sum/3.0;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade() {
		switch((int)avg / 10) {
		case 10:
		case 9 :
			grade = "A";
			break;
		case 8:
		case 7:
			grade = "B";
			break;
		case 6:
		case 5:
			grade = "F";
			break;
		}
	}
	
	
}