package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.ConnexionBDD;
import model.User;

public class MysqlUserDaoImp implements UserDao {
	
	private Connection conn;
	
	public MysqlUserDaoImp(Connection conn) {
		this.conn = conn;
	}
	
	public MysqlUserDaoImp() throws Exception {
		conn = ConnexionBDD.getConnexion();
	}

	@Override
	public void create(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAll() {
		List<User> res;
		User u;
		
		res = new ArrayList<User>();
		
		
		
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement("SELECT * FROM USERFX");
			rs = pStmt.executeQuery();
			
			while (rs.next()){
				int ID = rs.getInt("ID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String street = rs.getString("street");
				String city = rs.getString("city");
				int postalcode = rs.getInt("ID");
				String birthday = rs.getString("birthday");
				
				u= new User(firstName, lastName, street, city, postalcode, birthday);
				res.add(u);
			}
			
			System.out.println("MysqlUserDaoImp.findAll()");
			pStmt.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
	       } finally {
	           try { if (rs != null) rs.close(); } catch (SQLException sqle) {}
	           try { if (pStmt != null) pStmt.close(); } catch (SQLException sqle) {}
	       }
				
				
		return res;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
