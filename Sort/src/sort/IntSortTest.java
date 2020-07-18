package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

import static sort.IntSorter.*;

public class IntSortTest {
	public static final int SELECT = 1, INSERTION = 2, BUBBLE = 3,
							QUICK1 = 4, QUICK2 = 5, HEAP = 6,
							MERGE = 7, RADIX = 8, MY_MERGE = 9;

	public static final int N = 1000000;
	int i;
	public IntSortTest(int i){
		this.i = i;
	}
	static Random random = new Random(System.currentTimeMillis());
	public static void genArray(int[] a, int n){

		for (int i = 0; i < n; i++) {
			a[i] = random.nextInt();
		}
	}

	public static boolean checkAsc(int [] a , int n){
		for (int i = 1; i < n; i++)
			if (a[i] < a[i - 1])
			return false;
		return true;
	}

	public static long measure(int []a, int n, int method){
		long t1 = System.currentTimeMillis();
		long t2;
		switch (method){
			case SELECT:
				SelectionSort(a, n);
				break;
			case INSERTION:
				InsertionSort(a, n);
				break;
			case BUBBLE:
				BobbleSort(a, n);
				break;
			case QUICK1:
				QuickSort(a, n);
				break;
			case QUICK2:
				QuickSort2(a, n);
				break;
			case MERGE:
				MergeSort(a, n);
				break;
			case MY_MERGE:
				myMergeSort(a, n);
				break;
			case HEAP:
				heapSort(a, n);
				break;
			case RADIX:
				RadixSort(a, n);
				break;
		}
		t2 = System.currentTimeMillis();
		System.out.println(checkAsc(a, n));
		return t2 - t1;
	}

	public static void main(String[] args) {
		int[] arr = new int[N];
		int[] ints;
		Hashtable<Integer, IntSorter> hashtable = new Hashtable<>();

		genArray(arr, N);
//		System.out.print("selection sort time:");
//		ints = Arrays.copyOf(arr, N);
//		System.out.println(measure(ints, N, SELECT));
//
//		System.out.print("insertion sort time:");
//		ints = Arrays.copyOf(arr, N);
//		System.out.println(measure(ints, N, INSERTION));
//
//		System.out.print("Bubble sort time:");
//		ints = Arrays.copyOf(arr, N);
//		System.out.println(measure(ints, N, BUBBLE));

		System.out.print("Quick1 sort time:");
		ints = Arrays.copyOf(arr, N);
		System.out.println(measure(ints, N, QUICK1));

		System.out.print("Quick2 sort time:");
		ints = Arrays.copyOf(arr, N);
		System.out.println(measure(ints, N, QUICK2));

		System.out.print("merge sort time:");
		ints = Arrays.copyOf(arr, N);
		System.out.println(measure(ints, N, MERGE));

		ints = Arrays.copyOf(arr, N);
		System.out.print("my merge sort time:");
		System.out.println(measure(ints, N, MY_MERGE));

		ints = Arrays.copyOf(arr, N);
		System.out.print("heap sort time:");
		System.out.println(measure(ints, N, HEAP));

		for (int i = 0; i < N; i++) ints[i] = random.nextInt(Integer.MAX_VALUE);
		System.out.print("radix sort time:");
		System.out.println(measure(ints, N, RADIX));
	}
}
