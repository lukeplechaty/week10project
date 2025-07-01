package week10project;

import java.sql.*;
import java.util.Date;

public class DB
{
	Main main;
	Connection connection = null;
	Statement statement = null;
	DB(Main main){
		this.main = main;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:API.db");
			main.window.log("Opened database successfully");
			
			statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS temps "+
			"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
			"temp INT NOT NULL, "+
			"type CHAR(1) NOT NULL, "+
			"date INT NOT NULL);";
			statement.executeUpdate(sql);
			statement.close();
		}
		catch ( Exception e ) {
			main.window.log("DB Open Error.");
			main.window.log(e.getMessage());
		}
	}
	void close() {
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
	void add(Temp temp) {
        try
		{
		statement = connection.createStatement();
		String sql = "INSERT INTO temps (temp,type,date) "+
		"VALUES ("+temp.temp+","+temp.type+","+new Date()+");";
		statement.executeUpdate(sql);
		statement.close();
		main.window.log("Added ("+temp.temp+","+temp.type+","+new Date()+")");
		}
		catch(SQLException e)
		{
			main.window.log("DB Add Error.");
			main.window.log(e.getMessage());
		}
	}
	Temp read(String id){
        try
		{
        	statement = connection.createStatement();
            ResultSet rs = statement.executeQuery( "SELECT * FROM temps WHERE id="+id+";" );
            Temp temp=null;
            while ( rs.next() ) {
               temp=new Temp(rs.getInt("temp"),rs.getString("type"));
            }
            rs.close();
            statement.close();
    		main.window.log("Read ("+temp.toString());
            return temp;
		}
		catch(SQLException e)
		{
			main.window.log("DB Read Error.");
			main.window.log(e.getMessage());
		}
		return null;
	}
}
