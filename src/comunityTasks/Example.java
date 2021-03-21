package comunityTasks;

public class Example {
	public static void main (String args[]) {
		new Square(0, 0);
	}
}

class Square {
	Point v[] = new Point[4];

	Point axis = new Point(0, 0);

	double angle = 14;

	Square(double posX, double posY){
		v[0] = new Point(0, 0);
		v[1] = new Point(0, 3);
		v[2] = new Point(3, 3);
		v[3] = new Point(3, 0);
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
}

class Point {

	double localX, localY, globalX, globalY;
	
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