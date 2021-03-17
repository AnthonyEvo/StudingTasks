package experiments;

import java.util.Random;
import java.io.*;

public class passGen {

	passGen() {
		File genResult = new File("Password.txt");
		if (!genResult.exists()) {
			try {
				genResult.createNewFile();
			} catch (IOException Ex) {
			}
		}
		try {
			FileOutputStream fStream = new FileOutputStream(genResult);
			for (int i = 0; i < 12; i++) {
				fStream.write(genSymb());
			}
		} catch (IOException Ex) {
			System.out.println(Ex);
		}
	}

	public static void main(String args[]) {
		new passGen();
	}

	int genSymb() {
		switch (Math.abs(new Random().nextInt()) % 3) {
		case 0:
			return (Math.abs(new Random().nextInt()) % 10 + 48);
		case 1:
			return (Math.abs(new Random().nextInt()) % 26 + 97);
		case 2:
			return (Math.abs(new Random().nextInt()) % 26 + 65);
		}
		return 48;
	}

}
