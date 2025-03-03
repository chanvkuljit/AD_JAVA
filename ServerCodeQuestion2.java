package socket1;

import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class ServerCodeQuestion2 {

	public static void main(String args[]) throws IOException {

		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Server started, waiting for client...");

		Socket socket = serverSocket.accept();
		System.out.println("Client connected!");

		DataInputStream clientInput = new DataInputStream(socket.getInputStream());
		DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
		BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

		String messageFromClient;

		while (true) {
			try {
				LocalTime time = LocalTime.now();
				messageFromClient = clientInput.readUTF();
				serverOutput.writeUTF("Hello " + messageFromClient + " Currently it is: " + time);
//				System.out.println("Client: " + messageFromClient);

				if (messageFromClient.equalsIgnoreCase("exit")) {
					System.out.println("Client closed the connection.");
					break;
				}
			} catch (Exception e) {
				serverOutput.writeUTF(e.getMessage());
			}
		}

		serverInput.close();
		clientInput.close();
		serverOutput.close();
		socket.close();
		serverSocket.close();
	}
}
