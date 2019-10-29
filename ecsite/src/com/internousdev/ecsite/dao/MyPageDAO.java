package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.MyPageDTO;
import com.internousdev.ecsite.util.DBConnector;

public class MyPageDAO {

	//getMyPageUserInfoメソッドの作成
	//購入履歴を取得するメソッド
	public ArrayList<MyPageDTO>getMyPageUserInfo
	(String item_transaction_id,String user_master_id)throws SQLException{

		//mysqlへの接続準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		//MyPageDTO型のListを作成。型名は名前でしかない
		//格納する中身はListの型と同じ形でないといけない
		ArrayList<MyPageDTO>myPageDTO=new ArrayList<MyPageDTO>();

		//テーブルの呼び出し
		String sql=
		"SELECT ubit.id,iit.item_name,ubit.total_price,ubit.total_count,ubit.pay,ubit.insert_date FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit ON ubit.item_transaction_id=iit.id WHERE ubit.item_transaction_id=? AND ubit.user_master_id=? ORDER BY insert_date DESC";

		try{
			//mysqlに接続
			PreparedStatement ps=con.prepareStatement(sql);

			//SELECT文の？に格納
			//数字は？の順番
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);

			//実行
			ResultSet rs=ps.executeQuery();

			//繰り返し処理
			while(rs.next()){

				//インスタンス化
				MyPageDTO dto=new MyPageDTO();

				//sqlから取得した情報を上から順に１件ずつDTOに格納
				dto.setId(rs.getString("id"));
				dto.setItemName(rs.getString("item_name"));
				dto.setTotalPrice(rs.getString("total_price"));
				dto.setTotalCount(rs.getString("total_count"));
				dto.setPayment(rs.getString("pay"));
				dto.setInsert_date(rs.getString("insert_date"));

				//DTOに格納した情報をListにも格納する
				myPageDTO.add(dto);
			}

			//例外処理
		}catch(Exception e){
			e.printStackTrace();

			//mysqlとの接続を切断
		}finally{
			con.close();
		}

		//呼び出し元にmyPageDTOを返す
		return myPageDTO;
	}

	//buyItemHistryDeleteメソッドの作成
	//購入履歴を削除するためメソッド
	public int buyItemHistoryDelete
	(String item_transaction_id,String user_master_id)throws SQLException{

		//mysqlへの接続準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		//データベースを呼び出してdeleteする準備
		String sql="DELETE FROM user_buy_item_transaction WHERE item_transaction_id=? AND user_master_id=?";

		PreparedStatement ps;

		//resultに0を入れて初期化
		int result=0;
		try{
			//データベースに接続
			ps=con.prepareStatement(sql);

			//delete文の？に情報を格納する
			//数字は？の順番
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);

			//実行
			//resultには削除した件数が入る
			result=ps.executeUpdate();

		//例外処理
		}catch(SQLException e){
			e.printStackTrace();

		//mysqlとの接続を切断
		}finally{
			con.close();
		}

		//呼び出し元にresultを返す
		return result;
	}

}
