package com.semi.update.member.board.dto;

import java.util.Date;

public class BoardDto {
	// 게시판 기능들 

	public BoardDto() {
		
	}
	
	public BoardDto(int board_Category_No) {
		this.board_Category_No = board_Category_No;
	}
	
	public BoardDto(String id, String board_Member_Name) {
		this.id = id;
		this.board_Member_Name = board_Member_Name;
	}
	
	public BoardDto(int board_Category_No, String board_Content) {
		this.board_Category_No = board_Category_No;
		this.board_Content = board_Content;
	}
	
	public BoardDto(int board_Group, String id, String board_Member_Name, String board_Content) {
		this.board_Group = board_Group;
		this.id = id;
		this.board_Member_Name = board_Member_Name;
		this.board_Content = board_Content;
	}
	
	public BoardDto(String id, String board_Member_Name, String board_Title, String board_Content) {
		this.id = id;
		this.board_Member_Name = board_Member_Name;
		this.board_Title = board_Title;
		this.board_Content = board_Content;
	}
	
	public BoardDto(int board_Category_No, String id, String board_Member_Name, String board_Title, String board_Content) {
		this.board_Category_No = board_Category_No;
		this.id = id;
		this.board_Member_Name = board_Member_Name;
		this.board_Title = board_Title;
		this.board_Content = board_Content;
	}
	
	public BoardDto(int board_No, String board_Category, int board_Category_No, int board_Group, int board_Group_No, int board_Tab, String id, String board_Member_Name, String board_Exercise_Name, String board_Title, String board_Content, Date board_Regdate, int board_Review_Score, String board_Exercise_Category, String board_Exercise_Url) {
		this.board_No = board_No;
		this.board_Category = board_Category;
		this.board_Category_No = board_Category_No;
		this.board_Group = board_Group;
		this.board_Group_No = board_Group_No;
		this.board_Tab = board_Tab;
		this.id = id;
		this.board_Member_Name = board_Member_Name;
		this.board_Exercise_Name = board_Exercise_Name;
		this.board_Title = board_Title;
		this.board_Content = board_Content;
		this.board_Regdate = board_Regdate;
		this.board_Review_Score = board_Review_Score;
		this.board_Exercise_Category = board_Exercise_Category;
		this.board_Exercise_Url = board_Exercise_Url;
	}
	    // 전체 글번호 
	    private int board_No;

	    // 구분(게시판, 리뷰, 정보) 
	    private String board_Category;

	    // 속성별 글번호 
	    private int board_Category_No;

	    // 그룹번호(게시판) 
	    private int board_Group;

	    // 그룹의 글순서(게시판) 
	    private int board_Group_No;

	    // 탭 
	    private int board_Tab;

	    // 아이디(게시판,리뷰) 
	    private String id;

	    // 이름(게시판,리뷰) 
	    private String board_Member_Name;

	    // 운동이름 
	    private String board_Exercise_Name;

	    // 제목(게시판,리뷰) 
	    private String board_Title;

	    // 내용(게시판,리뷰.정보)) 
	    private String board_Content;

	    // 작성일(게시판, 리뷰)) 
	    private Date board_Regdate;

	    // 별점(리뷰) 
	    private int board_Review_Score;

	    // 카테고리 
	    private String board_Exercise_Category;

	    // URL 
	    private String board_Exercise_Url;
	    
	    
	  //페이징 번호
	    private int board_Paging_Number;
	    
	    
	    
	    public int getBoard_Paging_Number() {
			return board_Paging_Number;
		}

		public void setBoard_Paging_Number(int board_Paging_Number) {
			this.board_Paging_Number = board_Paging_Number;
		}

		
		
	    
	    
	    
	    public int getBoard_No() {
	        return board_No;
	    }

	    public void setBoard_No(int board_No) {
	        this.board_No = board_No;
	    }

	    public String getBoard_Category() {
	        return board_Category;
	    }

	    public void setBoardCategory(String board_Category) {
	        this.board_Category = board_Category;
	    }

	    public int getBoard_Category_No() {
	        return board_Category_No;
	    }

	    public void setBoard_Category_No(int board_Category_No) {
	        this.board_Category_No = board_Category_No;
	    }

	    public int getBoard_Group() {
	        return board_Group;
	    }

	    public void setBoardGroup(int board_Group) {
	        this.board_Group = board_Group;
	    }

	    public int getBoard_Group_No() {
	        return board_Group_No;
	    }

	    public void setBoard_Group_No(int board_Group_No) {
	        this.board_Group_No = board_Group_No;
	    }

	    public int getBoard_Tab() {
	        return board_Tab;
	    }

	    public void setBoard_Tab(int board_Tab) {
	        this.board_Tab = board_Tab;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getBoard_Member_Name() {
	        return board_Member_Name;
	    }

	    public void setBoard_Member_Name(String board_Member_Name) {
	        this.board_Member_Name = board_Member_Name;
	    }

	    public String getBoard_Exercise_Name() {
	        return board_Exercise_Name;
	    }

	    public void setBoard_Exercise_Name(String board_Exercise_Name) {
	        this.board_Exercise_Name = board_Exercise_Name;
	    }

	    public String getBoard_Title() {
	        return board_Title;
	    }

	    public void setBoard_Title(String board_Title) {
	        this.board_Title = board_Title;
	    }

	    public String getBoard_Content() {
	        return board_Content;
	    }

	    public void setBoard_Content(String board_Content) {
	        this.board_Content = board_Content;
	    }

	    public Date getBoard_Regdate() {
	        return board_Regdate;
	    }

	    public void setBoard_Regdate(Date board_Regdate) {
	        this.board_Regdate = board_Regdate;
	    }

	    public int getBoard_Review_Score() {
	        return board_Review_Score;
	    }

	    public void setBoard_Review_Score(int board_Review_Score) {
	        this.board_Review_Score = board_Review_Score;
	    }

	    public String getBoard_Exercise_Category() {
	        return board_Exercise_Category;
	    }

	    public void setBoard_Exercise_Category(String board_Exercise_Category) {
	        this.board_Exercise_Category = board_Exercise_Category;
	    }

	    public String getBoard_Exercise_Url() {
	        return board_Exercise_Url;
	    }

	    public void setBoard_Exercise_Url(String board_Exercise_Url) {
	        this.board_Exercise_Url = board_Exercise_Url;
	    }

		@Override
		public String toString() {
			return "BoardDto [board_No=" + board_No + ", board_Category=" + board_Category + ", board_Category_No="
					+ board_Category_No + ", board_Group=" + board_Group + ", board_Group_No=" + board_Group_No
					+ ", board_Tab=" + board_Tab + ", id=" + id + ", board_Member_Name=" + board_Member_Name
					+ ", board_Exercise_Name=" + board_Exercise_Name + ", board_Title=" + board_Title
					+ ", board_Content=" + board_Content + ", board_Regdate=" + board_Regdate + ", board_Review_Score="
					+ board_Review_Score + ", board_Exercise_Category=" + board_Exercise_Category
					+ ", board_Exercise_Url=" + board_Exercise_Url + "]";
		}

		
}
