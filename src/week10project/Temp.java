package week10project;
public class Temp
{
	private int id;
	private int temp;
	private String type;
	private long date;
	
	Temp(int id,int temp, String type, long date)
	{
		this.id = id;
		this.temp = temp;
		this.type = type;
		this.date =date;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getTemp()
	{
		return temp;
	}
	
	public String getType()
	{
		return type;
	}
	
	public long getDate()
	{
		return date;
	}
	
	public String toString()
	{
		return id + " : " + temp + " : " + type + " : " + date;
	}
}