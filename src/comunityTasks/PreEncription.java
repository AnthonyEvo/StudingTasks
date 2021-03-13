package comunityTasks;

import java.util.Random;

public class PreEncription {
	
	PreEncription() {
		for(int x: encrypt("Hello world!")) {
			System.out.print(x + " ");
		}
		
		System.out.print("\n" + dechipher(encrypt("Hello world!")));
	}
	
	public static void main(String Args[]) {
		new PreEncription();
	}
	
	int[] encrypt(String messege) {
		int temp[] = new int[messege.length() + 1];
		
		int key = 100000 + Math.abs(new Random().nextInt() % 899999);
		
		temp[0] = key;
		
		for(int i = 1, key2 = 0; i < temp.length; i++) {
			key2 += messege.getBytes()[i - 1];
			temp[i] = key + key2;
		}
		
		return temp;
	}
	
	String dechipher(int messege[]) {
		
		String decMessege = "";
		int key = messege[0];
		
		for(int i = 1; i < messege.length; i++) {
			decMessege += (char)(messege[i] - messege[i-1]);
		}
		
		return decMessege;
	}
}