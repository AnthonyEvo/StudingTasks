package comunityTasks;

import java.math.BigInteger;

public class RSAEncryptionSubModule {	// RSA �������� ������������� � ������������(�����������) �����, �� ����� ����������� ���  �� ������ ��� java.security

	private BigInteger P, Q, E, N, D, F;	// ���������� ���������� ��� ���� ����� ����� ���� � ���������� ��������

	RSAEncryptionSubModule() {	
		P = new BigInteger("462183876001");	// ������� ����� 1
		Q = new BigInteger("423568976003");	// ������� ����� 2
		N = calculateN();	// ������������� P � Q (����������� �����) ����� ����� ������
		E = calculateE();	// ��������� ����
		D = calculateD();	// �������� ����
		
		//TODO ����� ��������� ��������� ��� ������������ � ����� ������� �������� ������������
	}

	private BigInteger calculateE() { // ���������� ��������� �����

		F = (P.subtract(BigInteger.valueOf(1))).multiply(Q.subtract(BigInteger.valueOf(1)));
		BigInteger[] simpleNums = new BigInteger[5];
		BigInteger temp = F;

		for (int i = 0; i < 5;) {
			temp = temp.subtract(BigInteger.valueOf(1));

			if (temp.isProbablePrime(1) && temp.compareTo(F.divide(BigInteger.valueOf(2))) > 0) {
				simpleNums[i] = temp;
				i++;
			}
		}
		return simpleNums[(int) Math.abs(Math.random() * 4)];
	}

	private BigInteger calculateD() { // ���������� ��������� �����

		BigInteger temp = F;

		BigInteger stepF = ((E.multiply(temp.subtract(BigInteger.valueOf(1)))).mod(F)).subtract(E.multiply(temp)).mod(F);
		BigInteger iCount = F.divide(stepF);
		
		for (; !(E.multiply(temp)).mod(F).equals(new BigInteger("1"));) {

			if (E.multiply(temp).mod(F).compareTo(F) < 0 && E.multiply(temp).mod(F).compareTo(stepF) > 0) { // (D * E)mod F = 1
				temp = temp.subtract(BigInteger.valueOf(1));
			} else {
				temp = temp.subtract(iCount);
			}
		}
		return temp;
	}

	private BigInteger calculateN() { // ����������� ������������ ������� �����
		return P.multiply(Q);
	}

	public String encrypt(int messege[]) { // ������� ��������� ��� ����� ��� ���������� ������� ������ �����
		String encMessege = "";

		for (int i = 0; i < messege.length; i++) {
			String tempMessege = (BigInteger.valueOf(messege[i]).modPow(E, N)) + "";

			for (int j = tempMessege.length(); j < (N + "").length(); j++) { // ��������� ������ ������ � ������ ���� ������������� �������� ������ ��� ������ N
				tempMessege = "0" + tempMessege;
			}
			encMessege += tempMessege;
		}
		return encMessege;
	}

	public int[] decypher(String messege) {
		int temp[] = new int[messege.length() / (N + "").length()];
		String tempMessege = messege;

		for (int i = 0; i < temp.length; i++) {
			String part = tempMessege.substring(0, (N + "").length());
			tempMessege = tempMessege.substring((N + "").length());

			temp[i] = (new BigInteger(part).modPow(D, N)).intValue();
		}

		return temp;
	}
}
