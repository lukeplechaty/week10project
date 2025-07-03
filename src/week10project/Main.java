package week10project;
public class Main
{
	ConsoleWindow window;
	DB db;
	Server server;
	
	public static void main(String[] args)
	{
		new Main().portSet();
	}
	
	void portSet()
	{
		new PortWindow(this);
	}
	
	void start(int port)
	{
		server = new Server(this, port);
		window = new ConsoleWindow(this);
		db = new DB(this);
	}
}
