package com.semi.update.member.mentee.food.dto;

public class FoodDto {

	    // 음식고유번호 
	    private int foodNo;

	    // 음식이름 
	    private String foodName;

	    // 그램(1g) 
	    private String foodGram;

	    // 탄수화물 
	    private String foodCar;

	    // 단백질 
	    private String foodPro;

	    // 지방 
	    private String foodFat;

	    // 칼로리 
	    private String foodCalorie;
	    

	    public FoodDto() {
			super();
		}

		public FoodDto(int foodNo, String foodName, String foodGram, String foodCar, String foodPro, String foodFat,
				String foodCalorie) {
			super();
			this.foodNo = foodNo;
			this.foodName = foodName;
			this.foodGram = foodGram;
			this.foodCar = foodCar;
			this.foodPro = foodPro;
			this.foodFat = foodFat;
			this.foodCalorie = foodCalorie;
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

		@Override
		public String toString() {
			return "Food [foodNo=" + foodNo + ", foodName=" + foodName + ", foodGram=" + foodGram + ", foodCar="
					+ foodCar + ", foodPro=" + foodPro + ", foodFat=" + foodFat + ", foodCalorie=" + foodCalorie + "]";
		}

	    // Food 모델 복사
	    
	}
