package week10project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main
{
	int port = 80;
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.Server();
	}
	
	void Server() {
		
		try
		{
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new RequestHandler());
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
			server.setExecutor(threadPoolExecutor);
			server.start();
			System.out.println("Server started on port " + port + ".");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	class RequestHandler implements HttpHandler
	{
		public void handle(HttpExchange httpExchange) throws IOException
		{
			String response = "Request Received";
			String method = httpExchange.getRequestMethod();
			Gson gson = new Gson();
			try
			{
				if(method.equals("GET"))
				{
					response = gson.toJson(new Temps(32,"C"));
				}
				else if(method.equals("POST"))
				{
					InputStream is = httpExchange.getRequestBody();
					int n;
					String text = "";
					while((n=is.read())!=-1) text+=(char)n;
					Temps tempData = gson.fromJson(text,Temps.class);
					System.out.println(tempData.temp+" "+tempData.type);
					response = "{\"saved\":true}";
				}
				else
				{
					throw new Exception("Not Valid Request Method");
				}
			}
			catch(Exception e)
			{
				System.out.println("An error request");
				response = e.toString();
				e.printStackTrace();
			}
			httpExchange.getResponseHeaders().set("Content-Type", "application/json");
			httpExchange.sendResponseHeaders(200, response.length());
			OutputStream outStream = httpExchange.getResponseBody();
			outStream.write(response.getBytes());
			outStream.close();
		}
	}
	
	class Temps
	{
		int temp;
		String type;
		Temps(int temp, String type)
		{
			this.temp = temp;
			this.type = type;
		}
	}
}
