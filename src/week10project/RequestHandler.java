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
			if(path.startsWith("/") && method.equals("GET"))
			{
				response = "<html>\n<body>\n"
				+ "<p style=\"white-space: pre-line; white-space: pre-wrap;\">To add data POST to /add with\n"
				+ "{\n"
				+ "\t\"temp\":21\t\t\t// temperature value\n"
				+ "\t\"type\":\"C\"\t\t\t// temperature type i.e. C,F,K\n"
				+ "}\n"
				+ "To read all data GET from /all\n"
				+ "To read last data GET from /last\n"
				+ "You will get this info\n"
				+ "{\n"
				+ "\t\"id\":0\t\t\t\t// id in the table\n"
				+ "\t\"temp\":21\t\t\t// temperature value\n"
				+ "\t\"type\":\"C\"\t\t\t// temperature type i.e. C,F,K\n"
				+ "\t\"date\":1234567890\t// date in milliseconds\n"
				+ "}\n"
				+ "</p>\n</body>\n</html>";
			}
			else if(path.startsWith("/last") && method.equals("GET"))
			{
				response = gson.toJson(main.db.readLast());
				httpExchange.getResponseHeaders().set("Content-Type", "application/json");
			}
			else if(path.startsWith("/all") && method.equals("GET"))
			{
				response = gson.toJson(main.db.readAll());
				httpExchange.getResponseHeaders().set("Content-Type", "application/json");
			}
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
				httpExchange.getResponseHeaders().set("Content-Type", "application/json");
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
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream outStream = httpExchange.getResponseBody();
		outStream.write(response.getBytes());
		outStream.close();
	}
}