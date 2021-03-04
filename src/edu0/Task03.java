package edu0;

import java.io.IOException;
import java.util.Random;

public class Task03 {
	
	char A[] = new char[40];												//Массив для генератора случайных символов
	
	Task03() {
		
		System.out.println("Поиск подстрок\n\nДля показательности работает только\nс первыми четырьмя буквами латинского алфавита,\nно можно больше!");
		fillCharArray();													//Заполняем массив случайными символами
		subTextSearch();													//Запуск поиска
	}
	
	public static void main(String args[]) {
		new Task03();
	}
	
	void fillCharArray() {													//метод - генератор случайных символов
		for(int i = 0; i < A.length; i ++) {
			A[i] = (char) (Math.abs(new Random().nextInt()) % 4 + 65);		// первое число после % можнно изменять в диапазоне от 1 до 25
		}																	// будет давать меньше букв от начала латинского алфавита для упрощения проверки
	}
	
	void subTextSearch() {													//метод поиска подстрок
		
		System.out.print("Введите ключ поиска, не более пяти латинских символов: ");
		
		byte[] buf = new byte[5];											//Буфер для ввода поискового запроса 
		byte[] B = new byte[A.length];										//Массив в который записываются звездочки
		byte[] key;															//массив с поисковым ключем
		int keyLength = 0;													//Длинна ключа, равна единице даже если не введешь, иначе Exception
		
		try {
			System.in.read(buf);											//Ввод ключа
		} catch (IOException e) {
			
		}
		
		for(int i = 0; i < buf.length; i++) {								//Определяем длинну ключа
			if((buf[i] > 64 && buf[i] < 92) || (buf[i] > 96 && buf[i] < 123) ) {
				keyLength++;
			}
		}
		
		key = new byte[keyLength];											//Инициализируем массив для ключа с нужной длинной
		
		for(int i = 0; i < keyLength; i++) {								//записываем ключ
			key[i] = buf[i];
		}
		
		System.out.println();
		
		for(int i = 0, j = 0; i < A.length; ) {								//тут идет сравнение ключа с массивом символов
			
//			System.out.println(i + ". Сравниваю " + (char) A[i + j] + " и " + (char) key[j] + " альт. " + (char) (key[j] - 32) + " длинна ключа: " + key.length);
			if(i + j == A.length - 1) break;
			
			if(A[i + j] == key[j] || A[i + j] == (key[j] - 32)) {			//если первое значение соответствует
				j++;														//начинается сравнение остальных символов ключа
				
				
				if(j == key.length) {										//если ключь сравнен весь и все символы совпали
					for(int k = j; k > 0; k--) {							//то этот цикл помечает звездочками номера символов в специальном массиве
						B[i + k - 1] = (byte) '*';
					}
					i++;
					j = 0;													//нужно чтобы небыло ошибки 
				}
				
			} 
			else {
				i++;
				j = 0;
			}
			
		}
		
		for(int i = 0; i < A.length; i++) {									//эти два цикла выводят результаты
			System.out.print(A[i] + " ");
		}
			System.out.println();
		for(int i = 0; i < A.length; i++) {
			System.out.print((char)B[i] + " ");
		}
	}
}