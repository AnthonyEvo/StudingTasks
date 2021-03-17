package edu;

public class Task_E {

	final String a = "*", b = "@";

	public static void main(String args[]) {
		new Task_E();
	}

	Task_E() {

		int i = 0;

		System.out.println(fillString(4));

		while (i < 7) {
			System.out.println(fillString(-3));
			i++;
		}

		System.out.println(fillString(4));

	}

	private String fillString(int param) {
		if (param > 0) {
			String suffix = "", mid = a;
			for (int i = 1; i < param; i++) {
				mid += a + a;
			}
			for (int i = 4; i > param; i--) {
				suffix += b;
			}

			return suffix + mid + suffix;
		} else {
			String suffix = "", mid = b;
			for (int i = -1; i > param; i--) {
				mid += b + b;
			}
			for (int i = -4; i < param; i++) {
				suffix += a;
			}
			return suffix + mid + suffix;
		}
	}
}
