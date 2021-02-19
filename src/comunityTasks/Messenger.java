package comunityTasks;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.Calendar;

public class Messanger {
	
	String serverAddress = "127.0.0.1"/*"178.124.156.184"*/, clientAddress = "127.0.0.1";
	int serverPort = 12080, clientPort = 12079;
	String serverMessage = "", clientMessage = "";
	
	Messanger() {
		new Server();
		try {
			Thread.sleep(3000);
		} catch(InterruptedException Ex) {
			
		}
		new Client();
		
	}
	
	public static void main(String args[]) {
		new Messanger();
	}
	
	class Server implements Runnable{
		
		byte servInputBuffer[] = new byte[1024], servOutputBuffer[] = new byte[1024];
		
		Server(){
			Thread serverThread = new Thread(this, "Messanger Server");
			serverThread.start();
		}
		
		public void run() {
			try {
				manageMessages();
			}
			catch(IOException Ex) {
				
			}
			catch(InterruptedException Ex) {
				
			}
			
		}
		
		void manageMessages() throws IOException, InterruptedException {
			
			try (DatagramSocket socket = new DatagramSocket(serverPort)) {
				System.out.println("Server Started");
				while(true) {
					serverMessage = "";
					
					DatagramPacket input = new DatagramPacket(servInputBuffer, servInputBuffer.length);
					socket.receive(input);
					socket.send(new DatagramPacket(servOutputBuffer, servInputBuffer.length, input.getAddress(), clientPort));
					String message = new String(input.getData());
					if(message.trim().length() > 0) {
						serverMessage += Calendar.getInstance().getTime() + " " + input.getAddress().getHostAddress() + " ";
						
						for(char x : message.toCharArray()) {	
							if(x != 0)
							serverMessage += x;
						}
					}
					System.out.println(serverMessage);
				}
			}
		}
	}

	class Client implements Runnable{
		
		byte cliInputBuffer[] = new byte[1024], cliOutputBuffer[] = new byte[1024];
		
		Client() {
			Thread clientThread = new Thread(this, "Messanger Client");
			clientThread.start();
		}
		
		public void run() {
			requestServer();
		}
		
		void requestServer() {
			System.out.println("Client Started");
			try (DatagramSocket socket = new DatagramSocket(clientPort)) {
			
				while(true) {
					
					DatagramPacket output;
					String message = new String(cliInputBuffer);
					
					if(message.trim().length() > 0) {
						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length, InetAddress.getByName(serverAddress), serverPort);
					} else {
						
						String defaultMessage = "Check Connection";
						for(int i = 0; i < defaultMessage.getBytes().length; i++) {
							cliInputBuffer[i] = defaultMessage.getBytes()[i];
						}
						
						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length, InetAddress.getByName(serverAddress), serverPort);
					}
					
					socket.send(output);
					Thread.sleep(500);
				}
			}
			catch (IOException Ex) {
				
			}
			catch (InterruptedException Ex) {
				
			}
		}
	}

	class MessangerScreen extends JPanel implements Runnable{
		MessangerScreen() {
			
		}
		
		public void run() {
			
		}
	}

}

