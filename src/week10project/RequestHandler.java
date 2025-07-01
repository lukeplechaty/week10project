package week10project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
		Gson gson = new Gson();
		try
		{
			if(method.equals("GET"))
			{
				Map<String, String> pramps = getParamMap(httpExchange.getRequestURI().getQuery());
				response = gson.toJson(main.db.read(pramps.get("id")));
			}
			else if(method.equals("POST"))
			{
				InputStream is = httpExchange.getRequestBody();
				int n;
				String text = "";
				while((n = is.read()) != -1)
					text += (char)n;
				Temp tempData = gson.fromJson(text, Temp.class);
				main.window.log(tempData.toString());
				main.db.add(tempData);
				response = "{\"saved\":true}";
			}
			else
			{
				throw new Exception("Not Valid Request Method");
			}
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
	
	public Map<String, String> getParamMap(String query)
	{
		if(query == null || query.isEmpty())
			return Collections.emptyMap();
		return Stream.of(query.split("&")).filter(s -> !s.isEmpty()).map(kv -> kv.split("=", 2)).collect(Collectors.toMap(x -> x[0], x -> x[1]));
	}
}