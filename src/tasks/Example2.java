package tasks; //�����

public class Example2 { // ����� � ������

	Example2() { // ����������� ������
		System.out.print(mathOp(mathOp(mathOp(mathOp(25, '+', 1), '/', 2), '*', 15), '-', 4));
	}

	static public void main(String args[]) { // ����� ���������

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