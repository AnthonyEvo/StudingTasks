package experiments;

import java.io.*;
import java.net.*;

public class NetTrans {

	public static void main(String Args[]) {
		/*
		 * InetAddress Address = InetAddress.getLocalHost();
		 * System.out.println(Address);
		 * 
		 * InetAddress Addresses[] = InetAddress.getAllByName("VK.com"); for(InetAddress
		 * x: Addresses) { System.out.println(x); }
		 */
		// -----------------------------------------------------------------------

		try (Socket socket = new Socket("whois.internic.net", 43)) {
			int c;

			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			String str = (Args.length == 0 ? "MHProfessionals.com" : Args[0]) + "\n";
			byte buf[] = str.getBytes();

			out.write(buf);

			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}
}
