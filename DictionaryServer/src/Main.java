import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Map<String, String> words = new HashMap<>();
		Map<String, String> words = new HashMap<>();
		words.put("apple", "ផ្លែប៉ោម");
		words.put("book", "សៀវភៅ");
		
		try {
			System.out.println("Bind port ...");
			ServerSocket serverSocket = new ServerSocket(1234);
			while(true) {
				// Wait and accept connection from client
				System.out.println("Wait for client ...");
				Socket connection = serverSocket.accept();

				InputStream inputStream = connection.getInputStream();
				Scanner streamReader = new Scanner(inputStream);

				OutputStream outputStream = connection.getOutputStream();
				PrintWriter streamWriter = new PrintWriter(outputStream, true);

				// Implement DP protocol
				// Read request from client

				while(true){
					String request = streamReader.nextLine();
					if(request.equals("//close")){
						// Send close response back to client and close connection 
						streamWriter.println(request);
						break;
					}else {
						// Translate word to Khmer
						String translatedWord = words.get(request);
						if(translatedWord == null){
							translatedWord = "មិនមានក្នុងវចនានុក្រម";
						}
						// Send translated word to user and wait for another request
						streamWriter.println(translatedWord);
					}
				}
				// Close resources
				System.out.println("Disconnect from client ...");
				streamReader.close();
				connection.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}