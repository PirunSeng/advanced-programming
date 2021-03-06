import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static String checkStatus(String[] statuses) {
		String status = String.join(" => ", statuses);
		return status;
	}
	public static void main(String[] args) {
		String statuses[] = {"EN", "KH"};
		Map<String, String> words = new HashMap<>();
		words.put("apple", "ផ្លែប៉ោម");
		words.put("book", "សៀវភៅ");
		
		Map<String, String> reversed_words = new HashMap<>();
		reversed_words.put("ផ្លែប៉ោម", "apple");
		reversed_words.put("សៀវភៅ", "book");
		
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
					}
					else if (request.equals("//status")) {
						streamWriter.println(request);
					}
					else if (request.equals("//switch")) {
						List<String> list = Arrays.asList(statuses);
						Collections.reverse(list);
						list.toArray(statuses);
						streamWriter.println(request);
					}
					else {
						String enWord = reversed_words.get(request);
						String translatedWord = words.get(request);
						String status = checkStatus(statuses);
						
						if(status.equals("KH => EN")) {
							// Translate word to English
							if(enWord == null) {
								enWord = "Not found";
							}
							streamWriter.println(enWord);
						}else {
							// Translate word to Khmer
							System.out.println("new status is not : " + status);
							if(translatedWord == null) {
								translatedWord = "មិនមានក្នុងវចនានុក្រម";
							}
							streamWriter.println(translatedWord);
						}
					}
				}
				// Close resources
				System.out.println("Disconnect from client ...");
				streamReader.close();
				connection.close();
				//serverSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}