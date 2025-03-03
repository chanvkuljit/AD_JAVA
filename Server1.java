import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server1
{
	public static void main(String[] args)
	{
		try(ServerSocket ss = new ServerSocket(8080))
		{
			while(true)
			{
				System.out.println("Server is Listening");

				Socket s=ss.accept();
				System.out.println("Client Connected: "+s.getInetAddress());

				DataInputStream dis=new DataInputStream(s.getInputStream());
				DataOutputStream dos=new DataOutputStream(s.getOutputStream());

				Scanner sc=new Scanner(System.in);

				while(true){
					String clientmessage = dis.readUTF();
					System.out.println("Client:"+clientmessage);

					if(clientmessage.equalsIgnoreCase("exit")){
						System.out.println("Client Disconnect");
						break;
					}
					System.out.print("Server:");
					String servermessage=sc.nextLine();
					dos.writeUTF(servermessage);
					dos.flush();
				}
			s.close();
			sc.close();
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
}