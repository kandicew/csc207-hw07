package edu.grinnell.sortingvisualizer.sorts;

import java.util.ArrayList;
import java.util.List;
import edu.grinnell.sortingvisualizer.events.*;

public class Sorts {

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


  /**
   * @param arr, an array containing the two halves to be merged
   * @param lb, the index of the lower bound
   * @param mid, the index between the two subarrays
   * @param ub, the index of the upper bound of the array
   * @pre the subarray of arr from lb to mid is sorted the subarray of mid+1 to ub is sorted
   * @post the front and the back half of the array is merged to a sorted array the elements do not
   *       change within arr
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> merge(T[] arr, int lb, int mid,
      int ub) {
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

        lst.add(new CopyEvent<T>(left[i - 1], pos - 1));
      } else {
        arr[pos++] = (T) right[j++];

        lst.add(new CopyEvent<T>(right[j - 1], pos - 1));
      } // else
    } // while
    // copy the remaining of one array to the end of the merged one
    while (i < leftsize) {
      arr[pos++] = (T) left[i++];

      lst.add(new CopyEvent<T>(left[i - 1], pos - 1));
    } // while
    while (j < rightsize) {
      arr[pos++] = (T) right[j++];

      lst.add(new CopyEvent<T>(right[j - 1], pos - 1));
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
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSortHelper(T[] arr, int lo,
      int hi) {
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
   * @param events, a List
   * @pre no additionals
   * @post apply the event to arr
   */
  public static <T> void eventSort(T[] arr, List<SortEvent<T>> events) {
    for (SortEvent<T> event : events) {
      event.apply(arr);
    } // for
  }// eventSort


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
}// Sorts
