package edu0;

import java.io.IOException;
import java.util.Random;

public class Task01 {
	
	int[] A = new int[20];
	int request;
	
	Task01() {
		fillArray();
		System.out.print("������� ����� �� 0 �� 9: ");
				
		try {
				request = System.in.read() - 48;
				
				System.out.println(search01());
				System.out.println(search02());
				System.out.println(search03());
		} 
		catch (Exception Ex) {
			
		}
	}
	
	void fillArray() {
		for(int i = 0; i < A.length; i++) {
			
			A[i] = Math.abs(new Random().nextInt() % 10);
			System.out.print(A[i]);
		}
		System.out.println();
	}
	
	static public void main(String Args[]) {
		new Task01();
	}
	
	String search01() throws IOException {		// ������� ������ ��������� �������� �����
		
		String answer = "";
		System.out.print("����� �� ���������� ���������. ���������: ");
		for (int i = 0; i < A.length; i++) {
			if(A[i] == (int) request) {
				answer = ((i + 1) + " ");
			}
		}
		
		return answer;
	}
	
	String search02() throws IOException { 		// ������� ��������� ��������� �������� ����� 
		
		String answer = "";
		System.out.print("����� �� ������� ���������. ���������: ");
		for (int i = 0; i < A.length; i++) {
			if(A[i] == (int) request) {
				answer = ((i + 1) + " ");
				break;
			}
		}
		
		return answer;
	}
	
	String search03() throws IOException {		// ������� ��� ��������� �������� �����
		
		String answer = "";
		System.out.print("����� �� ���� ����������. ���������: ");		
		for (int i = 0; i < A.length; i++) {
			if(A[i] == (int) request) {
				answer += ((i + 1) + " ");
			}
		}
		
		if(answer == "") return "No matches";
		else return answer;
	}
}
