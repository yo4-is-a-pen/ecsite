package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	/*1,resultにloginを入れる。
	 *2,sessionにlogin_user_idがあるか確認する。 */
	public String execute(){
		String result="login";
		if(session.containsKey("login_user_id")){

		//buyitemDAOとbuyitemDTOのインスタンス化
			BuyItemDAO buyItemDAO=new BuyItemDAO();
			BuyItemDTO buyItemDTO=buyItemDAO.getBuyItemInfo();

		//sessionにid,buyitem_name,buyitem_priceをbuyitemDTOから持ってきて入れる。
			session.put("id",buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

		//上記の処理がすべて出来たらresultにSUCCESSを入れて返す。
			result=SUCCESS;
		}
		return result;
	}

	//setterとgetterの定義
	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object>session){
		this.session=session;
	}

}
