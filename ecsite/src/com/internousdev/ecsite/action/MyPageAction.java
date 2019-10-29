package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.MyPageDAO;
import com.internousdev.ecsite.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private MyPageDAO myPageDAO=new MyPageDAO();
	private ArrayList<MyPageDTO> myPageList=new ArrayList<MyPageDTO>();
	private String deleteFlg;
	private String message;

	public String execute() throws SQLException{

		//session内にlogin_user_idがないならERRORを返す
		if(!session.containsKey("login_user_id")){
			return ERROR;
		}

		//購入履歴の状態確認
		//deleteFlgのvalueが空の場合（履歴が削除されている場合）
		if(deleteFlg==null){

			//sessionから指定した情報を取得して、それぞれの変数に代入する
			String item_transaction_id=session.get("id").toString();
			String user_master_id=session.get("login_user_id").toString();

			//上で取得した情報をgetMyPageUserInfoメソッドで処理してmyPageListに格納する
			//履歴情報をDBに格納するメソッド
			myPageList=myPageDAO.getMyPageUserInfo(item_transaction_id,user_master_id);

		//deleteFlgのvalueが１の場合、deleteメソッドを実行する（履歴が削除されていない場合）
		}else if(deleteFlg.equals("1")){
			delete();
		}

		//上記がすべて実行出来たらresultにSUCCESSを入れて、呼び出し元にresultを返す
		String result=SUCCESS;
		return result;
	}

	//履歴情報を削除するメソッド
	public void delete() throws SQLException{

		//sessionから指定した情報を取得してそれぞれの変数に代入する
		String item_transaction_id=session.get("id").toString();
		String user_master_id=session.get("login_user_id").toString();

		//上で取得した情報をbuyItemHistoryDeleteメソッドで処理する
		//返ってきた処理の結果（result）をint型でresに格納する
		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id,user_master_id);

		//resが１以上の場合
		if(res>0){

			//myPageListを空にして、messageに下の文をsetする
			myPageList=null;
			setMessage("商品情報を正しく削除しました。");

		//resが0の場合、messageに下の文をsetする
		}else if(res==0){
			setMessage("商品情報の削除に失敗しました。");
		}
	}

	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg=deleteFlg;
	}

	public ArrayList<MyPageDTO>getMyPageList(){
		return this.myPageList;
	}

	public String getMessaage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message=message;
	}

	public Map<String, Object>getSession(){
		return this.session;
	}

	@Override
	public void setSession(Map<String, Object>session){
		this.session=session;
	}

}

