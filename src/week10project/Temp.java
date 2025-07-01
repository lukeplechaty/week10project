package week10project;
public class Temp
{
	int temp;
	String type;
	
	Temp(int temp, String type)
	{
		this.temp = temp;
		this.type = type;
	}
	
	public String toString()
	{
		return temp + " " + type;
	}
}