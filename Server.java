import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Date; 
import java.io.PrintWriter;
/** * Java program to create a simple HTTP Server to demonstrate how to use * ServerSocket and Socket class. * * @author Javin Paul */ 
public class Server { 

  public static Array getWords() {
    
    return new Array[0];
  }

  public static void main(String args[]) throws IOException { 
    ServerSocket server = new ServerSocket(8080); 
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
