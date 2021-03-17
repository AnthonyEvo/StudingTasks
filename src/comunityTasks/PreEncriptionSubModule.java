package comunityTasks;

import java.util.Random;

public class PreEncriptionSubModule {

	PreEncriptionSubModule() {

	}

	int[] encrypt(String messege) {
		int temp[] = new int[messege.length() + 1];

		int key = 100000 + Math.abs(new Random().nextInt() % 899999);

		temp[0] = key;

		for (int i = 1, key2 = 0; i < temp.length; i++) {
			key2 += messege.getBytes()[i - 1];
			temp[i] = key + key2;
		}

		return temp;
	}

	String dechipher(int messege[]) {

		String decMessege = "";

		for (int i = 1; i < messege.length; i++) {
			decMessege += (char) (messege[i] - messege[i - 1]);
		}

		return decMessege;
	}
}