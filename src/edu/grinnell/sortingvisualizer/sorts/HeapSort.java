package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

// this code is copied and modified from https://www.geeksforgeeks.org/heap-sort/

public class HeapSort {
  /**
   * @param arr, an array
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> heapSort(T[] arr) {
    List<SortEvent<T>> lst = null;

    int n = arr.length;
    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(arr, n, i, lst);
    } // for
      // One by one extract an element from heap
    for (int i = n - 1; i >= 0; i--) {
      // Move current root to end
      swap(arr, 0, i);
      // call max heapify on the reduced heap
      heapify(arr, i, 0, lst);
    } // for
    return lst;
  }// heapSort

  /**
   * @param arr, an array
   * @param n, the size of heap
   * @param i, the index of root in arr
   * @pre arr contains at least one element
   * @post arr is sorted
   */
  public static <T extends Comparable<T>> void heapify(T arr[], int n, int i,
      List<SortEvent<T>> lst) {

    int largest = i; // Initialize largest as root
    int l = 2 * i + 1; // left = 2*i + 1
    int r = 2 * i + 2; // right = 2*i + 2

    // If left child is larger than root
    lst.add(new CompareEvent<T>(l, largest));
    if (l < n && arr[l].compareTo(arr[largest]) > 0) {
      largest = l;
    } // if
      // If right child is larger than largest so far
    lst.add(new CompareEvent<T>(r, largest));
    if (r < n && arr[r].compareTo(arr[largest]) > 0) {
      largest = r;
    } // if
      // If largest is not root
    if (largest != i) {
      lst.add(new SwapEvent<T>(i, largest));
      swap(arr, i, largest);

      // Recursively heapify the affected sub-tree
      heapify(arr, n, largest, lst);
    } // if
  }// heapify

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
