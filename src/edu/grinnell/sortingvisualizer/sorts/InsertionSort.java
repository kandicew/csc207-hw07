package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

public class InsertionSort<T> {

  /**
   * @param arr, an array
   * @pre no additionals
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
    List<SortEvent<T>> lst = new ArrayList();
    int len = arr.length;
    for (int i = 1; i < len; i++) {
      T key = arr[i];
      int j = i - 1;

      while (j >= 0 && (arr[j].compareTo(key) > 0)) {
        lst.add(new CompareEvent<T>(j, i));
        arr[j + 1] = arr[j--];
        
        // index might be wrong
        lst.add(new CopyEvent<T>(arr[j + 1], j + 2));
      } // while
      arr[j + 1] = key;
      lst.add(new CopyEvent<T>(key, j + 1));
    } // for
    return lst;
  }// insertionsort

}// class
