package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Collegamento;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		// String sql = "SELECT * from objects";
		String sql = "SELECT * FROM objects AS T1 WHERE (SELECT COUNT(object_id) "
				+ "FROM exhibition_objects AS T2 WHERE T1.object_id=T2.object_id "
				+ "GROUP BY object_id) <>0";

		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Collegamento> listCollegamenti() {
		
		String sql = "SELECT O1, O2, CE1 FROM ( SELECT O1, O2, COUNT(*) AS CE1 FROM (SELECT exhibition_id as E1, object_id AS O1 FROM exhibition_objects) AS T1, (SELECT exhibition_id as E2, object_id AS O2 FROM exhibition_objects) AS T2 WHERE O1<>O2 AND E1=E2 GROUP BY O1, O2 ) AS T1";
		List<Collegamento> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int peso = res.getInt("CE1");
				int id1 = res.getInt("O1");
				int id2 = res.getInt("O2");
				

				Collegamento c = new Collegamento(id1, id2, peso);
				
				result.add(c);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * SELECT O1, O2, CE1 FROM ( SELECT O1, O2, COUNT(*) AS CE1 FROM (SELECT exhibition_id as E1, object_id AS O1 FROM exhibition_objects) AS T1, (SELECT exhibition_id as E2, object_id AS O2 FROM exhibition_objects) AS T2 WHERE O1<>O2 AND E1=E2 GROUP BY O1, O2 ) AS T1
	 */
	
}
