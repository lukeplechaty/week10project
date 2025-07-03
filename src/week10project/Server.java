package week10project;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.sun.net.httpserver.HttpServer;

public class Server implements Runnable
{
	private Main main;
	private int port;
	private HttpServer server;
	
	Server(Main main, int port)
	{
		this.main = main;
		this.port = port;
	}
	
	public void run()
	{
		main.log("The server is running.");
		try
		{
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new RequestHandler(main));
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
			server.setExecutor(threadPoolExecutor);
			server.start();
			main.log("Server started on port " + port + ".");
		}
		catch(Exception e)
		{
			main.log("An Exception was caught.");
			main.log(e.getMessage());
		}
	}
}
