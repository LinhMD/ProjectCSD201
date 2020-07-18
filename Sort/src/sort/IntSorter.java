package sort;

import java.util.*;

public class IntSorter {
	public static void swap(int []a , int i, int j){
		int t = a[i]; a[i] = a[j]; a[j] = t;
	}

	public static int getMinIndex(int [] a, int first, int last){
		int min = first;
		for (int i = first; i < last; i++) if (a[min] > a[i]) min = i;
		return min;
	}

	public static void SelectionSort(int []a, int n){
		for (int i = 0; i < n; i++) swap(a, getMinIndex(a, i, n), i);
	}

	public static void InsertionSort(int [] a, int n){
		int i, j, temp;
		for(i = 1; i < n; i++){
			temp = a[i];
			j = i - 1;
			while(j >= 0 && a[j] >temp){
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = temp;
		}
	}

	private static void QuickSort (int []a, int first, int last){
		int mid = (first + last) / 2;
		swap(a, first, mid);
		int lower = first + 1;
		int upper = last;
		int pivot = a[first];
		while (lower <= upper){
			while (lower <= upper && pivot > a[lower]) lower++;
			while (lower <= upper && pivot < a[upper]) upper--;
			if(lower < upper) swap(a, lower++, upper--);
			else lower++;
		}
		swap(a, upper, first);
		if (first < upper - 1) QuickSort(a, first, upper - 1);
		if (upper + 1 < last) QuickSort(a, upper + 1, last);
	}

	public static void QuickSort(int [] a, int n){
		QuickSort(a, 0, n - 1);
	}

	private static int median(int a, int b, int c){
		if((a > b && a < c) || (a > c && a < b)) return a;
		if ((b > a && b < c) || (b > c && b < a)) return b;
		return c;

	}

	private static void QuickSort2(int [] a, int first, int last){
		if(first >= last) return;
		int mid = (first + last) / 2;
		int pivot = median(a[first], a[last], a[mid]);
		int lower = first;
		int upper = last;
		while (lower <= upper){
			while (lower <= upper && pivot > a[lower]) lower++;
			while (lower <= upper && pivot < a[upper]) upper--;
			if (lower < upper) swap(a, lower++, upper--);
			else lower++;
		}
		QuickSort2(a, first, upper);
		QuickSort2(a, upper + 1, last);
	}

	public static void QuickSort2(int [] a, int n){
		QuickSort2(a, 0, n - 1);
	}

	static int[]temp;

	private static void merge(int[]a, int first, int last){
		int mid = (first + last) / 2;
		int i = first;
		int j = mid + 1;
		int k = 0;
		while(i <= mid  && j <= last)
			if (a[i] < a[j]) temp[k++] = a[i++];
			else temp[k++] = a[j++];
		while(i <= mid) temp[k++] = a[i++];
		while(j <= last) temp[k++] = a[j++];
		k = 0; i = first;
		while (i <= last) {
			a[i++] = temp[k++];
		}
	}

	private static void mergeSort(int[] a, int first, int last){
		if (first == last) return;
		int mid = (first + last) / 2;

		if(first < mid) mergeSort(a, first, mid);

		if (mid + 1 < last) mergeSort(a, mid + 1, last);

		merge(a, first, last);
	}

	public static void MergeSort(int [] a, int n){
		if(n < 2) return;
		temp = new int[n];
		mergeSort(a, 0, n - 1);
	}

	public static void myMergeSort(int [] a, int n){
		if(n < 2) return;
		temp = new int[n];
		int i = 0;
		Vector<SubArray> arrays = new Vector<>();
		SubArray subArray = new SubArray(0, n - 1);
		arrays.add(subArray);
		int mid;
		while(arrays.size() > i){
			subArray = arrays.get(i++);
			if(subArray.first != subArray.last){
				mid = (subArray.first + subArray.last) / 2;
				if (subArray.first < mid) arrays.add(new SubArray(subArray.first, mid));
				if (mid + 1 < subArray.last) arrays.add(new SubArray(mid + 1, subArray.last));
			}
		}
		
		i = arrays.size() - 1;
		while (0 <= i) {
			subArray = arrays.get(i);
			merge(a, subArray.first, subArray.last);
			i--;
		}
	}

	public static void BobbleSort(int [] a, int n){
		for (int i = 0; i < n - 1; i++)
			for (int j = i; j < n - 1; j++)
				if (a[j] < a[i])
					swap(a, j, i);
	}

	public static void moveDown(int []a, int first, int last){
		int largest = 2*first + 1;
		while (largest <= last){
			if(largest < last && a[largest] < a[largest+1]) largest++;
			if(a[first] < a[largest]){
				swap(a, first, largest);
				first = largest;
				largest = 2*first + 1;
			}else largest = last + 1;
		}
	}

	public static void heapSort(int []a, int n){
		for (int i = n / 2; i >= 0; --i) moveDown(a, i, n - 1);
		for (int i = n-1; i >= 1; --i) {
			swap(a, 0, i);
			moveDown(a, 0, i - 1);
		}
	}

	private static int countDigits(int n){
		int count = 0;
		while(n / 10 != 0){
			count++;
			n /= 10;
		}
		return count + 1;
	}
	private static int maxAbs(int []a, int n){
		int result = Integer.MIN_VALUE;
		for (int i : a) {
			if(i < 0) i = -i;
			if(result < i) result = i;
		}
		return result;
	}
	public static int RADIX = 10;

	public static void RadixSort(int []a, int n){
		int digit = countDigits(maxAbs(a, n));
		int d, j, k, factor;
		LinkedList<Integer>[] queues = new LinkedList[RADIX];

		for(d = 0; d < RADIX; d++) queues[d] = new LinkedList<>();

		for(d = 1, factor = 1; d <= digit; factor *= RADIX, d++){
			for(j = 0; j < n; j++) queues[(a[j] / factor) % RADIX].add(a[j]);
			for(j = k = 0; j < RADIX; j++) while (!queues[j].isEmpty()) a[k++] = queues[j].removeFirst();
		}
	}
}
