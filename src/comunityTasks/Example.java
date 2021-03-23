package comunityTasks;

import javax.swing.*;
import java.awt.*;

class Display extends JPanel{
	
	JFrame window;
	double form[][];
	int scale = 5;
	
	Display(double[][] form) {
		
		this.form = form;
		
		window = new JFrame("Square");
		window.setSize(500, 500);
		window.setResizable(false);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		window.add(this);
		window.setVisible(true);
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintSquare(g);
	}
	
	private void paintSquare(Graphics g) {
		g.drawLine((int)((form[0][0] * scale) - 1), (int)(form[0][1] * scale), (int)((form[0][0] * scale) + 1), (int)(form[0][1] * scale));
		g.drawLine((int)(form[0][0]  * scale), (int)((form[0][1]  * scale) - 1), (int)(form[0][0]  * scale), (int)((form[0][1]  * scale) + 1));
		
		for(int i = 2; i < form.length; i++ ) {
			g.drawLine((int)(form[i - 1][0] * scale), (int)(form[i - 1][1] * scale), (int)(form[i][0] * scale), (int)(form[i][1]* scale));
		}
		
		g.drawLine((int)(form[form.length - 1][0] * scale), (int)(form[form.length - 1][1] * scale), (int)(form[1][0] * scale), (int)(form[1][1] * scale));
	}
}

public class Example {
	public static void main (String args[]) {
		Square square = new Square(25, 25);
		new Display(square.sendPoints());
	}
}

class Square {
	Point v[] = new Point[4];
	double sideLength = 10;
	Point axis = new Point(15, 15);

	double angle = 315;

	Square(double posX, double posY){
		v[0] = new Point(0, 0);
		v[1] = new Point(0, sideLength);
		v[2] = new Point(sideLength, sideLength);
		v[3] = new Point(sideLength, 0);
		
		axis.setGlobalX(posX);
		axis.setGlobalY(posY);
		
		changePosition();
	}

	void move(Point point) {
		double sideX = -axis.getLocalX() + point.getLocalX();
		double sideY = -axis.getLocalY() + point.getLocalY();
		double radius = Math.sqrt(Math.pow(sideX, 2) + Math.pow(sideY, 2));
		double localAngle = Math.atan(sideY / sideX);
		
		if(radius == 0) {
			point.setGlobalX(axis.getGlobalX());
			point.setGlobalY(axis.getGlobalY());
			System.out.println(point.getGlobalX() + " " + point.getGlobalY());
			return;
		}
		
		if(sideX >= 0 && sideY >= 0) {
			point.setGlobalX(axis.getGlobalX() + radius * Math.cos(localAngle + Math.toRadians(angle)));
			point.setGlobalY(axis.getGlobalY() + radius * Math.sin(localAngle + Math.toRadians(angle)));
		}
		else if(sideX < 0 && sideY >= 0) {
			point.setGlobalX(axis.getGlobalX() - radius * Math.cos(localAngle + Math.toRadians(angle)));
			point.setGlobalY(axis.getGlobalY() + radius * Math.sin(localAngle + Math.toRadians(angle)));
		}
		else if(sideX >= 0 && sideY < 0) {
			point.setGlobalX(axis.getGlobalX() + radius * Math.cos(localAngle + Math.toRadians(angle)));
			point.setGlobalY(axis.getGlobalY() - radius * Math.sin(localAngle + Math.toRadians(angle)));
		} 
		else if(sideX < 0 && sideY < 0) {
			point.setGlobalX(axis.getGlobalX() - radius * Math.cos(localAngle + Math.toRadians(angle)));
			point.setGlobalY(axis.getGlobalY() - radius * Math.sin(localAngle + Math.toRadians(angle)));
		}		
		System.out.println(point.getGlobalX() + " " + point.getGlobalY());
	}
	
	void changePosition() {
		
		for(int i = 0; i < v.length; i++) {
			move(v[i]);
		}
	}
	
	public double[][] sendPoints() {
		
		double temp[][] = new double[v.length + 1][2];
		temp[0][0] = axis.getGlobalX();
		temp[0][1] = axis.getGlobalY();
		
		for(int i = 1; i < temp.length; i ++) {
			temp[i][0] = v[i - 1].getGlobalX();
			temp[i][1] = v[i - 1].getGlobalY();
		}
		
		return temp;
	}
}

class Point {

	private double localX, localY, globalX, globalY;
	
	Point(double localX, double localY) {
		this.localX = localX;
		this.localY = localY;
	}

	public double getGlobalX() {return globalX;}

	public double getGlobalY() {return globalY;}

	void setGlobalX(double globalX) {this.globalX = globalX;}

	void setGlobalY(double globalY) {this.globalY = globalY;}

	public double getLocalX() {return localX;}

	public double getLocalY() {return localY;}
}

