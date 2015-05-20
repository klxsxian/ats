package com.lk.model.interview.algorithm.array;

import java.util.Arrays;

public class OrderbyArray {
	// 冒泡排序 Bubble Sort 最简单的排序方法是冒泡排序方法
	public int[] orderArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				//System.out.println("i="+i+"&&j="+j);
				if (array[i] > array[j]) {
					int s = array[i];
					array[i] = array[j];
					array[j] = s;
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] array = { 1, 8, 5, 2, 4, 9, 7 };
		Arrays.sort(array);
		//OrderbyArray order = new OrderbyArray();
		//array = order.orderArray(array);
		for (int i : array) {
			System.out.println(i);
		}
	}
}
