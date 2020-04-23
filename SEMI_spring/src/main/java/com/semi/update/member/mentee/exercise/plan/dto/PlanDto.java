package com.semi.update.member.mentee.exercise.plan.dto;

import java.util.Date;

public class PlanDto {

	// 운동 계획 시퀀스
	private int planNo;

	// 날짜
	private String planDate;

	// 아이디(멘티)
	private String id;

	// 카테고리
	private String planCategory;

	// 운동이름
	private String exerciseName;

	// 횟수(근력)
	private int planCount;

	// 시간(분, 유산소)
	private int planTime;

	// 요일
	private String planDayofweek;

	// 플랜 or 수행
	private String planKinds;

	public PlanDto() {
		super();
	}

	public PlanDto(int planNo, String planDate, String id, String planCategory, String exerciseName, int planCount,
			int planTime, String planDayofweek, String planKinds) {
		super();
		this.planNo = planNo;
		this.planDate = planDate;
		this.id = id;
		this.planCategory = planCategory;
		this.exerciseName = exerciseName;
		this.planCount = planCount;
		this.planTime = planTime;
		this.planDayofweek = planDayofweek;
		this.planKinds = planKinds;
	}

	public int getPlanNo() {
		return planNo;
	}

	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanCategory() {
		return planCategory;
	}

	public void setPlanCategory(String planCategory) {
		this.planCategory = planCategory;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getPlanCount() {
		return planCount;
	}

	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}

	public int getPlanTime() {
		return planTime;
	}

	public void setPlanTime(int planTime) {
		this.planTime = planTime;
	}

	public String getPlanDayofweek() {
		return planDayofweek;
	}

	public void setPlanDayofweek(String planDayofweek) {
		this.planDayofweek = planDayofweek;
	}

	public String getPlanKinds() {
		return planKinds;
	}

	public void setPlanKinds(String planKinds) {
		this.planKinds = planKinds;
	}

}
