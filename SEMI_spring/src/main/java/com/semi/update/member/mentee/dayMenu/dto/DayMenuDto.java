package com.semi.update.member.mentee.dayMenu.dto;

import java.util.Date;

public class DayMenuDto {

       // 시퀀스 
       private int menuNo;

       // 아이디(멘티) 
       private String id;

       // 날짜 
       private String menuDate;

       // 음식고유번호 
       private String foodName;
       
       // 음식 그램
       private int menuGram;

       // 음식이름(아침) 
       private String menuMorning;

       // 음식이름(점심) 
       private String menuLunch;

       // 음식이름(저녁) 
       private String menuEven;

       // 음식이름(간식) 
       private String menuSnack;

       public DayMenuDto() {
         super();
      }

	public DayMenuDto(int menuNo, String id, String menuDate, String foodName, int menuGram, String menuMorning,
			String menuLunch, String menuEven, String menuSnack) {
		super();
		this.menuNo = menuNo;
		this.id = id;
		this.menuDate = menuDate;
		this.foodName = foodName;
		this.menuGram = menuGram;
		this.menuMorning = menuMorning;
		this.menuLunch = menuLunch;
		this.menuEven = menuEven;
		this.menuSnack = menuSnack;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuDate() {
		return menuDate;
	}

	public void setMenuDate(String menuDate) {
		this.menuDate = menuDate;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getMenuGram() {
		return menuGram;
	}

	public void setMenuGram(int menuGram) {
		this.menuGram = menuGram;
	}

	public String getMenuMorning() {
		return menuMorning;
	}

	public void setMenuMorning(String menuMorning) {
		this.menuMorning = menuMorning;
	}

	public String getMenuLunch() {
		return menuLunch;
	}

	public void setMenuLunch(String menuLunch) {
		this.menuLunch = menuLunch;
	}

	public String getMenuEven() {
		return menuEven;
	}

	public void setMenuEven(String menuEven) {
		this.menuEven = menuEven;
	}

	public String getMenuSnack() {
		return menuSnack;
	}

	public void setMenuSnack(String menuSnack) {
		this.menuSnack = menuSnack;
	}

    
       
}