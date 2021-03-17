package edu;

public class Task_1 {

	String c = "";

	void getKeyboardInput() {

		System.out.print("Enter your name: ");

		for (int i = 0; i != 13;) {

			try {
				i = System.in.read();
			}

			catch (Exception Ex) {
				break;
			}

			c += (char) i;
		}

		System.out.print("Hello " + c + "!");
	}

	int getSymbol() {
		return (' ');
	}

	public static void main(String args[]) {
		new Task_1().getKeyboardInput();
	}
}
