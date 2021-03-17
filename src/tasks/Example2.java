package tasks; //Пакет

public class Example2 { // Класс в пакете

	Example2() { // Конструктор класса
		System.out.print(mathOp(mathOp(mathOp(mathOp(25, '+', 1), '/', 2), '*', 15), '-', 4));
	}

	static public void main(String args[]) { // Метод акссессор

		Example2 core = new Example2();
	}

	int mathOp(int a, char c, int b) {
		if (c == '+')
			return a + b;
		if (c == '-')
			return a - b;
		if (c == '*')
			return a * b;
		if (c == '/')
			return a / b;
		return 0;
	}

	// (25 + 1) / 2 * 15 - 4
}