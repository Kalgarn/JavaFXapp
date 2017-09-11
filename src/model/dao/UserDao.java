package model.dao;



import java.util.List;

import model.User;

public interface UserDao {

	
	public abstract void create (User u);
	public abstract void update (User u);
	public abstract void delete (User u);
	public abstract List<User> findAll();
	public abstract User findById (String id);

	
}
