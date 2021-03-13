package comunityTasks;

import java.math.BigInteger;

public class RSAEncryptingModule {
	
	private BigInteger P, Q, E, N, D, F;
	
	RSAEncryptingModule() {
		P = new BigInteger("462183876001"); Q = new BigInteger("423568976003");
		N = calculateN();
		E = calculateE();
		D = calculateD();
	}
	
	private BigInteger calculateE() {	//���������� ��������� �����
		
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
	
	private BigInteger calculateD() {	//���������� ��������� �����
		
		BigInteger temp = F;
		
		BigInteger stepF = ((E.multiply(temp.subtract(BigInteger.valueOf(1)))).mod(F)).subtract(E.multiply(temp)).mod(F);
		BigInteger iCount = F.divide(stepF);
		
		for( ;!(E.multiply(temp)).mod(F).equals(new BigInteger("1")); ) {

			if(E.multiply(temp).mod(F).compareTo(F) < 0 && E.multiply(temp).mod(F).compareTo(stepF) > 0) {
				temp = temp.subtract(BigInteger.valueOf(1));
			}
			else {
				temp = temp.subtract(iCount);
			}
		
			try {
				Thread.sleep(250);
			}
			catch(Exception Ex) {
				
			}
		}
		
		// (D * E) mod F = 1
		
		return temp;
	}
	
	private BigInteger calculateN() {	//����������� ������������ ������� �����
		return P.multiply(Q);
	}
	
	public String encrypt(String messege) {
		
		byte temp[] = messege.getBytes();
		String encMessege = "";
		
		for(int i = 0; i < temp.length; i++) {
			String tempMessege = (BigInteger.valueOf(temp[i]).modPow(E, N)) + " ";
			
			
			
			
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
