package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.util.DBConnector;

public class BuyItemDAO {

	//buyItemInfoメソッドを作る。
	public BuyItemDTO getBuyItemInfo(){

		//mysqlに接続するための準備
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();

		//buyItemDTOのインスタンス化
		BuyItemDTO buyItemDTO=new BuyItemDTO();

		//テーブル呼び出し
		String sql="SELECT id,item_name,item_price FROM item_info_transaction";

		//mysqlへの接続と実行
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

		//mysqlから一つずつ取得してbuyItemDTOに入れる。
			if(rs.next()){
				buyItemDTO.setId(rs.getInt("id"));
				buyItemDTO.setItemName(rs.getString("item_name"));
				buyItemDTO.setItemPrice(rs.getString("item_price"));
			}

		//例外への対処
		}catch(Exception e){
			e.printStackTrace();
		}

		//buyItemDTOに返す。
		return buyItemDTO;
	}

}
