package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	private Map<String, Object> session;
	private LoginDAO loginDAO=new LoginDAO();
	private LoginDTO loginDTO=new LoginDTO();
	private BuyItemDAO buyItemDAO= new BuyItemDAO();

	public String execute(){

		//resultにERRORを入れる。
		String result=ERROR;

	//loginDTOにloginDAOのLoginUserInfoメソッド内のloginUserIdとloginPasswordを入れる。
		loginDTO=loginDAO.getLoginUserInfo(loginUserId,loginPassword);

		//sessionにkey:loginUser, 型:loginDTOを入れる。
		session.put("loginUser", loginDTO);


		//sessionのloginUserをLoginDTOに戻すことで上記のloginDTOの処理が発生して、LoginDTOからloginFlgを取得する。
		//loginDAO.getLoginUserInfo()の処理で結果がtrueになる。
		//要するにif文で処理がtrueであることを確認している。
		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){

			//条件を満たした場合、resultにSUCCESSを入れる。
			result=SUCCESS;

			//インスタンス化
			BuyItemDTO buyItemDTO=buyItemDAO.getBuyItemInfo();

			//sessionに格納
			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			return result;

		}
		return result;
	}

	public String getLoginUserId(){
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId){
		this.loginUserId=loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword){
		this.loginPassword=loginPassword;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object>session){
		this.session=session;
	}

}
