package week10project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DB
{
	Main main;
	Connection connection = null;
	Statement statement = null;
	
	DB(Main main)
	{
		this.main = main;
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:API.db");
			main.window.log("Opened database successfully");
			statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS temps (id INTEGER PRIMARY KEY AUTOINCREMENT, temp INT NOT NULL, type CHAR(1) NOT NULL, date INT NOT NULL);";
			statement.executeUpdate(sql);
			statement.close();
		}
		catch(Exception e)
		{
			main.window.log("DB Open Error.");
			main.window.log(e.getMessage());
		}
	}
	
	void close()
	{
		try
		{
			connection.close();
			main.window.log("DB Closed.");
		}
		catch(SQLException e)
		{
			main.window.log("DB Close Error.");
			main.window.log(e.getMessage());
		}
	}
	
	void add(Temp temp)
	{
		try
		{
			statement = connection.createStatement();
			String sql = "INSERT INTO temps (temp,type,date) VALUES (" + temp.temp + ",'" + temp.type + "'," + new Date().getTime() + ");";
			statement.executeUpdate(sql);
			statement.close();
			main.window.log("Added");
		}
		catch(SQLException e)
		{
			main.window.log("DB Add Error.");
			main.window.log(e.getMessage());
		}
	}

	Temp readLast()
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
			main.window.log("Read last");
			return temp;
		}
		catch(SQLException e)
		{
			main.window.log("DB Read last Error.");
			main.window.log(e.getMessage());
		}
		return null;
	}
	
	List<Temp> readAll()
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
			main.window.log("Read all");
			return temp;
		}
		catch(SQLException e)
		{
			main.window.log("DB Read add Error.");
			main.window.log(e.getMessage());
		}
		return null;
	}
}
