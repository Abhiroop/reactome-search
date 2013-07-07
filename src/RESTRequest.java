import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class RESTRequest 
{

/**
* Makes an HTTP GET Request to the specified URL
* @param urlStr URL to make the HTTP GET Request
* @return JSON response
* @throws IOException
*/
	public static void main (String[]args) throws IOException 
	{
		String urlStr="http://reactomews.oicr.on.ca:8080/ReactomeRESTfulAPI/RESTfulWS/queryById/Pathway/109581";
		URL url = new URL(urlStr);
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.addRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200)
		{
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) 
		{
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		System.out.println(sb.toString());
	}

}