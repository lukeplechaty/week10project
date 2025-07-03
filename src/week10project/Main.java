package week10project;
public class Main
{
	private Window window;
	private ConsoleWindow panel;
	public DB db;
	private static boolean gui = true;
	private static int argPort = -1;
	
	public static void main(String[] args)
	{
		if(args.length>0)
		{
			boolean select = false;
			for(String a:args)
			{
				if(select)
				{
					argPort = Integer.parseInt(a);
					select = false;
				}
				else if(a.equalsIgnoreCase("-help") || a.equalsIgnoreCase("-?"))
				{
					System.out.println("Args\n-nogui //console only\n-port 8080 //port select replace 8080 with the port you want");
					System.exit(0);
				}
				else if(a.equalsIgnoreCase("-port"))select = true;
				else if(a.equalsIgnoreCase("-nogui"))gui = false;
			}
		}
		if(gui && argPort<0) new Main().portSet();
		else if(gui && argPort>0) new Main().guiStart(argPort);
		else if(!gui && argPort>0) new Main().start(argPort);
		else
		{
			System.out.println("Args\n-nogui //console only\n-port 8080 //port select replace 8080 with the port you want");
			System.exit(0);
		}
	}
	
	private void portSet()
	{
		window = new Window(new PortWindow(this),this);
	}
	
	public void guiStart(int port)
	{
		panel = new ConsoleWindow(this);
		window.replace(panel);
		db = new DB(this);
		new Thread(new Server(this, port)).start();
	}
	
	private void start(int port)
	{
		db = new DB(this);
		new Thread(new Server(this, port)).start();
	}
	
	public void log(String message)
	{
		if(gui)	panel.log(message);
		else System.out.println(message);
	}
}
