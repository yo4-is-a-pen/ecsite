package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class UserCreateCompleteDAO {

	//createUserメソッド(返り値なし)
	public void createUser(String loginUserId,String loginUserPassword,String userName)throws SQLException{

		//mysqlに接続準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		DateUtil dateUtil=new DateUtil();

		//テーブルを呼び出して値を挿入する準備
		String sql="INSERT INTO login_user_transaction(login_id,login_pass,user_name,insert_date)VALUES(?,?,?,?)";

		try{
			//mysqlに接続
			PreparedStatement ps=con.prepareStatement(sql);

			//挿入の実行
			ps.setString(1, loginUserId);
			ps.setString(2, loginUserPassword);
			ps.setString(3, userName);
			ps.setString(4, dateUtil.getDate());

			//実行命令
			ps.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}

}
