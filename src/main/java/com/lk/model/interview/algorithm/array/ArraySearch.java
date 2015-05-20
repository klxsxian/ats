package com.lk.model.interview.algorithm.array;

/** 
 * 数组查找方式 
 * @detail 这里演示了普通查找法和二分查找法 
 */  
public class ArraySearch {
	public static void main(String[] args) {  
        int commonResult = commonSearch(new int[]{1,5,6,7,4,3,9,11,13,14,16,19,21}, 9);  
        int binaryResult = binarySearch(new int[]{1,3,4,6,7,8,9,12,15,17,18,20,22}, 8);  
        System.out.println("二分查找法: " + binaryResult);  
        System.out.println("普通查找法: " + commonResult);  
    }  
      
    /** 
     * 普通查找法 
     * @detail 该方式最好理解，同时效率也最低 
     */  
    public static int commonSearch(int[] array, int value){  
        for(int i=0; i<array.length; i++){  
            if(value == array[i]){  
                return i; //返回该元素在数组中的下标  
            }  
        }  
        return -1; //不存在该元素则返回-1  
    }  
  
    /** 
     * 二分查找法 
     * @detail 要求数组有序，升序或降序均可 
     */  
    public static int binarySearch(int[] array, int value){  
        int low = 0; //最小元素值的下标  
        int high = array.length - 1; //最大元素值的下标  
        int middle; //中间元素的下标  
        while(low <= high){  
            middle = (low+high) / 2;  
            for(int i=0; i<array.length; i++){  
                System.out.print(array[i]);  
                if(i == middle){  
                    System.out.print("#"); //在元素后面用#号标识其为中间元素  
                }  
                System.out.print(" "); //各元素间用空格隔开  
            }  
            System.out.println();  
            if(value == array[middle]){  
                return middle;  
            }  
            if(value < array[middle]){  
                high = middle - 1; //右侧的不要了  
            }  
            if(value > array[middle]){  
                low = middle + 1; //左侧的不要了  
            }  
        }  
        return -1; //不存在该元素则返回-1  
    }  
}
