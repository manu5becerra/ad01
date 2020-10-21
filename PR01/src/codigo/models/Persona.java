package codigo.models; 

import codigo.db.DbObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Persona extends DbObject {	 
	 
	private static final String TABLE = "person";
	private static final ArrayList COLS = getArrayCols();
		
	private int id = 0;
	public String name = "";  
	public String lastName = "";  
	
	private static ArrayList getArrayCols() {
		ArrayList list = new ArrayList();
		list.add("name");
		list.add("lastName"); 
		return list;
	}
	
	@Override
	public DbObject parse(ResultSet rs) throws SQLException {
		Persona per = new Persona();
		per.id = rs.getInt("id");
		per.name = rs.getString("name");
		per.lastName = rs.getString("lastName");
		
		return per;		
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
		list.add(this.name);
		list.add(this.lastName);
		return list;
	}

	@Override
	public int getId() { 
		return this.id;
	} 
	
	public void setId(int id) {
		this.id = id;
	}

	
 
}
