import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Main {
  public static void main(String[] args) {
	  // defile trust store path
	  System.setProperty("javax.net.ssl.trustStore", "./cacerts");
	  System.setProperty("javax.net.ssl.trustStorePassword", "rainysong");
	  
	  // Create secure socket
	  SocketFactory socketFactory = SSLSocketFactory.getDefault();
	  try {
		System.out.println("Connect to server...");
		SSLSocket connection = (SSLSocket) socketFactory.createSocket("localhost", 1234);
		connection.startHandshake();
		
		// read write data
		InputStream inputStream = connection.getInputStream();
		Scanner streamReader = new Scanner(inputStream);
		OutputStream outputStream = connection.getOutputStream();
		PrintWriter streamWriter = new PrintWriter(outputStream, true);
		
		streamWriter.println("Hello Server!");
		String response = streamReader.nextLine();
		System.out.println("Response : " + response);
		
		streamReader.close();
		connection.close();
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
