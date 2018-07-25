import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.*;
import java.net.URI;
import java.net.URLDecoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
    ServerSocket server = new ServerSocket(8080);
    ArrayList<String> words = getWords();
    Word word = new Word(pickWord(words));
    System.out.println("Listening for connection on port 8080 ....");
    while (true) {
      try (Socket socket = server.accept()) {
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\nContent-Length: " + word.getWord().length() + "\r\n\r\n" + word.getWord();
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
      }
    }
  }
}
