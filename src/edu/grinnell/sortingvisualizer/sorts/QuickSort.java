package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

public class QuickSort<T> {
  // List<SortEvent<T>> lst;

  // public QuickSort (){
  // this.lst = new ArrayList();}


  /**
   * @param arr, an array
   * @param lb, the lower index
   * @param ub, the upper index
   * @return i, the position where the pivot is placed
   * @pre arr contains at least one element
   * @post all the elements less than or equal to the pivot is placed before the pivot all the
   *       elements larger than the pivot is placed after the pivot
   */
  public static <T extends Comparable<T>> int partition(T[] arr, int lb, int ub,
      List<SortEvent<T>> lst) {

    T pivot = arr[ub];
    int i = lb - 1;
    for (int j = lb; j < ub; j++) {

      lst.add(new CompareEvent(j, ub));
      if (arr[j].compareTo(pivot) <= 0) {
        i++;
        swap(arr, i, j);
        lst.add(new SwapEvent(i, j));
      } // if
    } // for
    swap(arr, ++i, ub);
    lst.add(new SwapEvent(i, ub));
    return i;
  }// partition

  /**
   * @param arr, an array
   * @param lb, the lower index
   * @param ub, the upper index
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> void quickSortHelper(T[] arr, int lb, int ub,
      List<SortEvent<T>> lst) {
    if (lb < ub) {
      int pivot = partition(arr, lb, ub, lst);
      quickSortHelper(arr, lb, pivot - 1, lst);
      quickSortHelper(arr, pivot + 1, ub, lst);
    } // if
  }// quickSortHelper


  /**
   * @param arr, an array
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
    List<SortEvent<T>> lst = null;
    quickSortHelper(arr, 0, arr.length - 1, lst);
    return lst;
  }// quickSort

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
