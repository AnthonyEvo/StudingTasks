package edu0;

import java.util.Random;

public class Task04 {

	Worker office[] = new Worker[10];

	Task04() {

		System.out.println(
				"Работа с объектами\n\nпо условию у начальника зарплатa в двое выше чем у обычного сотрудника\nразбежка в зарплатах - 150$\n");
		fillOffice();
	}

	static public void main(String args[]) {
		new Task04();
	}

	void fillOffice() {
		for (int i = 0; i < office.length - 1; i++) {
			office[i] = new Worker(getRandomName(), getRandomBirthDay(), getRandomMonthPay());
		}
		office[office.length - 1] = new Supervisor(getRandomName(), getRandomBirthDay(), getRandomMonthPay());

		System.out.println("\nПовышаем зарплату на 50 процентов\n");

		for (int i = 0; i < office.length; i++) {
			office[i].increaceMonthPay(0.5);
		}

	}

	String getRandomName() {
		String firstName[] = { "Bob", "Lusy", "Edd", "Sarah", "Tom", "Jane", "Phill", "Aliss", "John", "Anna" };
		String lastName[] = { "Brown", "Shepard", "Williams", "Smith", "Collins", "Stone", "White", "Black", "Cotton",
				"Shefild" };

		return firstName[Math.abs(new Random().nextInt() % 10)] + " " + lastName[Math.abs(new Random().nextInt() % 10)];
	}

	String getRandomBirthDay() {

		return (Math.abs(new Random().nextInt()) % 27 + 1) + "." + (Math.abs(new Random().nextInt()) % 12 + 1) + "."
				+ (Math.abs(new Random().nextInt()) % 20 + 1980);
	}

	int getRandomMonthPay() {
		return Math.abs(new Random().nextInt()) % 150 + 250;
	}
}

class Worker {
	String name;
	String birthday;
	int monthPay;

	Worker(String name, String birthday, int monthPay) {
		this.name = name;
		this.birthday = birthday;
		this.monthPay = monthPay;
		System.out.println(message());
	}

	void increaceMonthPay(double size) {
		monthPay *= (1 + size);
		System.out.println("Name: " + name + " month pay increace to: " + monthPay + "$");
	}

	String message() {
		return "Name: " + name + " - worker, births at " + birthday + " month pay: " + getMonthPay() + "$";
	}

	int getMonthPay() {
		return monthPay;
	}
}

class Supervisor extends Worker {

	int monthPayCoeficient = 2;

	Supervisor(String name, String birthday, int monthPay) {
		super(name, birthday, monthPay);
		super.name += " (supervisor)";
		super.monthPay = monthPay * monthPayCoeficient;
	}

	@Override
	String message() {
		return "Name: " + name + " - supervisor, births at " + birthday + " month pay: " + getMonthPay() * 2 + "$";
	}
}
