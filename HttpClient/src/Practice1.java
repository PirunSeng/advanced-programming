import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Practice1 {
  public static void main(String[] args) {
    try {
		Socket connection = new Socket("127.0.0.1", 80);
		
		// data
		String data = "name=OSCaR";
		int dataLength = data.length();
		
		// send request/data
		OutputStream outputStream = connection.getOutputStream();
		Writer streamWriter = new OutputStreamWriter(outputStream);
		// request line
		streamWriter.write("POST /AdvancedProgramming/index.php HTTP/1.1\r\n");
		// request header fields
		streamWriter.write("Host: localhost\r\n");
		streamWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
		streamWriter.write("Content-Length: " + dataLength + "\r\n");
		streamWriter.write("\r\n");
		// request body
		streamWriter.write(data);
		streamWriter.flush();
		
		// read response/data
		InputStream inputStream = connection.getInputStream();
		Scanner streamReader = new Scanner(inputStream);
		while (streamReader.hasNextLine()) {
		  String response = streamReader.nextLine();
		  System.out.println(response);
		}
		
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
