package com.semi.update.All.pagination;

public class OraclePagination {
	
	// 현재 페이지
	private int currentPage;	
	
	// 한 페이지에 출력될 게시물 수
	private int boardListSize = 6;	
	
	// 한 화면에 출력될 페이지 수 == 출력될 페이지 크기(개수)
	private int pageSize = 10;
	
	// 총 게시물 수(DB에서 조회)
	private int totalBoard;
	
	// 총 페이지 수
	private int totalPage;
	
	// 시작 페이지
	private int startPage;
	
	// 끝 페이지
	private int endPage;
	
	// 현재 페이지 시작 게시물 번호(쿼리에 사용)
	private int startBoardNo;
	
	// 현재 페이지 끝 게시물 번호(쿼리에 사용)
	private int endBoardNo; 	
	
	// 이전 페이지
	private int prevPage;

	// 다음 페이지
	private int nextPage;
	
	public OraclePagination() {
		
	}
	
	/*---------- 페이징 설정 생성자 ----------*/
	// 총 게시글 수(DB에서), 현재 페이지(view에서)
	public OraclePagination(int totalBoard, int currentPage) {
		// 현재페이지 
		setCurrentPage(currentPage);
		
		// 1. 총 게시물 수  	: DB에서 조회하여 파라미터로 전달 
		setTotalBoard(totalBoard);
		
		// 2. 총 페이지 수 
		setTotalPage(totalBoard);
		
		// 3. 시작페이지, 끝 페이지
		setStartEndPage(currentPage);

		// 4. 현재 페이지 시작게시물 번호(boardDto에 넣어서 메퍼에 사용)
		setStartBoardNo(currentPage);
		
		// 5. 현재 페이지 끝게시물 번호(boardDto에 넣어서 메퍼에 사용)
		setEndBoardNo(currentPage);
		
		// 6. 이전페이지 다음페이지 설정
		setPrevPage(currentPage - 1);
		setNextPage(currentPage + 1);
	}

	// 기본생성자로 생성후 >>> setter로 세팅후 >> 페이징 연산에 사용
	public void calcPaging(int totalBoard, int currentPage) {
		// 현재페이지 
		setCurrentPage(currentPage);
				
		// 1. 총 게시물 수  	: DB에서 조회하여 파라미터로 전달 
		setTotalBoard(totalBoard);
				
		// 2. 총 페이지 수 
		setTotalPage(totalBoard);
				
		// 3. 시작페이지, 끝 페이지
		setStartEndPage(currentPage);

		// 4. 현재 페이지 시작게시물 번호(boardDto에 넣어서 메퍼에 사용)
		setStartBoardNo(currentPage);
				
		// 5. 현재 페이지 끝게시물 번호(boardDto에 넣어서 메퍼에 사용)
		setEndBoardNo(currentPage);
				
		// 6. 이전페이지 다음페이지 설정
		setPrevPage(currentPage - 1);
		setNextPage(currentPage + 1);		
	}
	
	
	public int getTotalBoard() {
		return totalBoard;
	}
	
	/* 1. 총 게시물 수  설정*/
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	/* 2. 총 페이지 수  설정 */
	public void setTotalPage(int totalBoard) {
		this.totalPage = (int) Math.ceil(totalBoard * 1.0 / boardListSize);	// Math.ceil() 소수점 올림
		
	/*
	  	# 총 페이지  >>> 총 게시물 / 화면에 보여줄 게시물 수 = 나머지 있으면++
		this.totalPage = totalBoard / boardListSize;
		
		// 나머지가 있다면 == boardListSize 나온면 
		if(totalBoard % countList > 0) {
			this.totalPage++;
		}
	*/
	}
	
	/* 3. 시작페이지 끝페이지 설정 */
	public void setStartEndPage(int currentPage) {
		int totalPage = this.totalPage;		// 2. 에서 설정한 전체 페이지 수 가져온다

//		// 한 화면에 출력되는 페이지 5개 기준
//		// 현재페이지가 4페이지 보다 작을 때	>>>  현재페이지  < (pageSize/2 + 2)	or	현재페이지  <= (pageSize/2 + 1) 
//		if(currentPage < 4) {
//			this.startPage = 1;
//			// 끝페이지 = 전체가 5 미만 / 5이상 
//			this.endPage = (totalPage < 5)? totalPage : 5;
//		// 현재 페이지가 (전체페이지 -
//		} 
		
		// 한 화면에 출력되는 페이지 10개 기준
		//현재페이지가 7 미만
		if(currentPage < 7) {
			this.startPage = 1;
			// 끝페이지 = 전체가 10  미만 일때  / 10 이상 일때
			this.endPage = (totalPage < 10)? totalPage : 10;
		// 현재 페이지가 (전체페이지-4)보다 크고 7페이지 이상이라면 
		} else if((currentPage < (totalPage - 4)) && (7 <= currentPage)) {
			this.startPage = currentPage - 5;
			this.endPage = currentPage + 4;
		// 현재 페이지가 (전체페이지 - 4) 이상이라면	
		} else if(currentPage >= (totalPage - 4)) {
			// 시작 페이지 = 전체가 10 미만 일때	/ 10이상 일때
			this.startPage = (totalPage < 10)? 1 : totalPage - 9;
			this.endPage = totalPage;
		}
		
		// 페이지 보정(끝 페이지가 총 페이지 보다 크다면?)
		if(getEndPage() > totalPage) {
			setEndPage(totalPage);
		}
		
		/*
		 * 예시
			# 시작 ~ 6번째 페이지까지
			<< (1) 2 3 4 5 6 7 8 9 10 ... N >>
			- 시작페이지 = 1
			- 끝 페이지
			if(N > 10)
				 끝 페이지  = 10 ... N
			else if(N <= 10)  	 
				끝 페이지 = 총 페이지(N : 1 ~ 10)
				
			# 현재페이지가 (N-4)미만 7페이지 이상부터는(현재 버튼 기준으로 뒤로 5개, 앞으로 4개)
			-  시작 페이지 = (현재페이지 -5)
			-  끝 페이지 = (현재페이지 + 4)
			예시)
				if(현재페이지+4 < N && 7페이지 이상)
					<< 1 ... 2 3 4 5 6 (7) 8 9 10 11 ... N >>
				else if(현재페이지  >= (N-4) && 7페이지 이상)
					<< 1 ... 2 3 4 5 6 (7) >>
					<< 1 ... 2 3 4 5 6 (7) 8 >>
					<< 1 ... 2 3 4 5 6 (7) 8 9 >>
					<< 1 ... 2 3 4 5 6 (7) 8 9 10 >>
					<< 1 ... 2 3 4 5 6 (7) 8 9 10 11 >>
			
			# 현재 페이지가 N부터 (N-4)번째 페이지 이상이라면
			- 시작 페이지  = N - 10
			- 끝 페이지 = N
			<< 1 ... N-10 N-8  N-7 N-6 N-5 (N-4) N-3 N-2 N-1 N >>
		 */
	}
	
	public int getStartBoardNo() {
		return startBoardNo;
	}
	/* 4. 현재 페이지 시작게시물 번호  설정*/
	public void setStartBoardNo(int currentPage) {
		this.startBoardNo = (currentPage - 1) * boardListSize + 1;
		// (현재페이지 - 1) * 한 화면에 출력될 게시글 수 + 1
		// (2-1) * 10 + 1 = 11부터
	}
	
	public int getEndBoardNo() {
		return endBoardNo;
	}
	/* 5. 현재 페이지 끝게시물 번호 설정*/
	public void setEndBoardNo(int currentPage) {
		this.endBoardNo = currentPage * boardListSize;
		// 현재페이지 * 10
		// 2 * 20 = 20번까지
	}
	

	public int getPrevPage() {
		return prevPage;
	}
	/* 6. 이전페이지 설정 */
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	/* 6. 다음페이지 설정 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBoardListSize() {
		return boardListSize;
	}

	public void setBoardListSize(int boardListSize) {
		this.boardListSize = boardListSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "OraclePagination [currentPage=" + currentPage + ", boardListSize=" + boardListSize + ", pageSize="
				+ pageSize + ", totalBoard=" + totalBoard + ", totalPage=" + totalPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", startBoardNo=" + startBoardNo + ", endBoardNo=" + endBoardNo
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage + "]";
	}
	
	public static void main(String[] args) {
		
//		int page =11;			// 현재 페이지
//		int countList = 10;		// 한 페이지에 출력될 게시물 수 
//		int countPage = 10;		// 한 화면에 출력될 페이지 수
//		int totalCount = 111;	// 총 게시물 수
//		int totalPage = totalCount / countList;	// 총 페이지 수
//		
//		if (totalCount % countList > 0) {
//		    totalPage++;
//		}
//		if (totalPage < page) {
//		    page = totalPage;
//		}
//		int startPage = ((page - 1) / 10) * 10 + 1;
//		int endPage = startPage + countPage - 1;
//		if (endPage > totalPage) {
//		    endPage = totalPage;
//		}
//		
//	
//		if (startPage > 1) {
//		    System.out.print("<a href=\"?page=1\">처음</a> ");
//		}
//		if (page > 1) {
//		    System.out.print("<a href=\"?page=" + (page - 1)  + "\">이전</a> \t");
//		}
//		for (int iCount = startPage; iCount <= endPage; iCount++) {
//		    if (iCount == page) {
//		        System.out.print(" <b>" + iCount + "</b>");
//		    } else {
//		        System.out.print(" " + iCount + " ");
//		    }
//		}
//		if (page < totalPage) {
//		    System.out.print("\t <a href=\"?page=" + (page + 1)  + "\">다음</a>");
//		}
//
//		if (endPage < totalPage) {
//		    System.out.print(" <a href=\"?page=" + totalPage + "\">끝</a>");
//		}
		
		
//		int page = 13;			// 현재 페이지
//		int totalCount = 90;	// 총 게시물 수
//		OraclePagination pageing = new OraclePagination(totalCount, page);
//		System.out.println(pageing);
//		
//		int startPage = pageing.getStartPage();
//		int endPage = pageing.getEndPage();
//		int totalPage = pageing.getTotalPage();
//		
//		if(page != 1) {
//			System.out.print("<< ");
//		}
//		if(page > 6) {
//			System.out.print("1 ...");
//		}
//		
//		for (int i = startPage; i <= endPage; i++) {
//			
//		    if (i == page) {
//		        System.out.print(" <b>" + i + "</b>");
//		    } else {
//		        System.out.print(" " + i + " ");
//		    }
//		}
//		
//		if(endPage != totalPage && totalPage > 10) {
//			System.out.print("... " + totalPage);
//		} else if (endPage == totalPage) {
//			System.out.print("");
//		}
//		
//		if(page != totalPage) {
//			System.out.print(" >>");
//		}
		
	}
}
