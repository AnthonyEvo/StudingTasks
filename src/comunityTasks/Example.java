package comunityTasks;

public class Example {
	public static void main(String args[]) {
		new Square(0, 0);
	}

}

class Square {
	Point v[] = new Point[4];

	Point axis = new Point(0, 0);

	Square(double posX, double posY) {
		v[0] = new Point(0, 0);
		v[1] = new Point(0, 3);
		v[2] = new Point(3, 0);
		v[3] = new Point(3, 3);

		axis.setGlobalX(posX);
		axis.setGlobalY(posY);

		changePosition();
	}

	void changePosition() {
		for (int i = 0; i < v.length; i++) {
			v[i].setGlobalX(axis.getGlobalX() + v[i].getLocalX());
			v[i].setGlobalY(axis.getGlobalY() + v[i].getLocalY());
			System.out.println(v[i].getGlobalX() + " " + v[i].getGlobalY());
		}
	}
}

class Point {

	double localX, localY, globalX, globalY;

	Point(double localX, double localY) {
		this.localX = localX;
		this.localY = localY;
	}

	public double getGlobalX() {
		return globalX;
	}

	public double getGlobalY() {
		return globalY;
	}

	void setGlobalX(double globalX) {
		this.globalX = globalX;
	}

	void setGlobalY(double globalY) {
		this.globalY = globalY;
	}

	public double getLocalX() {
		return localX;
	}

	public double getLocalY() {
		return localY;
	}
}