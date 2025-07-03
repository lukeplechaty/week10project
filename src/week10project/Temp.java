package week10project;
public class Temp
{
	private int id;
	private int temp;
	private String type;
	private String senser;
	private long date;
	
	Temp(int id,int temp, String type, String senser, long date)
	{
		this.id = id;
		this.temp = temp;
		this.type = type;
		this.senser = senser;
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
	
	public String getSenser()
	{
		return senser;
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