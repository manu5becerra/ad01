package codigo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import codigo.db.DbObject;

public class Usuario extends DbObject {
	
	private static final String TABLE = "usuario";
	private static final ArrayList COLS = getArrayCols();
	
	
	private int id = 0;
	private String user = "";
	private String password = "";
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	private static ArrayList getArrayCols() {
		ArrayList list = new ArrayList();
		list.add("user");
		list.add("password"); 
		return list;
	}

	@Override
	public DbObject parse(ResultSet rs) throws SQLException {
		Usuario usr = new Usuario();
		usr.id = rs.getInt("id");
		usr.user = rs.getString("user");
		usr.password = rs.getString("password");
		
		return usr;		
	}

	

	@Override
	public String getTable() { 
		return TABLE;
	} 
	
	@Override
	public ArrayList getCols() { 
		return COLS;
	}

	@Override
	public ArrayList<String> getValues() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(this.user);
		list.add(this.password);
		return list;
	}

	public void setId(int id) {
		this.id = id;
	}
	  

}
