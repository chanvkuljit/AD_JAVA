import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client1
{
	public static void main(String[]args)
	{
		try(Socket s=new Socket("localhost",8080))
		{
			DataInputStream dis=new DataInputStream(s.getInputStream());
			DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			Scanner sc=new Scanner(System.in);

			System.out.println("Enter Exit for Exit");

			while(true)
			{
				System.out.println("Client1:");
				String message=sc.nextLine();
				dos.writeUTF(message);
				dos.flush();

				if(message.equalsIgnoreCase("exit"))
				{
					System.out.println("Exit");
					break;
				}

				String response = dis.readUTF();
				System.out.println(response);
			}
			sc.close(); 
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
}