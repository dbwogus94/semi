package com.semi.update.All.join.menuFood.dto;

public class MenuFoodDto {

	// dayMenu
	private int menuNo;
	private String id;
	private String menuDate;
	private String menuMorning;
	private String menuLunch;
	private String menuEven;
	private String menuSnack;

	// Food
	private int foodNo;
	private String foodName;
	private String foodGram;
	private String foodCar;
	private String foodPro;
	private String foodFat;
	private String foodCalorie;	
	
	public MenuFoodDto() {
		super();
	}

	public MenuFoodDto(int menuNo, String id, String menuDate, String menuMorning, String menuLunch, String menuEven,
			String menuSnack, int foodNo, String foodName, String foodGram, String foodCar, String foodPro,
			String foodFat, String foodCalorie) {
		super();
		this.menuNo = menuNo;
		this.id = id;
		this.menuDate = menuDate;
		this.menuMorning = menuMorning;
		this.menuLunch = menuLunch;
		this.menuEven = menuEven;
		this.menuSnack = menuSnack;
		this.foodNo = foodNo;
		this.foodName = foodName;
		this.foodGram = foodGram;
		this.foodCar = foodCar;
		this.foodPro = foodPro;
		this.foodFat = foodFat;
		this.foodCalorie = foodCalorie;
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

	public int getFoodNo() {
		return foodNo;
	}

	public void setFoodNo(int foodNo) {
		this.foodNo = foodNo;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodGram() {
		return foodGram;
	}

	public void setFoodGram(String foodGram) {
		this.foodGram = foodGram;
	}

	public String getFoodCar() {
		return foodCar;
	}

	public void setFoodCar(String foodCar) {
		this.foodCar = foodCar;
	}

	public String getFoodPro() {
		return foodPro;
	}

	public void setFoodPro(String foodPro) {
		this.foodPro = foodPro;
	}

	public String getFoodFat() {
		return foodFat;
	}

	public void setFoodFat(String foodFat) {
		this.foodFat = foodFat;
	}

	public String getFoodCalorie() {
		return foodCalorie;
	}

	public void setFoodCalorie(String foodCalorie) {
		this.foodCalorie = foodCalorie;
	}

}
