package com.semi.update.All;

import java.util.Calendar;

public class Exercise {

	public int StrengthTraining(int time) {
	// 웨이트 운동 칼로리 계산
		
		return time * 36 / 10;
	}
	
	
	public int AerobicExercise(String Board_Exercise_Name, double weight, int time) {
	// 유산소 운동 칼로리 계산
		
		if(Board_Exercise_Name == "산책") {
			if(weight >= 90) {
				return time * 38 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 34 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 30 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 26 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 22 / 10;
			} else {
				return time * 18 / 10;
			}
		} else if(Board_Exercise_Name == "조깅") {
			if(weight >= 90) {
				return time * 141 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 125 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 110 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 94 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 79 / 10;
			} else {
				return time * 64 / 10;
			}
		} else if(Board_Exercise_Name == "자전거타기(천천히)") {
			if(weight >= 90) {
				return time * 57 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 50 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 43 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 34 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 31 / 10;
			} else {
				return time * 26 / 10;
			}
		} else if(Board_Exercise_Name == "자전거타기(빠르게)") {
			if(weight >= 90) {
				return time * 66 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 59 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 52 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 44 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 37 / 10;
			} else {
				return time * 31 / 10;
			}
		} else if(Board_Exercise_Name == "스트레칭체조") {
			if(weight >= 90) {
				return time * 38 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 33 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 29 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 25 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 21 / 10;
			} else {
				return time * 17 / 10;
			}
		} else if(Board_Exercise_Name == "춤추기") {
			if(weight >= 90) {
				return time * 62 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 55 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 48 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 41 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 34 / 10;
			} else {
				return time * 27 / 10;
			}
		} else if(Board_Exercise_Name == "요가") {
			if(weight >= 90) {
				return time * 37 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 34 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 29 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 25 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 21 / 10;
			} else {
				return time * 17 / 10;
			}
		} else if(Board_Exercise_Name == "에어로빅") {
			if(weight >= 90) {
				return time * 76 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 67 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 59 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 52 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 42 / 10;
			} else {
				return time * 33 / 10;
			}
		} else if(Board_Exercise_Name == "계단오르기") {
			if(weight >= 90) {
				return time * 89 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 78 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 68 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 58 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 48 / 10;
			} else {
				return time * 37 / 10;
			}
		} else if(Board_Exercise_Name == "수영(자유형)") {
			if(weight >= 90) {
				return time * 262 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 234 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 204 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 174 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 145 / 10;
			} else {
				return time * 116 / 10;
			}
		} else if(Board_Exercise_Name == "수영(접형)") {
			if(weight >= 90) {
				return time * 329 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 293 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 258 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 220 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 184 / 10;
			} else {
				return time * 150 / 10;
			}
		} else {
			if(weight >= 90) {
				return time * 135 / 10;
			}else if(weight < 90 && weight >= 80) {
				return time * 120 / 10;
			} else if(weight < 80 && weight >= 70) {
				return time * 104 / 10;
			} else if(weight < 70 && weight >= 60){
				return time * 89 / 10;
			}  else if(weight < 60 && weight >= 50){
				return time * 75 / 10;
			} else {
				return time * 60 / 10;
			}
		}
	}
	
	public double BMR(String check, double heigth, double weight, String BirthYear) {
		// check : 성별, heigth : 키, weight : 몸무게, BirthYear : 태어난 년도
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age =  year - Integer.parseInt(BirthYear) ;
		if(check.equals("M")) {
			return 66.47 + (13.75 * heigth) + (5 * weight) - (6.76 * age);
		} else {
			return 655.1 + (9.56 * heigth) + (1.85 * weight) - (4.68 * age);
		}
	}
	
	public double BMI(double heigth, double weight) {
		// heigth : 키, weight : 몸무게
		return weight / ((double)(heigth / 100) * (double)(heigth / 100));
	}
	
	public double totalKal(double BMR, int ActivityIndex) {
		// BMR : 기초대사량, ActivityIndex : 활동지수
		switch(ActivityIndex) {
		case 1:
			return BMR + ((double)BMR * 0.1);
		case 2:
			return BMR + ((double)BMR * 0.3);
		case 3:
			return BMR + ((double)BMR * 0.4);
		default :
			return BMR + ((double)BMR * 1);
		}
	}
}
