package tasks;

import java.io.*;

public class SumModule {

	int count = 0, sPos, ePos, bestResult = 0;

	SumModule() {
		File input = new File("input.txt");
		File output = new File("output.txt");
		try (FileInputStream iS = new FileInputStream(input); FileOutputStream oS = new FileOutputStream(output)) {
			String line = "";
			int i = 0, j[] = new int[0];
			while (i != -1) {
				while ((i = iS.read()) != -1 && i != 32)
					line += (char) i;
				j = arrayExpander(j, 1, Integer.parseInt(line));
				line = "";
			}
			count = j.length;
			check(j);
			output.createNewFile();
			oS.write(("" + sPos + " " + ePos).getBytes());
		} catch (IOException Ex) {
			Ex.printStackTrace();
		}
	}

	synchronized void check(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			new Summ(i, arr);
		}
		try {
			wait();
		} catch (Exception Ex) {
		}
	}

	int[] arrayExpander(int[] expandableArray, int expandSize, int value) {
		int[] tempArray = new int[expandableArray.length + expandSize];
		for (int i = 0; i < expandableArray.length; i++) {
			tempArray[i] = expandableArray[i];
		}
		tempArray[expandableArray.length] = value;
		return tempArray;
	}

	synchronized void setBestResult(int value, int sPos, int ePos) {
		if (Math.abs(value) > Math.abs(bestResult)) {
			bestResult = value;
			this.sPos = sPos + 1;
			this.ePos = ePos + 1;
		}
		count--;
		if (count == 0)
			notify();
	}

	class Summ implements Runnable {
		int result = 0, pos;
		int arr[];

		Summ(int pos, int[] arr) {
			this.pos = pos;
			this.arr = arr;
			Thread th = new Thread(this, "Summ" + pos);
			th.start();
		}

		@Override
		public void run() {
			int ePos = 0, sPos = pos;
			for (int i = pos, j = 0; i < arr.length; i++) {
				j += arr[i];
				if (Math.abs(j) > Math.abs(result)) {
					result = j;
					ePos = i;
				}
			}
			System.out.println(result);
			setBestResult(result, sPos, ePos);
		}
	}
}