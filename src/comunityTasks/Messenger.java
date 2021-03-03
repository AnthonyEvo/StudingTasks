package comunityTasks;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.util.Calendar;

public class Messenger {
	
	String serverAddress = "178.124.156.184", clientAddress = "127.0.0.1";
	int serverPort = 12080, clientPort = 12079;
	String serverMessage = "", clientMessage = "";
	
	Messenger() {
/*		new Server();
		try {
			Thread.sleep(3000);
		} catch(InterruptedException Ex) {
			
		}
		new Client();*/
		
		new MessengerScreen();
		
	}
	
	public static void main(String args[]) {
		new Messenger();
	}
	
	
	
	class Server implements Runnable{
		
		byte servInputBuffer[] = new byte[1024], servOutputBuffer[] = new byte[servInputBuffer.length];
		
		String defaultMessege = "server online";
		
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
			
			try (DatagramSocket socket = new DatagramSocket(serverPort, InetAddress.getByName("192.168.100.131"))) {
				System.out.println("Server Started");
				while(true) {
					serverMessage = "";
					
					DatagramPacket input = new DatagramPacket(servInputBuffer, servInputBuffer.length);
					socket.receive(input);
					
					for(int i = 0; i < defaultMessege.getBytes().length; i++) {
						servOutputBuffer[i] = defaultMessege.getBytes()[i];
					}
					
					socket.send(new DatagramPacket(servOutputBuffer, servOutputBuffer.length, input.getAddress(), input.getPort()));
					
					String messege = new String(input.getData());
					if(messege.trim().length() > 0) {
						serverMessage += Calendar.getInstance().getTime() + " " + input.getAddress().getHostAddress() + " ";
						
						for(char x : messege.toCharArray()) {	
							if(x != 0)
							serverMessage += x;
						}
					}
//					System.out.println(serverMessage);
				}
			}
		}
	}
	
	class Client implements Runnable{
		
		byte cliInputBuffer[] = new byte[1024], cliOutputBuffer[] = new byte[cliInputBuffer.length];
		
		Client() {
			Thread clientThread = new Thread(this, "Messanger Client");
			clientThread.start();
		}
		
		public void run() {
			requestServer();
		}
		
		void requestServer() {
			System.out.println("Client Started");	
			String defaultMessage = "Check Connection";
			
			try (DatagramSocket socket = new DatagramSocket(clientPort)) {
			
				socket.setSoTimeout(5000);
				
				while(true) {
					
					DatagramPacket output, input;
					String message = new String(cliInputBuffer);
					
					if(message.trim().length() > 0) {
						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length, InetAddress.getByName(serverAddress), serverPort);
					} else {
						
						for(int i = 0; i < defaultMessage.getBytes().length; i++) {
							cliInputBuffer[i] = defaultMessage.getBytes()[i];
						}
						
						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length, InetAddress.getByName(serverAddress), serverPort);
					}
					
					socket.send(output);
					
					input = new DatagramPacket(cliInputBuffer, cliInputBuffer.length);
					socket.receive(input);
					System.out.println(new String(input.getData()));
					
					Thread.sleep(500);
				}
			}
			catch (IOException Ex) {
				System.out.println("Seems server is ofline, but no guaranty");
				requestServer();
			}
			catch (InterruptedException Ex) {
				
			}
		}
	}
	
	class MessengerScreen extends JPanel implements Runnable{
		
		JFrame window;
		JPanel inputPanel;
		
		MessengerScreen() {
			Thread screen = new Thread(this, "Screen");
			screen.start();
		}
		
		void initiateScreen(String name) {
			window = new JFrame("Messeger " + name);
			window.setSize(500, 300);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			
			window.add(this);
			
			inputPanel = new JPanel();
			inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			window.add(inputPanel);
			window.setLayout(new GridLayout(2,1));
			window.setVisible(true);
		}
		
		JTextPane setInputTextPane() {
			
			return null;
		}
		
		JButton setSnedButton() {
			
			return null;
		}
		
		public void run() {
			initiateScreen("Client");
		}
	}
}

