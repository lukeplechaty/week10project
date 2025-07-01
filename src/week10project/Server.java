package week10project;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.sun.net.httpserver.HttpServer;

public class Server implements Runnable
{
	Main main;
	int port = 80;
	HttpServer server;
	Server(Main main){
		this.main = main;
	}
	public void run()
	{
		main.window.log("The server is running.");
		try
		{
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new RequestHandler(main));
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
			server.setExecutor(threadPoolExecutor);
			server.start();
			main.window.log("Server started on port " + port + ".");
		}
		catch(Exception e)
		{
			main.window.log("An Exception was caught.");
			main.window.log(e.getMessage());
		}
	}
}
