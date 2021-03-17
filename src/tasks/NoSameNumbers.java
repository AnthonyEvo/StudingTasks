package tasks;

import java.io.*;

public class NoSameNumbers {
	NoSameNumbers() {
		long x = System.currentTimeMillis();
		File input = new File("Input.txt");
		File output = new File("output.txt");
		try (FileInputStream iS = new FileInputStream(input); FileOutputStream oS = new FileOutputStream(output)) {
			String line = "";
			int i = 0;
			while ((i = iS.read()) != -1)
				line += (char) i;
			output.createNewFile();
			oS.write(("" + check(Integer.parseInt(line))).getBytes());
		} catch (IOException Ex) {
			Ex.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - x);
	}

	long check(int num) {
		for (int i = 0, j = 9, k = 9, s = 0; i < 10; k *= (9 - i), j += k, i++) {
			s = j;
			if (s >= num) {
				int mod = 0, subRawPoint;
				long rawPoint;
				do {
					if (s - (k / 9) * (10 - mod) <= num && s - (k / 9) * (9 - mod) >= num) {
						rawPoint = (long) (Math.pow(10, i + 1) - (Math.pow(10, i + 1) / 10) * (10 - mod));
						subRawPoint = s - (k / 9) * (10 - mod);
						break;
					}
					mod++;
				} while (true);
				while (subRawPoint < num) {

					int c = 0;
					boolean flag = true;
					char arr[] = ("" + rawPoint).toCharArray();
					for (char x : arr) {
						for (int y = c + 1; y < arr.length; y++) {
							if (x == arr[y]) {
								flag = false;
								break;
							}
						}
						c++;
					}
					if (flag)
						subRawPoint++;

					++rawPoint;
				}
				return rawPoint - 1;
			}
		}
		return -1;
	}
}
