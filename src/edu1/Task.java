package edu1;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;

public class Task implements ActionListener {

	JFrame window;
	Display graph = new Display(dType.GraphFrame);
	Display bar01;
	Display bar02;
	Display bar03;

	Task(dType DType) {
		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLayout(new FlowLayout());

		switch (DType) {

		case InputT1Frame:
			window.add(graph);
			bar01 = new Display(dType.InputT1Frame);
			bar01.setButton.addActionListener(this);
			window.setTitle("Решение задачи интерполирования");
			window.add(bar01);
			break;

		case InputT2Frame:
			window.add(graph);
			bar02 = new Display(dType.InputT2Frame);
			bar02.setButton.addActionListener(this);
			window.setTitle("Решение задачи №2, Вариант №9");
			window.add(bar02);
			break;

		case InputT3Frame:
			window.add(graph);
			bar03 = new Display(dType.InputT3Frame);
			bar03.setButton.addActionListener(this);
			window.setTitle("Решение задачи №3, Вариант №9");
			window.add(bar03);
			break;

		default:
			break;
		}

		window.setVisible(true);
	}

	public static void main(String args[]) {

		switch (new String("task01")) {
		case "task01":
			new Task(dType.InputT1Frame);
			break;
		case "task02":
			new Task(dType.InputT2Frame);
			break;
		case "task03":
			new Task(dType.InputT3Frame);
			break;
		default:
			new Task(dType.DefaultFrame);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent Event) {

		if (Event.getActionCommand() == "Задать точки") {

			double points[][] = new double[3][2];

			points[0][0] = Double.parseDouble(bar01.field01.getText());
			points[0][1] = Double.parseDouble(bar01.field02.getText());

			points[1][0] = Double.parseDouble(bar01.field03.getText());
			points[1][1] = Double.parseDouble(bar01.field04.getText());

			points[2][0] = Double.parseDouble(bar01.field05.getText());
			points[2][1] = Double.parseDouble(bar01.field06.getText());

			graph.dataInput(points, dType.InputT1Frame);
			graph.flag01 = true;
		} else if (Event.getActionCommand() == "Построить график") {

			graph.dataInput(new double[][] { { -5, 0 }, { 5, 0 } }, dType.InputT2Frame);
			graph.flag01 = true;
		}

		else if (Event.getActionCommand() == "Показать решение") {
			double[][] temp = { { 2, 1, 1, 1, 2 }, { 2, 2, 2, 3, 1 }, { 2, 2, 3, 4, 0 }, { 2, 2, 3, 5, -1 }, };
			graph.dataInput(temp, dType.InputT3Frame);
			graph.flag01 = true;
		}
	}
}

class Display extends JPanel {

	dType DType, TType;

	public JTextField field01, field02, field03, field04, field05, field06;
	public JButton setButton;
	JLabel label;

	double step = 0.1;
	double minXValue, maxXValue;
	double[][] input;

	boolean flag01 = false;

	int scrnDivision, scale = 20, sShift = 275;

	Display(dType DType) {

		switch (DType) {

		case GraphFrame:
			this.DType = DType;
			this.setPreferredSize(new Dimension(550, 550));
			this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
			break;

		case InputT1Frame:
			this.DType = DType;
			this.setPreferredSize(new Dimension(200, 550));
			this.setBorder(BorderFactory.createLineBorder(Color.darkGray));

			field01 = new JTextField();
			field01.setPreferredSize(new Dimension(70, 20));
			field01.setText("-1");
			this.add(new JLabel("x1:"));
			this.add(field01);

			field02 = new JTextField();
			field02.setPreferredSize(new Dimension(70, 20));
			field02.setText("4");
			this.add(new JLabel("y1:"));
			this.add(field02);

			field03 = new JTextField();
			field03.setPreferredSize(new Dimension(70, 20));
			field03.setText("2");
			this.add(new JLabel("x2:"));
			this.add(field03);

			field04 = new JTextField();
			field04.setPreferredSize(new Dimension(70, 20));
			field04.setText("3");
			this.add(new JLabel("y2:"));
			this.add(field04);

			field05 = new JTextField();
			field05.setPreferredSize(new Dimension(70, 20));
			field05.setText("5");
			this.add(new JLabel("x3:"));
			this.add(field05);

			field06 = new JTextField();
			field06.setPreferredSize(new Dimension(70, 20));
			field06.setText("4");
			this.add(new JLabel("y3:"));
			this.add(field06);

			this.add(setButton = new JButton("Задать точки"));

			break;
		case InputT2Frame:
			this.DType = DType;
			this.setPreferredSize(new Dimension(200, 550));
			this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
			this.add(setButton = new JButton("Построить график"));

			label = new JLabel(
					"<html>Задание №2<br> \"Численное решение <br>нелинейных уравнений\"<br> Используется метод<br> деления отрезков<br> пополам.<br> Рабочая формула:<br>\n X^3 + 6X - 1 = 0");
			label.setPreferredSize(new Dimension(180, 180));
			this.add(label);
			break;

		case InputT3Frame:
			this.DType = DType;
			this.setPreferredSize(new Dimension(200, 550));
			this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
			this.add(setButton = new JButton("Показать решение"));

			label = new JLabel(
					"<html>Задание №3<br> \"Решение СЛАУ<br>методом Гауса\"<br> Изначальный массив<br> и операции над ним<br> отображаются после запуска\n");
			label.setPreferredSize(new Dimension(180, 180));
			this.add(label);
			break;

		default:
			break;
		}
	}

	public void dataInput(double[][] input, dType DType) {
		this.input = input;
		this.repaint();

		TType = DType;

		minXValue = Double.MAX_VALUE;
		maxXValue = Double.MIN_VALUE;

		for (int i = 0; i < input.length; i++) {
			if (maxXValue < input[i][0])
				maxXValue = input[i][0];
			if (minXValue > input[i][0])
				minXValue = input[i][0];
		}
	}

	@Override
	public void paintComponent(Graphics G) {

		super.paintComponent(G);
		if (flag01) {
			drawGraph(G);
			drawPoints(G);
			drawText(G);
		}
	}

	private void drawGraph(Graphics G) {
		if (TType == dType.InputT2Frame || TType == dType.InputT1Frame) {
			G.setColor(Color.RED);
			G.drawLine(sShift / 10, sShift, sShift * 2 - (sShift / 10), sShift);
			G.setColor(Color.BLUE);
			G.drawLine(sShift, sShift / 10, sShift, sShift * 2 - (sShift / 10));

			for (double i = minXValue - 1; i < maxXValue + 1; i += step) {
				if (TType == dType.InputT1Frame) {
					G.drawLine((int) ((i * scale) + sShift), (int) (sShift - (scale * new EqIMN(input, i).getY())),
							(int) (((i + step) * scale) + sShift),
							(int) (sShift - (scale * new EqIMN(input, i + step).getY())));
				}

				if (TType == dType.InputT2Frame) {
					G.drawLine((int) ((i * scale) + sShift), (int) (sShift - (scale * new EqCube(i).getY())),
							(int) (((i + step) * scale) + sShift),
							(int) (sShift - (scale * new EqCube(i + step).getY())));
				}
			}
		}
	}

	private void drawPoints(Graphics G) {
		if (TType == dType.InputT2Frame || TType == dType.InputT1Frame) {
			for (int i = 0; i < input.length; i++) {
				G.setColor(Color.BLACK);
				G.drawOval((int) ((input[i][0] * scale - 1) + sShift), (int) (sShift - (input[i][1] * scale + 1)), 3,
						3);
				G.drawOval((int) ((input[i][0] * scale - 3) + sShift), (int) (sShift - (input[i][1] * scale + 3)), 7,
						7);
			}
		}
	}

	private void drawText(Graphics G) {
		if (TType == dType.InputT2Frame) {
			G.setColor(Color.BLACK);
			G.drawString(segDivision(), 5, 545);
		} else if (TType == dType.InputT3Frame) {

			EqSystem system = new EqSystem(input);

			for (int i = 0, k = 0; i < 5; i++) {
				String message = "";

				for (int j = 0; j < system.getMessage(i).length(); j++) {
					char ch = system.getMessage(i).toCharArray()[j];

					if (ch == '@') {
						G.drawString(message, 5, 20 + i + k * 12);
						k++;
						message = "";
					} else {
						message += ch;
					}

				}
				k++;
			}

			G.drawString(system.getMessage(4), 5, 400);
		}

	}

	String segDivision() {

		double accuracy = 0.1;

		if (new EqCube(0).getY() > 0) {

			for (double i = 0;; i -= accuracy) {
				if (new EqCube(i - accuracy).getY() <= 0) {
					return halfDivision(i - accuracy, i, 0);

				}
			}
		} else {

			for (double i = 0;; i += accuracy) {
				if (new EqCube(i + accuracy).getY() >= 0) {
					return halfDivision(i, i + accuracy, 0);
				}
			}
		}

	}

	String halfDivision(double minX, double maxX, int iCount) {

		double _minX = minX, _maxX = maxX;

		if (new EqCube(minX + (maxX - minX) / 2).getY() > 0) {
			_minX = minX;
			_maxX -= (maxX - minX) / 2;
		} else {
			_minX += (maxX - minX) / 2;
			_maxX = maxX;
		}

		if (Math.abs(maxX - minX) < 0.0001) {
			return "Корень X1: ~" + ((minX + (-minX + maxX)) - (minX + (-minX + maxX)) % 0.00001)
					+ " \nКоличество итераций: " + iCount++;
		} else {
			return halfDivision(_minX, _maxX, iCount += 1);
		}

	}
}

enum dType {
	GraphFrame, DefaultFrame, InputT1Frame, InputT2Frame, InputT3Frame;
}

class EqIMN {

	double Y;

	public EqIMN(double[][] input, double x) {
		Y = input[0][1]
				+ devSubstr(new double[] { input[0][0], input[1][0] }, new double[] { input[0][1], input[1][1] })
						* (x - input[0][0])
				+ devSubstr(new double[] { input[0][0], input[1][0], input[2][0] },
						new double[] { input[0][1], input[1][1], input[2][1] }) * (x - input[0][0]) * (x - input[1][0]);
	}

	public double getY() {
		return Y;
	}

	String textAssembling() {
		return "";
	}

	double devSubstr(double x[], double y[]) {
		if (y.length > 2 || x.length > 2) {
			double[] y1 = new double[y.length - 1], y2 = new double[y.length - 1];
			double[] x1 = new double[x.length - 1], x2 = new double[x.length - 1];
			for (int i = 0; i < y.length - 1; i++) {
				y1[i] = y[i + 1];
				y2[i] = y[i];

				x1[i] = x[i + 1];
				x2[i] = x[i];
			}
			return (devSubstr(x1, y1) - devSubstr(x2, y2)) / (x[x.length - 1] - x[0]);
		} else {
			return (y[1] - y[0]) / (x[1] - x[0]);
		}
	}

}

class EqIML {

	double Y;

	EqIML(double[][] input, double x) {

		double x1 = input[0][0], x2 = input[1][0], x3 = input[2][0], y1 = input[0][1], y2 = input[1][1],
				y3 = input[2][1];

		double a;
		double b;
		double c;

		a = y1 / ((x1 - x2) * (x1 - x3)) + y2 / ((x2 - x1) * (x2 - x3)) + y3 / ((x3 - x1) * (x3 - x2));
		b = -y1 * (x2 + x3) / ((x1 - x2) * (x1 - x3)) - y2 * (x1 + x3) / ((x2 - x1) * (x2 - x3))
				- y3 * (x1 - x2) / ((x3 - x1) * (x3 - x2));
		c = y1 * x2 * x3 / ((x1 - x2) * (x1 - x3)) + y2 * x1 * x3 / ((x2 - x1) * (x2 - x3))
				+ y3 * x1 * x2 / ((x3 - x1) * (x3 - x2));

		Y = (a * x * x) + (b * x) + c;

	}

	public double getY() {
		return Y;
	}

	String textAssembling() {
		return "";
	}
}

class EqCube {
	double Y;

	EqCube(double input[][], double x) {
		Y = Math.pow(x, 3) + 6 * x - 1;
	}

	EqCube(double x) {
		Y = Math.pow(x, 3) + 6 * x - 1;
	}

	double getY() {
		return Y;
	}
}

class EqSystem {

	String[] message;

	EqSystem(double[][] input) {
		message = mGausSystem(input);
	}

	public String getMessage(int page) {
		if (page < message.length) {
			return message[page];
		} else {
			return message[message.length - 1];
		}
	}

	String[] mGausSystem(double[][] input) {

		double temp[][] = input;
		double x1, x2, x3, x4;
		String[] message = new String[5];

		message[0] = showArray(temp);

		for (int i = 0; i < temp[0].length; i++) {
			temp[3][i] -= temp[2][i];
		}

		message[1] = showArray(temp);

		for (int i = 0; i < temp[0].length; i++) {
			temp[2][i] -= temp[1][i];
		}

		message[2] = showArray(temp);

		for (int i = 0; i < temp[0].length; i++) {
			temp[1][i] -= temp[0][i];
		}

		x4 = (temp[3][4]) / temp[3][3];
		x3 = (temp[2][4] - x4 * temp[2][3]) / temp[2][2];
		x2 = (temp[1][4] - (x3 * temp[1][2] + x4 * temp[1][3])) / temp[1][1];
		x1 = (temp[0][4] - (x2 * temp[0][1] + x3 * temp[0][2] + x4 * temp[0][3])) / temp[0][0];

		message[3] = showArray(temp);

		message[4] = "X1: " + x1 + ", X2: " + x2 + ", X3: " + x3 + ", X4: " + x4;

		return message;
	}

	String showArray(double[][] array) {

		String message = "";

		for (int i = 0, j = 0; j < array.length; i++) {

			message += (array[j][i] + " ");

			if (i == array[0].length - 1) {
				message += "@";
				j++;
				i = -1;
			}
		}
		return message;

	}
}
