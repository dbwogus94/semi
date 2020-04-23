package com.semi.update.member.mentee.exercise.total.dto;

import java.util.Date;

public class TotalDto {
	// 번호
	private int totalNo;
	// 날짜
	private String totalDate;

	// 아이디(멘티)
	private String id;

	// 요일
	private String totalDayofweek;

	// 총 시간(분)
	private String totalTime;

	// 총 칼로리
	private double totalCalorie;

	// 체중
	private double totalWeight;

	public TotalDto() {
		super();
	}

	public TotalDto(int totalNo, String totalDate, String id, String totalDayofweek, String totalTime,
			double totalCalorie, double totalWeight) {
		super();
		this.totalNo = totalNo;
		this.totalDate = totalDate;
		this.id = id;
		this.totalDayofweek = totalDayofweek;
		this.totalTime = totalTime;
		this.totalCalorie = totalCalorie;
		this.totalWeight = totalWeight;
	}

	public int getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}

	public String getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotalDayofweek() {
		return totalDayofweek;
	}

	public void setTotalDayofweek(String totalDayofweek) {
		this.totalDayofweek = totalDayofweek;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public double getTotalCalorie() {
		return totalCalorie;
	}

	public void setTotalCalorie(double totalCalorie) {
		this.totalCalorie = totalCalorie;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	@Override
	public String toString() {
		return "TotalDto [totalNo=" + totalNo + ", totalDate=" + totalDate + ", id=" + id + ", totalDayofweek="
				+ totalDayofweek + ", totalTime=" + totalTime + ", totalCalorie=" + totalCalorie + ", totalWeight="
				+ totalWeight + "]";
	}

	
}