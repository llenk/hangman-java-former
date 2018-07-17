import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.*;

/**
 * * Java program to create a simple HTTP Server to demonstrate how to use *
 * ServerSocket and Socket class. * * @author Javin Paul
 */
public class Server {

  public static ArrayList getWords() {
    ArrayList<String> words = new ArrayList<String>();

    try {
      File file = new File("wordlist.txt");
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

  public static void main(String args[]) throws IOException {
    ServerSocket server = new ServerSocket(8080);
    ArrayList<String> words = getWords();
    System.out.println(words.get(5));
    System.out.println("Listening for connection on port 8080 ....");
    while (true) {
      try (Socket socket = server.accept()) {
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\nContent-Length: 28\r\n\r\n" + today;
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
      }
    }
  }
}
