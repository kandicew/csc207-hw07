package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

public class SelectionSort {
  /**
   * @param arr an array
   * @pre no additionals
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
    List<SortEvent<T>> lst = new ArrayList();
    
    int len = arr.length;
    for (int i = 0; i < len - 1; i++) {
      int min = i;
      for (int j = i + 1; j < len; j++) {
        lst.add(new CompareEvent<T>(j, min));
        if (arr[j].compareTo(arr[min]) < 0) {
          lst.add(new SwapEvent<T>(j, min));
          swap(arr, j, min);
        } // if
      } // for
    } // for
    return lst;
  }// selectionSort


  /**
   * @param arr, an array
   * @param i, an index in arr
   * @param j, an index in arr
   * @pre i and j are valid index in arr
   * @post the element of index i and j in arr swap places the rest of arr remains the same
   */
  private static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }// swap

}// class
