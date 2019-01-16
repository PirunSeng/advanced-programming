import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

	public static void main (String[] args) {
		
		client();
		
		// server();
		
	}
	
	private static void server() {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Server connected");
			serverSocket.close();
			
			ServerSocket newserverSocket = new ServerSocket(12345);
			System.out.println("Other Server connected again");
			newserverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void client(){
		try {
			// 1. Establish Connection
			System.out.println("Start connecting...");
//			Socket connection = new Socket("ip", 80);
			Socket connection = new Socket("localhost", 8888);
			System.out.println("Connected successfully");
			
			// 2. Loading Streams
			OutputStream outputStream = connection.getOutputStream();
			InputStream inputStream = connection.getInputStream();
			
			// 4. Communicating
			// 4.1 Sending request/data
			String postData = "name=Seng Pirun";
			int postDataLength = postData.length();
			Writer streamWriter = new OutputStreamWriter(outputStream);
			
			// request line
//			streamWriter.write("POST /rupp/mite/register.php HTTP/1.1\r\n");
			streamWriter.write("POST /AdvancedProgramming/index.php HTTP/1.1\r\n");
			// header
			streamWriter.write("Host: localhost\r\n");
			streamWriter.write("Content-Length: " + postDataLength + "\r\n");
			streamWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
			// must have empty line after header
			streamWriter.write("\r\n");

			// data
			streamWriter.write(postData);
			
			streamWriter.flush();
			
			// 4.2 Read data / Receiving response
			Scanner streamReader = new Scanner(inputStream);
			while (streamReader.hasNextLine()) {
				String data = streamReader.nextLine();
				System.out.println(data);
			}
			
			// close resources
			streamWriter.close();
			streamReader.close();
			outputStream.close();
			inputStream.close();
			
			
			// 5. Disconnect
			System.out.println("\nDisconnecting...");			
			connection.close();
			System.out.println("Disconnected successfully");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("End Application!");
		
		
		
//		another way called try with resource, but only for those with Closable, java >= 1.7
//		try (Socket connection = new Socket("localhost", 8888);) {
//			System.out.println("Connected");
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}	
}
