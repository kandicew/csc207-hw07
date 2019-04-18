package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

// this code is copied and modified from https://www.geeksforgeeks.org/merge-sort/

public class MergeSort {

  /**
   * @param arr, an array containing the two halves to be merged
   * @param lb, the index of the lower bound
   * @param mid, the index between the two subarrays
   * @param ub, the index of the upper bound of the array
   * @pre the subarray of arr from lb to mid is sorted the subarray of mid+1 to ub is sorted
   * @post the front and the back half of the array is merged to a sorted array the elements do not
   *       change within arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> merge(T[] arr, int lb, int mid, int ub) {
    List<SortEvent<T>> lst = new ArrayList();
    
    int leftsize = mid - lb + 1;
    int rightsize = ub - mid;
    // locate space to seperate the two array to be merged
    T[] left = (T[]) new Comparable[leftsize];
    T[] right = (T[]) new Comparable[rightsize];
    // copy the array
    for (int i = 0; i < leftsize; ++i) {
      left[i] = arr[lb + i];
      lst.add(new CopyEvent<T>(arr[lb + i], i));
    }
    for (int i = 0; i < rightsize; ++i) {
      right[i] = arr[mid + i + 1];
    lst.add(new CopyEvent<T>(arr[mid + i + 1], i));
    }
    // compare and merge
    int i = 0;
    int j = 0;
    int pos = lb;
    while (i < leftsize && j < rightsize) {
      lst.add(new CompareEvent<T>(i, j));
      if (left[i].compareTo(right[j]) <= 0) {
        
        arr[pos++] = (T) left[i++];
        
      lst.add(new CopyEvent<T>(left[i - 1], pos-1));
      } else {
        arr[pos++] = (T) right[j++];
        
      lst.add(new CopyEvent<T>(right[j-1], pos-1));
      } // else
    } // while
    // copy the remaining of one array to the end of the merged one
    while (i < leftsize) {
      arr[pos++] = (T) left[i++];
      
    lst.add(new CopyEvent<T>(left[i-1], pos-1));
    } // while
    while (j < rightsize) {
      arr[pos++] = (T) right[j++];
      
    lst.add(new CopyEvent<T>(right[j-1], pos-1));
    } // while
    
    return lst;
  }// merge


  /**
   * @param arr, an array
   * @param lo, the index of the lower bound
   * @param hi, the index of the last element to be considered
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSortHelper(T[] arr, int lo, int hi) {
    List<SortEvent<T>> lst = null;
    if (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      mergeSortHelper(arr, lo, mid);
      mergeSortHelper(arr, mid + 1, hi);
      // merge the sorted arrays
      lst = merge(arr, lo, mid, hi);
    } // if
    return lst;
  }// mergeSortHelper


  /**
   * @param arr, an array
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
    return mergeSortHelper(arr, 0, arr.length - 1);
  }// mergeSort

}// class
