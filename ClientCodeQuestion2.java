package socket1;

import java.io.*;
import java.net.*;

public class ClientCodeQuestion2 {
	public static void main(String args[]) throws IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(), 8080);
		System.out.println("Connected to server...");

		DataInputStream serverInput = new DataInputStream(socket.getInputStream());
		DataOutputStream clientOutput = new DataOutputStream(socket.getOutputStream());
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

		String messageFromServer, messageToServer;

		while (true) {
			System.out.print("You: ");
			messageToServer = userInput.readLine();
			clientOutput.writeUTF(messageToServer);
			clientOutput.flush();
			
			messageFromServer = serverInput.readUTF();
			System.out.println("Server: " + messageFromServer);

			if (messageToServer.equalsIgnoreCase("exit")) {
				System.out.println("Closing connection...");
				clientOutput.writeUTF("exit");
				break;
			}
		}

		// Close resources
		userInput.close();
		serverInput.close();
		clientOutput.close();
		socket.close();
	}
}
