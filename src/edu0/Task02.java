package edu0;

import java.util.Random;

public class Task02 {
	
	/*
	 * Задачи на линейную и рекурсивну сортировку
	 * Комментари в коде, чтобы запустить доп коменты в линейной сортировке
	 * разблокируй закоменченные System.out-ы
	 * для того чтоы сменить размер массива поменяй значение в инциализаторе массива сразу после этого комента.
	 * Если хочешь чтобы рандом был большего диапазона найди комент про рандом и после % в коде вбей нужное значение,
	 * по умолчанию 10.
	 */
	
	int[] A = new int[30];	//размер массива с рандомными числами
	
	Task02() {				//конструктор класса второй задачи
		fillArray();		//по отработанной схеме создаем рандомный числовой массив
		System.out.println("Время выполнения линейной сортировки: " + sort01() + "мс\n");	//методы Sort01 и 02 возвращают строку со временем исполнения
		
		System.out.println("Время выполнения сортировки разбиением: " + sort02() + "мс");	//с помощью print просто выводится значение
	}
	
	static public void main(String Args[]) {
		new Task02();		//тут создается экземпляр класса Task02
	}
	
	void fillArray() {		//метод по заполнению массива рандомными числами
		for(int i = 0; i < A.length; i++) {
			
			A[i] = Math.abs(new Random().nextInt() % 100);	//тут задаем рандом, можно сделать диапазон больше или меньше
			
		}
	}
	
	String sort01() {		//линейный сортировщик
		
		long startTime = System.currentTimeMillis();		//тут снимаем время начала работы
		int[] B = new int[A.length];						//массив метода в который он будет сортировать
		int[] temp;											//буферный массив для переноса фрагментов
		
		System.out.println("Линейная сортировка\n");
		
		for(int i = 0; i < A.length; i++) {					//основной цикл сортировщика
			
			System.out.println("Step " + i + ":");
			
			if(i == 0) {
//				System.out.println("  Set B[" + i + "] to A[" + i + "]");
				B[0] = A[0];
				
			} 
			else 
			{
				if(B[i - 1] > A[i]) {						//удобный случай когда записываемое значение меньше предыдущего в массиве
//					System.out.println("  Set B[" + i + "] to A[" + i + "]");
					B[i] = A[i];
				} 
				else {										//а тут все остальные неудобные случаи
					boolean flag = false;
					
					for(int j = i; j >= 0; j--) {			//цикл в котором нужно определить на сколько нужно отступить назад чтобы вставить новое значение
						
//						System.out.println("  Compare B[" + (j - 1) + "] and A[" + i + "]");
						
						if (j == 0) flag = true;			//нужно в случае если цикл дойдет до начала массива и сравнить будет не с чем
						else if(B[j - 1] < A[i]) flag = false;		//сработает если следующее значение тоже меньше записываемого и нужно проверять дальше
						else flag = true;					//а тут сработает во всех остальных случаях
						
						if(flag) {							//тут происходит запись в отсортированныйй массив и перенос его фрагменто
							temp = new int[(i  - j)];		//задаем размер буферному массиву
							
							for(int k = 0; k < (i- j); k++) {	//переносим в буфер значения которые нужно будет сдвинуть
//								System.out.println("  Moving B[" + (k + j - 1) + "] in to temp[" + k + "]");
								temp[k] = B[k + j];				//сама по себе операция переноса
								
							}
							
//							System.out.println("  Set B[" + (j) + "] to A[" + i + "]");
							B[j] = A[i];					//записываем новое значение
							
							for(int k = 0; k < temp.length; k++) {		//возвращаем значения со сдвигом
								
//								System.out.println("  Relising to B[" + (j + k + 1) + "] from temp[" + k + "]" );
								B[j + k + 1] = temp[k];		
								
							}
							
							temp = null;					
							break;							//нужен чтобы цикл проверки не крутил еще много раз после выполнения нужных операцияй
							
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
		return (System.currentTimeMillis() - startTime) + "";		//тут подсчитываем сколько времени потребовалось чтобы все отсортировать
	}
	
	String sort02() {									//сортировка разбением "самое интересное"
		long startTime = System.currentTimeMillis();	//снимаем время начала
		System.out.println("Сортировка разбиением\n");
		int B[] = fastSort(A);							//запускаем рекурсивный метод fastSort передав ему сгенерированный рандомом массив
		
		System.out.print("\nUnsorted array:\t");		//выведет несортированный массив для сравнения
		for(int g = 0; g < A.length; g++) {
			System.out.print(A[g] + " ");
			
		}
		
		System.out.print("\nSorted array:\t");			//выведет сортированный массив
		for(int g = 0; g < B.length; g++) {
			System.out.print(B[g] + " ");
			
		}
		System.out.println();
		return (System.currentTimeMillis() - startTime) + "";		//посчет сколько времени заняло
	}
	
	int[] fastSort(int A[]) {							//рекурсивный сортировщик с разбиением
		int B[] = new int[A.length]; 
		int maxValue = 0, minValue = Integer.MAX_VALUE, midValue;		//переменные для определения среднего значения
		int aLength = 0, bLength = 0;					//переменные для определения длинн буферных массивов
		int[] a, b;										//буферные массивы, объявлены но не инциализированы
		
		System.out.println("Start sorting cicle");
		for(int i = 0; i < A.length; i++) {				//тут считаем максимальное и минимальное значение в сортируемом массиве
			if(A[i] >= maxValue) maxValue = A[i];
			if(A[i] <= minValue) minValue = A[i];
		}
		
		midValue = (maxValue + minValue) / 2;			//считаем среднее значение
		
		System.out.println("  Mid Value is: " + midValue + ", max Value is: " + maxValue + ", min Value is: " + minValue);
		
		if(A.length > 2) {								//случей если массив больше 2
			System.out.println("  Array is in \"more then 2\" state.");
			for(int i = 0; i < A.length; i++) {			//считаем длинну буферных массивов
				if(A[i] > midValue) aLength++;
				if(A[i] <= midValue) bLength++;
			}
			
			a = new int[aLength];						//объявляем два массива
			b = new int[bLength];						//может выйти что одна из длин нулевая, но джава это игнорирует
			
			for(int i = 0, ac = 0, bc = 0; i < A.length; i++) {	//тут распеределяем значения по массивам
				
				if(A[i] > midValue) {					//в массив "ас" уходят значения которые выше среднего
					a[ac] = A[i];
					ac++;
				}
				
				if(A[i] < midValue) {					//в "bc" значения которые равны и ниже среднего
					b[bc] = A[i];
					bc++;
				}
				
				if(A[i] == midValue) {					//можно было конечно поставит "<=" в предыдущем но это для наглядности
					b[bc] = A[i];
					bc++;
				}
			}
			
			
			System.out.print("  Dividing: ");			//это все выводитсообщение в консоль по операции разделения
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
			
			if(a.length == A.length) {					//эти два "если" нужны в случае если все значения в сортируемом массиве равны среднему значению
				return a;
			}
			if(b.length == A.length) {					//или он уйдет в бесконечный цикл потому, как все время будет пытаться их разделить
				return b;
			}
			
			
			int[] aBuf = fastSort(a);					//запуск рекурсии
			int[] bBuf = fastSort(b);					//запускаем сюда буферные массивы для прогона по тому же алгоритму
			
			for(int i = 0; i < A.length; i++) {			// тут распеределяем полученные результаты
				if(i < a.length) {
					B[i] = aBuf[i];						//так как в буферный массив "а" ма выводили наибольшие значения
				} else {
					B[i] = bBuf[i - aBuf.length];		//а в "b" наименьшие, возвращаем из в нужном порядке, т.е. от большего к малому
				}
			}
			return B;									//возвращаем получившийся массив
		}
		
		else if(A.length == 2) {						//В случае с двумя значениями все просто, сравним их и поставим сначала большее потом меньшее
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
			
			return B;									//возвращаем получившийся массив
		}
		
		else {											//тут случай когда значение останется одно и его не нужно ни с чем сравнивать
			System.out.print("  Array is in \"less then 2\" state");
			System.out.println("  Returning result: " + A[0]);
			return A;									//возвращаем получившийся массив
		}
	}
}
