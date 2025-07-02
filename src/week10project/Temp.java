package week10project;
public class Temp
{
	int id;
	int temp;
	String type;
	long date;
	
	Temp(int id,int temp, String type, long date)
	{
		this.id = id;
		this.temp = temp;
		this.type = type;
		this.date =date;
	}
	
	public String toString()
	{
		return id + " : " + temp + " : " + type + " : " + date;
	}
}