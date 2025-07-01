package week10project;
public class Main
{
	Window window;
	DB db;
	Server server;
	
	public static void main(String[] args)
	{
		new Main().start();
	}
	
	void start()
	{
		server = new Server(this);
		window = new Window(this);
		db = new DB(this);
	}
}
