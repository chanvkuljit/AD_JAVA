package socket1;

import java.io.*;
import java.net.*;

public class ServerCodeQuestion1 {

	static boolean checkArmstrong(int n) {
		int temp, digits = 0, last = 0, sum = 0;
		temp = n;
		while (temp > 0) {
			temp = temp / 10;
			digits++;
		}
		temp = n;
		while (temp > 0) {

			last = temp % 10;
			sum += (Math.pow(last, digits));
			temp = temp / 10;
		}
		if (n == sum)
			return true;
		else
			return false;
	}

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
				messageFromClient = clientInput.readUTF();
				serverOutput.writeUTF(
						"Is given number armstrong number: " + checkArmstrong(Integer.parseInt(messageFromClient)));
				System.out.println("Client: " + messageFromClient);

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
