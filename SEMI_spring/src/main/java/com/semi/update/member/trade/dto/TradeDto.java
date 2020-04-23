package com.semi.update.member.trade.dto;

import java.util.Date;

public class TradeDto {
	// 맴버 거래내역 

	    // 번호 
	    private int tradeNo;

	    // 멘티/멘토(부모중복) 
	    private String tradeRole;

	    // 입금/출금 
	    private String tradeType;

	    // 아이디 
	    private String id;

	    // 예금주/입금주 이름 
	    private String tradeName;

	    // 은행명 
	    private String tradeBankname;

	    // 계좌번호 
	    private String tradeBanknum;

	    // 날짜 
	    private Date tradeDate;

	    // 거래금액 
	    private int tradePrice;

	    // 결제완료코드 
	    private String tradeSuccessCode;

	    
	    public TradeDto() {
	    	
	    }
	    
	    public int getTradeNo() {
	        return tradeNo;
	    }

	    public void setTradeNo(int tradeNo) {
	        this.tradeNo = tradeNo;
	    }

	    public String getTradeRole() {
	        return tradeRole;
	    }

	    public void setTradeRole(String tradeRole) {
	        this.tradeRole = tradeRole;
	    }

	    public String getTradeType() {
	        return tradeType;
	    }

	    public void setTradeType(String tradeType) {
	        this.tradeType = tradeType;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTradeName() {
	        return tradeName;
	    }

	    public void setTradeName(String tradeName) {
	        this.tradeName = tradeName;
	    }

	    public String getTradeBankname() {
	        return tradeBankname;
	    }

	    public void setTradeBankname(String tradeBankname) {
	        this.tradeBankname = tradeBankname;
	    }

	    public String getTradeBanknum() {
	        return tradeBanknum;
	    }

	    public void setTradeBanknum(String tradeBanknum) {
	        this.tradeBanknum = tradeBanknum;
	    }

	    public Date getTradeDate() {
	        return tradeDate;
	    }

	    public void setTradeDate(Date tradeDate) {
	        this.tradeDate = tradeDate;
	    }

	    public int getTradePrice() {
	        return tradePrice;
	    }

	    public void setTradePrice(int tradePrice) {
	        this.tradePrice = tradePrice;
	    }

	    public String getTradeSuccessCode() {
	        return tradeSuccessCode;
	    }

	    public void setTradeSuccessCode(String tradeSuccessCode) {
	        this.tradeSuccessCode = tradeSuccessCode;
	    }

		@Override
		public String toString() {
			return "MemberTrade [tradeNo=" + tradeNo + ", tradeRole=" + tradeRole + ", tradeType=" + tradeType + ", id="
					+ id + ", tradeName=" + tradeName + ", tradeBankname=" + tradeBankname + ", tradeBanknum="
					+ tradeBanknum + ", tradePrice=" + tradePrice + ", tradeSuccessCode=" + tradeSuccessCode + "]";
		}

	    // MemberTrade 모델 복사
	    
}

