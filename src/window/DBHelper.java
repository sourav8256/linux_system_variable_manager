package window;

import java.sql.*;
import java.util.ArrayList;

import window.model.PathVar;

public class DBHelper {
	
	public DBHelper() {
		//getConnection();
		createTable();
	}
	
	// create database schema
	
	
	  
	
	
	public Connection getConnection(){
		
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass()+" : "+e.getMessage());
			e.printStackTrace();
		}
		

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:variableStore.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	public void createTable() {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("CREATE TABLE paths (\n" + 
					"    `v_name` VARCHAR(50),\n" + 
					"    `path` VARCHAR(50)\n" + 
					")");
			ps.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	
	public void deleteRow(String path) {
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM paths WHERE path=?");
			ps.setString(1, path);
			ps.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PathVar> getList() {
		
		ArrayList<PathVar> pvars = new ArrayList<PathVar>();
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT v_name,path FROM paths");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pvars.add(new PathVar(rs.getString(1),rs.getString(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pvars;
	}
	
	public void insertPath(String path) {
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO paths VALUES (?,?)");
			ps.setString(1, "PATH");
			ps.setString(2, path);
			ps.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertVar(String vname,String path) {
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO paths VALUES (?,?)");
			ps.setString(1, vname);
			ps.setString(2, path);
			ps.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void updateVar(String vname,String path) {
		
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE paths SET path=? WHERE v_name=? LIMIT 1");
			ps.setString(1, path);
			ps.setString(2, vname);
			ResultSet rs = ps.executeQuery();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
