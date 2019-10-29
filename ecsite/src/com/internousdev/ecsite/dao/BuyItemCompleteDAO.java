package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;

public class BuyItemCompleteDAO {

	//戻り値なし
	public void buyItemInfo(String item_transaction_id,String total_price,String total_count,String user_master_id,String pay)throws SQLException{

		//mysqlへの接続準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		DateUtil dateUtil=new DateUtil();

		//データベースの呼び出しと値の格納準備
		String sql="INSERT INTO user_buy_item_transaction(item_transaction_id,total_price,total_count,user_master_id,pay,insert_date)VALUES(?,?,?,?,?,?)";

		try{
			//データベースに接続
			PreparedStatement ps=con.prepareStatement(sql);

			//情報の格納
			//数字は？の順番
			ps.setString(1, item_transaction_id);
			ps.setString(2, total_price);
			ps.setString(3, total_count);
			ps.setString(4, user_master_id);
			ps.setString(5, pay);

			//現在日時の取得
			ps.setString(6, dateUtil.getDate());

			//実行
			ps.execute();

		//例外処理
		}catch(Exception e){
			e.printStackTrace();

		//mysqlとの接続を切断
		}finally{
			con.close();
		}
	}

}
