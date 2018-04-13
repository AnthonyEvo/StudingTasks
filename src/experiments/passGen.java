package experiments;

import java.util.Random;
import java.io.*;

public class passGen {

	passGen() {
		File genResult = new File("Password.txt");
		if(!genResult.exists()) {
			try {
				genResult.createNewFile();
			}
			catch(IOException Ex) {}
		}
		try {
			FileOutputStream fStream = new FileOutputStream(genResult);
			for(int i = 0; i < 24; i++)
			{
				fStream.write(genPass());
			}
		}
		catch(IOException Ex) {System.out.println(Ex);}
	}
	
	public static void main(String args[]) {
		new passGen();
	}
		
	int genPass() {
		switch(Math.abs(new Random().nextInt()) % 3) {
		case 0:
			return (Math.abs(new Random().nextInt()) % 9 + 48);
		case 1:
			return (Math.abs(new Random().nextInt()) % 25 + 97);
		case 2:
			return (Math.abs(new Random().nextInt()) % 25 + 65);
		}
		return 48;
	}
	
}
