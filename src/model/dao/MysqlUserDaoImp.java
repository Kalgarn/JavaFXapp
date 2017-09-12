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
		PreparedStatement pStmt = null;
		
		try {
			pStmt.executeUpdate("INSERT INTO USERFX (FIRSTNAME, LASTNAME, STREET, CITY, POSTALCODE, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?");
			pStmt.setString(1, u.getFirstName());
			pStmt.setString(2, u.getLastName());
			pStmt.setString(3, u.getStreet());
			pStmt.setString(4, u.getCity());
			pStmt.setInt(5, u.getPostalCode());
			pStmt.setDate(5, java.sql.Date.valueOf(u.getBirthday()));
			
			pStmt.executeUpdate();
			
			System.out.println("insert user : " + u.getFirstName());
			
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User u) {
		PreparedStatement pStmt = null;
		
		try {
			pStmt.executeUpdate("UPDATE USERFX SET FIRSTNAME = ?, LASTNAME = ?, STREET = ?, CITY = ?, POSTALCODE = ?, BIRTHDAY = ? WHERE IDUSER = ?");
			pStmt.setString(1, u.getFirstName());
			pStmt.setString(2, u.getLastName());
			pStmt.setString(3, u.getStreet());
			pStmt.setString(4, u.getStreet());
			pStmt.setInt(5, u.getPostalCode());
			pStmt.setDate(5, java.sql.Date.valueOf(u.getBirthday()));
			pStmt.setInt(6, u.getIdUSer());
			
			pStmt.executeUpdate();
			
			System.out.println("update user : ");
			
			pStmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(User u) {
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement("DELETE FROM USERFX WHERE IDUSER = ?");
			pStmt.setInt(1, u.getIdUSer());
			
			pStmt.executeUpdate();
			
			System.out.println("delete user: " + u.getIdUSer() +"->"+ u.getFirstName());
			
			pStmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
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
				int postalcode = rs.getInt("postalcode");
				Date birthday = rs.getDate("birthday");
				
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
