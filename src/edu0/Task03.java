package edu0;

import java.io.IOException;
import java.util.Random;

public class Task03 {
	
	char A[] = new char[40];												//������ ��� ���������� ��������� ��������
	
	Task03() {
		
		System.out.println("����� ��������\n\n��� ��������������� �������� ������\n� ������� �������� ������� ���������� ��������,\n�� ����� ������!");
		fillCharArray();													//��������� ������ ���������� ���������
		subTextSearch();													//������ ������
	}
	
	public static void main(String args[]) {
		new Task03();
	}
	
	void fillCharArray() {													//����� - ��������� ��������� ��������
		for(int i = 0; i < A.length; i ++) {
			A[i] = (char) (Math.abs(new Random().nextInt()) % 4 + 65);		// ������ ����� ����� % ������ �������� � ��������� �� 1 �� 25
		}																	// ����� ������ ������ ���� �� ������ ���������� �������� ��� ��������� ��������
	}
	
	void subTextSearch() {													//����� ������ ��������
		
		System.out.print("������� ���� ������, �� ����� ���� ��������� ��������: ");
		
		byte[] buf = new byte[5];											//����� ��� ����� ���������� ������� 
		byte[] B = new byte[A.length];										//������ � ������� ������������ ���������
		byte[] key;															//������ � ��������� ������
		int keyLength = 0;													//������ �����, ����� ������� ���� ���� �� �������, ����� Exception
		
		try {
			System.in.read(buf);											//���� �����
		} catch (IOException e) {
			
		}
		
		for(int i = 0; i < buf.length; i++) {								//���������� ������ �����
			if((buf[i] > 64 && buf[i] < 92) || (buf[i] > 96 && buf[i] < 123) ) {
				keyLength++;
			}
		}
		
		key = new byte[keyLength];											//�������������� ������ ��� ����� � ������ �������
		
		for(int i = 0; i < keyLength; i++) {								//���������� ����
			key[i] = buf[i];
		}
		
		System.out.println();
		
		for(int i = 0, j = 0; i < A.length; ) {								//��� ���� ��������� ����� � �������� ��������
			
//			System.out.println(i + ". ��������� " + (char) A[i + j] + " � " + (char) key[j] + " ����. " + (char) (key[j] - 32) + " ������ �����: " + key.length);
			if(i + j == A.length - 1) break;
			
			if(A[i + j] == key[j] || A[i + j] == (key[j] - 32)) {			//���� ������ �������� �������������
				j++;														//���������� ��������� ��������� �������� �����
				
				
				if(j == key.length) {										//���� ����� ������� ���� � ��� ������� �������
					for(int k = j; k > 0; k--) {							//�� ���� ���� �������� ����������� ������ �������� � ����������� �������
						B[i + k - 1] = (byte) '*';
					}
					i++;
					j = 0;													//����� ����� ������ ������ 
				}
				
			} 
			else {
				i++;
				j = 0;
			}
			
		}
		
		for(int i = 0; i < A.length; i++) {									//��� ��� ����� ������� ����������
			System.out.print(A[i] + " ");
		}
			System.out.println();
		for(int i = 0; i < A.length; i++) {
			System.out.print((char)B[i] + " ");
		}
	}
}