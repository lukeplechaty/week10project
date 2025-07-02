package week10project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler
{
	Main main;
	
	RequestHandler(Main main)
	{
		this.main = main;
	}
	
	public void handle(HttpExchange httpExchange) throws IOException
	{
		String response = "Request Received";
		String method = httpExchange.getRequestMethod();
		String path = httpExchange.getRequestURI().getPath();
		Gson gson = new Gson();
		try
		{
			if(path.startsWith("/last") && method.equals("GET"))
				response = gson.toJson(main.db.readLast());
			else if(path.startsWith("/all") && method.equals("GET"))
				response = gson.toJson(main.db.readAll());
			else if(path.startsWith("/add") && method.equals("POST"))
			{
				InputStream is = httpExchange.getRequestBody();
				int n;
				String text = "";
				while((n = is.read()) != -1)
					text += (char)n;
				Temp tempData = gson.fromJson(text, Temp.class);
				main.db.add(tempData);
				response = "{\"saved\":true}";
			}
			else
				throw new Exception("Not Valid Request Method");
		}
		catch(Exception e)
		{
			main.window.log("An error request");
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