import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.*;

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

  public static String pickWord(ArrayList<String> words) {
    return words.get(0);
  }

  public static char[] wordToArray(String word) {
    char[] arrayWord = new char[word.length()];
    for (int i = 0; i < word.length(); i++) {
      arrayWord[i] = word.charAt(i);
    } 
    return arrayWord;
  }

  public static void main(String args[]) throws IOException {
    ServerSocket server = new ServerSocket(8080);
    ArrayList<String> words = getWords();
    String word = pickWord(words);
    char[] arrayWord = wordToArray(word);
    System.out.println("Listening for connection on port 8080 ....");
    while (true) {
      try (Socket socket = server.accept()) {
        Date today = new Date();
        String httpResponse = "HTTP/1.1 200 OK\r\nContent-Length: " + word.length() + "\r\n\r\n" + word;
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
      }
    }
  }
}
