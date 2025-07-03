package week10project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DB
{
	private Main main;
	private Connection connection = null;
	private Statement statement = null;
	
	DB(Main main)
	{
		this.main = main;
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:API.db");
			main.log("Opened database successfully");
			statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS temps (id INTEGER PRIMARY KEY AUTOINCREMENT, temp INT NOT NULL, type CHAR(1) NOT NULL, date INT NOT NULL);";
			statement.executeUpdate(sql);
			statement.close();
		}
		catch(Exception e)
		{
			main.log("DB Open Error: "+e.getMessage());
		}
	}
	
	public void close()
	{
		try
		{
			connection.close();
			main.log("DB Closed.");
		}
		catch(SQLException e)
		{
			main.log("DB Close Error: "+e.getMessage());
		}
	}
	
	public void add(Temp temp)
	{
		try
		{
			statement = connection.createStatement();
			String sql = "INSERT INTO temps (temp,type,date) VALUES (" + temp.getTemp() + ",'" + temp.getType() + "'," + new Date().getTime() + ");";
			statement.executeUpdate(sql);
			statement.close();
			main.log("Added");
		}
		catch(SQLException e)
		{
			main.log("DB Add Error: "+e.getMessage());
		}
	}

	public Temp readLast()
	{
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM temps ORDER BY id DESC LIMIT 1;");
			Temp temp = null;
			while(rs.next())
			{
				temp = new Temp(rs.getInt("id"), rs.getInt("temp"), rs.getString("type"), rs.getLong("date"));
			}
			rs.close();
			statement.close();
			main.log("Read last");
			return temp;
		}
		catch(SQLException e)
		{
			main.log("DB Read last Error: "+e.getMessage());
		}
		return null;
	}
	
	public List<Temp> readAll()
	{
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM temps;");
			List<Temp> temp = new ArrayList<Temp>();
			while(rs.next())
			{
				temp.add(new Temp(rs.getInt("id"), rs.getInt("temp"), rs.getString("type"), rs.getLong("date")));
			}
			rs.close();
			statement.close();
			main.log("Read all");
			return temp;
		}
		catch(SQLException e)
		{
			main.log("DB Read add Error: "+e.getMessage());
		}
		return null;
	}
}
