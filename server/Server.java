import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;
import java.net.URI;
import java.net.URLDecoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

  public static ArrayList getWords() {
    ArrayList<String> words = new ArrayList<String>();
    try {
      File file = new File("../wordlist.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      String st;
      while ((st = br.readLine()) != null) {
        words.add(st);
      }
      br.close();
    } catch (FileNotFoundException fe) {
    } catch (IOException ioe) {
    }
    return words;
  }

  // TODO: fix so this picks a random one
  public static String pickWord(ArrayList<String> words) {
    return words.get(0);
  }

  @SuppressWarnings("unchecked")
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);
					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}

  public static void main(String args[]) throws IOException {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
      server.setExecutor(null);
			server.start();
    }
    catch (IOException e) {
			e.printStackTrace();
		}
  }
}
