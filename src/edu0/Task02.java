package edu0;

import java.util.Random;

public class Task02 {
	
	/*
	 * ������ �� �������� � ���������� ����������
	 * ���������� � ����, ����� ��������� ��� ������� � �������� ����������
	 * ����������� ������������� System.out-�
	 * ��� ���� ���� ������� ������ ������� ������� �������� � ������������� ������� ����� ����� ����� �������.
	 * ���� ������ ����� ������ ��� �������� ��������� ����� ������ ��� ������ � ����� % � ���� ���� ������ ��������,
	 * �� ��������� 10.
	 */
	
	int[] A = new int[30];	//������ ������� � ���������� �������
	
	Task02() {				//����������� ������ ������ ������
		fillArray();		//�� ������������ ����� ������� ��������� �������� ������
		System.out.println("����� ���������� �������� ����������: " + sort01() + "��\n");	//������ Sort01 � 02 ���������� ������ �� �������� ����������
		
		System.out.println("����� ���������� ���������� ����������: " + sort02() + "��");	//� ������� print ������ ��������� ��������
	}
	
	static public void main(String Args[]) {
		new Task02();		//��� ��������� ��������� ������ Task02
	}
	
	void fillArray() {		//����� �� ���������� ������� ���������� �������
		for(int i = 0; i < A.length; i++) {
			
			A[i] = Math.abs(new Random().nextInt() % 100);	//��� ������ ������, ����� ������� �������� ������ ��� ������
			
		}
	}
	
	String sort01() {		//�������� �����������
		
		long startTime = System.currentTimeMillis();		//��� ������� ����� ������ ������
		int[] B = new int[A.length];						//������ ������ � ������� �� ����� �����������
		int[] temp;											//�������� ������ ��� �������� ����������
		
		System.out.println("�������� ����������\n");
		
		for(int i = 0; i < A.length; i++) {					//�������� ���� ������������
			
			System.out.println("Step " + i + ":");
			
			if(i == 0) {
//				System.out.println("  Set B[" + i + "] to A[" + i + "]");
				B[0] = A[0];
				
			} 
			else 
			{
				if(B[i - 1] > A[i]) {						//������� ������ ����� ������������ �������� ������ ����������� � �������
//					System.out.println("  Set B[" + i + "] to A[" + i + "]");
					B[i] = A[i];
				} 
				else {										//� ��� ��� ��������� ��������� ������
					boolean flag = false;
					
					for(int j = i; j >= 0; j--) {			//���� � ������� ����� ���������� �� ������� ����� ��������� ����� ����� �������� ����� ��������
						
//						System.out.println("  Compare B[" + (j - 1) + "] and A[" + i + "]");
						
						if (j == 0) flag = true;			//����� � ������ ���� ���� ������ �� ������ ������� � �������� ����� �� � ���
						else if(B[j - 1] < A[i]) flag = false;		//��������� ���� ��������� �������� ���� ������ ������������� � ����� ��������� ������
						else flag = true;					//� ��� ��������� �� ���� ��������� �������
						
						if(flag) {							//��� ���������� ������ � ���������������� ������ � ������� ��� ���������
							temp = new int[(i  - j)];		//������ ������ ��������� �������
							
							for(int k = 0; k < (i- j); k++) {	//��������� � ����� �������� ������� ����� ����� ��������
//								System.out.println("  Moving B[" + (k + j - 1) + "] in to temp[" + k + "]");
								temp[k] = B[k + j];				//���� �� ���� �������� ��������
								
							}
							
//							System.out.println("  Set B[" + (j) + "] to A[" + i + "]");
							B[j] = A[i];					//���������� ����� ��������
							
							for(int k = 0; k < temp.length; k++) {		//���������� �������� �� �������
								
//								System.out.println("  Relising to B[" + (j + k + 1) + "] from temp[" + k + "]" );
								B[j + k + 1] = temp[k];		
								
							}
							
							temp = null;					
							break;							//����� ����� ���� �������� �� ������ ��� ����� ��� ����� ���������� ������ ���������
							
						} 
												
					}
				}
			}
			
			System.out.print("  Unsorted array:\t");
			for(int g = 0; g < A.length; g++) {
				System.out.print(A[g] + " ");
				
			}
			
			System.out.print("\n  Sorted array:\t\t");
			for(int g = 0; g < A.length; g++) {
				System.out.print(B[g] + " ");
				
			}
			
			System.out.println();
		}
		return (System.currentTimeMillis() - startTime) + "";		//��� ������������ ������� ������� ������������� ����� ��� �������������
	}
	
	String sort02() {									//���������� ��������� "����� ����������"
		long startTime = System.currentTimeMillis();	//������� ����� ������
		System.out.println("���������� ����������\n");
		int B[] = fastSort(A);							//��������� ����������� ����� fastSort ������� ��� ��������������� �������� ������
		
		System.out.print("\nUnsorted array:\t");		//������� ��������������� ������ ��� ���������
		for(int g = 0; g < A.length; g++) {
			System.out.print(A[g] + " ");
			
		}
		
		System.out.print("\nSorted array:\t");			//������� ������������� ������
		for(int g = 0; g < B.length; g++) {
			System.out.print(B[g] + " ");
			
		}
		System.out.println();
		return (System.currentTimeMillis() - startTime) + "";		//������ ������� ������� ������
	}
	
	int[] fastSort(int A[]) {							//����������� ����������� � ����������
		int B[] = new int[A.length]; 
		int maxValue = 0, minValue = Integer.MAX_VALUE, midValue;		//���������� ��� ����������� �������� ��������
		int aLength = 0, bLength = 0;					//���������� ��� ����������� ����� �������� ��������
		int[] a, b;										//�������� �������, ��������� �� �� ���������������
		
		System.out.println("Start sorting cicle");
		for(int i = 0; i < A.length; i++) {				//��� ������� ������������ � ����������� �������� � ����������� �������
			if(A[i] >= maxValue) maxValue = A[i];
			if(A[i] <= minValue) minValue = A[i];
		}
		
		midValue = (maxValue + minValue) / 2;			//������� ������� ��������
		
		System.out.println("  Mid Value is: " + midValue + ", max Value is: " + maxValue + ", min Value is: " + minValue);
		
		if(A.length > 2) {								//������ ���� ������ ������ 2
			System.out.println("  Array is in \"more then 2\" state.");
			for(int i = 0; i < A.length; i++) {			//������� ������ �������� ��������
				if(A[i] > midValue) aLength++;
				if(A[i] <= midValue) bLength++;
			}
			
			a = new int[aLength];						//��������� ��� �������
			b = new int[bLength];						//����� ����� ��� ���� �� ���� �������, �� ����� ��� ����������
			
			for(int i = 0, ac = 0, bc = 0; i < A.length; i++) {	//��� ������������� �������� �� ��������
				
				if(A[i] > midValue) {					//� ������ "��" ������ �������� ������� ���� ��������
					a[ac] = A[i];
					ac++;
				}
				
				if(A[i] < midValue) {					//� "bc" �������� ������� ����� � ���� ��������
					b[bc] = A[i];
					bc++;
				}
				
				if(A[i] == midValue) {					//����� ���� ������� �������� "<=" � ���������� �� ��� ��� �����������
					b[bc] = A[i];
					bc++;
				}
			}
			
			
			System.out.print("  Dividing: ");			//��� ��� ���������������� � ������� �� �������� ����������
			for(int g = 0; g < A.length; g++) {
				System.out.print(A[g] + " ");
				
			}
			System.out.print("\n  to A: ");
			
			for(int g = 0; g < a.length; g++) {
				System.out.print(a[g] + " ");
				
			}
			System.out.print("\n  and B: ");
			
			for(int g = 0; g < b.length; g++) {
				System.out.print(b[g] + " ");
				
			}
			System.out.println();
			
			if(a.length == A.length) {					//��� ��� "����" ����� � ������ ���� ��� �������� � ����������� ������� ����� �������� ��������
				return a;
			}
			if(b.length == A.length) {					//��� �� ����� � ����������� ���� ������, ��� ��� ����� ����� �������� �� ���������
				return b;
			}
			
			
			int[] aBuf = fastSort(a);					//������ ��������
			int[] bBuf = fastSort(b);					//��������� ���� �������� ������� ��� ������� �� ���� �� ���������
			
			for(int i = 0; i < A.length; i++) {			// ��� ������������� ���������� ����������
				if(i < a.length) {
					B[i] = aBuf[i];						//��� ��� � �������� ������ "�" �� �������� ���������� ��������
				} else {
					B[i] = bBuf[i - aBuf.length];		//� � "b" ����������, ���������� �� � ������ �������, �.�. �� �������� � ������
				}
			}
			return B;									//���������� ������������ ������
		}
		
		else if(A.length == 2) {						//� ������ � ����� ���������� ��� ������, ������� �� � �������� ������� ������� ����� �������
			System.out.println("  Array is in \"equals 2\" state");
			if(A[0] <= A[1]) {
				B[0] = A[1];
				B[1] = A[0];
			} else if(A[0] > A[1]){
				B[0] = A[0];
				B[1] = A[1];
			}
			
			System.out.print("  Returning result: ");
			for(int g = 0; g < B.length; g++) {
				System.out.print(B[g] + " ");
				
			}
			System.out.println();
			
			return B;									//���������� ������������ ������
		}
		
		else {											//��� ������ ����� �������� ��������� ���� � ��� �� ����� �� � ��� ����������
			System.out.print("  Array is in \"less then 2\" state");
			System.out.println("  Returning result: " + A[0]);
			return A;									//���������� ������������ ������
		}
	}
}
