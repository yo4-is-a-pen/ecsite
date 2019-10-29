package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private int count;
	private String pay;

	public String execute(){
		String result=SUCCESS;
		session.put("count", count);

		//integer.parseIntで文字列を整数に変換する
		int intCount=Integer.parseInt(session.get("count").toString());
		int intPrice=Integer.parseInt(session.get("buyItem_price").toString());

		//sessionにtotal_priceを格納
		session.put("total_price",intCount*intPrice);

		//変数paymentの定義
		String payment;

		//payのvalueが１の場合、payに１、paymentに"現金払い"を格納
		if(pay.equals("1")){
			payment="現金払い";
			session.put("pay", payment);

		//payのvalueが１以外の場合、paymentに"クレジットカード"を格納
		}else{
			payment="クレジットカード";
			session.put("pay",payment);
		}
		return result;
	}

	public void setCount(int count){
		this.count=count;
	}

	public void setPay(String pay){
		this.pay=pay;
	}

	public Map<String, Object>getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object>session){
		this.session=session;
	}

}
