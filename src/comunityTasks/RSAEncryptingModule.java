package comunityTasks;

import java.math.BigInteger;

public class RSAEncryptingModule {
	
	BigInteger P, Q, E, N, D, F;
	
	RSAEncryptingModule() {
		
		P = new BigInteger("462183876001"); Q = new BigInteger("423568976003");
		N = calculateN();
		E = calculateE();
		D = calculateD();
		
		System.out.println("(" + F + ")");
		System.out.println("(" + E + ", " + N + ")");
/*		System.out.println("(" + D + ", " + N + ")");
		
		String temp = encrypt("error");
		
		System.out.println(temp);
		System.out.println(decypher(temp));*/
		
	}
	
	public static void main(String Args[]) {
		new RSAEncryptingModule();
	}
	
	BigInteger calculateE() {	//Вычисление открытого ключа
		
		F = (P.subtract(BigInteger.valueOf(1))).multiply(Q.subtract(BigInteger.valueOf(1)));
		BigInteger[] simpleNums = new BigInteger[5];
		BigInteger temp = F;
		
		for(int i = 0; i < 5; ) {
			
			temp = temp.subtract(BigInteger.valueOf(1));
			
			if(temp.isProbablePrime(1) && temp.compareTo(F.divide(BigInteger.valueOf(2))) > 0 ) {
				simpleNums[i] = temp;
				i++;
			}			
		}
		
		return simpleNums[(int) Math.abs(Math.random() * 4)];
	}
	
	BigInteger calculateD() {	//Вычисление закрытого ключа
		BigInteger temp = F.multiply(BigInteger.valueOf((int)(Math.random() * 100))).divide(BigInteger.valueOf(100));
		
		do {
			temp = temp.subtract(BigInteger.valueOf(1));
			System.out.println((E.multiply(temp)).mod(F) + "");
			
			try {
				Thread.sleep(250);
			}
			catch(Exception Ex) {
				
			}
		}
		while(!(E.multiply(temp)).mod(F).equals(new BigInteger("1")));
		
		
		
		// (D * E) mod F = 1
		
		return temp;
	}
	
	BigInteger calculateN() {	//Расчитываем произведение простых чисел
		return P.multiply(Q);
	}
	
	public String encrypt(String messege) {
		
		byte temp[] = messege.getBytes();
		String encMessege = "";
		
		for(int i = 0; i < temp.length; i++) {
			System.out.print(temp[i] + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < temp.length; i++) {
			encMessege += (BigInteger.valueOf(temp[i]).modPow(E, N)) + " ";
		}
		
		return encMessege;
	}
	
	public String decypher(String messege) {
		String temp[] = new String[5];
		String decMessege = "";
		
		for(int i = 0; i < temp.length; i++) {
			String part = messege.substring(0, messege.indexOf(" "));
			messege = messege.substring(messege.indexOf(" ") + 1);
			
			decMessege += (new BigInteger(part).modPow(D, N)) + " ";
		}
		
		return decMessege;
	}
	
}
