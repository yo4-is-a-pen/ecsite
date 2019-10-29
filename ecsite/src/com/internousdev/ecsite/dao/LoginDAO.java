package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.LoginDTO;
import com.internousdev.ecsite.util.DBConnector;

public class LoginDAO {

	//loginUserInfoメソッドの作成
	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword){

		//mysqlへの接続準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		LoginDTO loginDTO=new LoginDTO();

		//select文でテーブル呼び出し
		String sql="SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";

		//mysqlに接続
		try{
			PreparedStatement ps=con.prepareStatement(sql);

			//sqlに文字列でset
			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);

			//実行命令
			ResultSet rs= ps.executeQuery();

			//上から順に一行ずつ処理
			//mysqlから情報を取得してloginDTOにset
			if(rs.next()){
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));

				//login_idが空じゃないなら取得してloginDTOにtrueを入れる
				if(rs.getString("login_id")!=null){
					loginDTO.setLoginFlg(true);
				}
			}
		//例外処理
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginDTO;
	}

}
