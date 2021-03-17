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
		
		/*
		 * new Server(); try { Thread.sleep(3000); } catch(InterruptedException Ex) {
		 * 
		 * } new Client();
		 * 
		 * new MessengerScreen();
		 */

		EncryptionModule eModule = new EncryptionModule();
		eModule.desypher(eModule.encrypt("Error"));
	}

	public static void main(String args[]) {

		new Messenger();
	}

	/*
	 * Сервер месенджера, должен принимать сообщения, отправлять публичные и личные
	 * сообщения клиентам, являясь узлом передачи данных
	 */

	class Server implements Runnable {	//TODO Разработать класс сервера на основе абстрактного причем и для сервера и для клиента

		byte servInputBuffer[] = new byte[1024], servOutputBuffer[] = new byte[servInputBuffer.length];

		String defaultMessege = "server online";

		Server() {
			Thread serverThread = new Thread(this, "Messanger Server");
			serverThread.start();
		}

		@Override
		public void run() { // Обработка сообщений происходит в отдельном потоке
			try {
				manageMessages();
			} catch (IOException Ex) {

			} catch (InterruptedException Ex) {

			}
		}

		void manageMessages() throws IOException, InterruptedException { // Обработчик сообщений

			try (DatagramSocket socket = new DatagramSocket(serverPort, InetAddress.getByName("192.168.100.131"))) {
				System.out.println("Server Started");
				while (true) {
					serverMessage = "";

					DatagramPacket input = new DatagramPacket(servInputBuffer, servInputBuffer.length);
					socket.receive(input);

					for (int i = 0; i < defaultMessege.getBytes().length; i++) {
						servOutputBuffer[i] = defaultMessege.getBytes()[i];
					}

					socket.send(new DatagramPacket(servOutputBuffer, servOutputBuffer.length, input.getAddress(),
							input.getPort()));

					String messege = new String(input.getData());
					if (messege.trim().length() > 0) {
						serverMessage += Calendar.getInstance().getTime() + " " + input.getAddress().getHostAddress()
								+ " ";

						for (char x : messege.toCharArray()) {
							if (x != 0)
								serverMessage += x;
						}
					}
//					System.out.println(serverMessage);
				}
			}
		}
	}

	/*
	 * Клиент месенджера, отправляет сообщения и ждет ответа от сервера для проверки
	 * его работоспособности так же как и сервер работает во отдельном потоке
	 */

	class Client implements Runnable {

		byte cliInputBuffer[] = new byte[1024], cliOutputBuffer[] = new byte[cliInputBuffer.length];

		Client() {
			Thread clientThread = new Thread(this, "Messanger Client");
			clientThread.start();
		}

		@Override
		public void run() {
			requestServer();
		}

		void requestServer() { // Метод запрашивающий сервер и ожидающий ответа

			System.out.println("Client Started");
			String defaultMessage = "Check Connection";

			try (DatagramSocket socket = new DatagramSocket(clientPort)) {

				socket.setSoTimeout(5000); // Таймаут приема ответного пакета от сервера

				while (true) {

					DatagramPacket output, input;
					String message = new String(cliInputBuffer);

					if (message.trim().length() > 0) {
						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length,
								InetAddress.getByName(serverAddress), serverPort);
					} else {

						for (int i = 0; i < defaultMessage.getBytes().length; i++) {
							cliInputBuffer[i] = defaultMessage.getBytes()[i];
						}

						output = new DatagramPacket(cliInputBuffer, cliInputBuffer.length,
								InetAddress.getByName(serverAddress), serverPort);
					}

					socket.send(output);

					input = new DatagramPacket(cliInputBuffer, cliInputBuffer.length);
					socket.receive(input);
					System.out.println(new String(input.getData()));

					Thread.sleep(500);
				}
			} catch (IOException Ex) {
				System.out.println("Seems server is ofline, but no guaranty");
				requestServer();
			} catch (InterruptedException Ex) {

			}
		}
	}

	class MessengerScreen extends JPanel implements Runnable {

		JFrame window;
		JPanel inputPanel;

		MessengerScreen() {
			Thread screen = new Thread(this, "Screen");
			screen.start();
		}

		void initiateScreen(String name) {
			window = new JFrame("Messeger " + name);
			window.setSize(500, 300);
			window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			this.setBorder(BorderFactory.createLineBorder(Color.black));

			window.add(this);

			inputPanel = new JPanel();
			inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));

			window.add(inputPanel);
			window.setLayout(new GridLayout(2, 1));
			window.setVisible(true);
		}

		JTextPane setInputTextPane() {

			return null;
		}

		JButton setSnedButton() {

			return null;
		}

		@Override
		public void run() {
			initiateScreen("Client");
		}
	}
}
