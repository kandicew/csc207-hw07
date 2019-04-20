package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.grinnell.sortingvisualizer.events.*;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

class SortTest<T> {


  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  static Random rand = new Random();
  
  /**
   * Create sorts
   */
  public <T extends Comparable<T>> void checkSort(T[] expected, T[] values) {
    Sorts.insertionSort(values);
    assertArrayEquals(expected, values);
    Sorts.selectionSort(values);
    assertArrayEquals(expected, values);
    Sorts.mergeSort(values);
    assertArrayEquals(expected, values);
    Sorts.quickSort(values);
    assertArrayEquals(expected, values);
    Sorts.heapSort(values);
    assertArrayEquals(expected, values);
  }// checkSort
  
  /**
   * Create eventSort
   */
  public <T extends Comparable<T>> void checkEventSort(T[] expected, T[] values,
      Function<T[], List<SortEvent<T>>> sorter) {
    T[] arr = values.clone();
    List<SortEvent<T>> events = sorter.apply(arr);
    Sorts.eventSort(values, events);
    assertArrayEquals(expected, values);
  }// checkEventSort

  /**
   * Create sorts
   */
  public <T extends Comparable<T>> void checkSort(T[] sorted) {
    T[] copy = sorted.clone();
    randomlyPermute(copy);
    checkSort(sorted, copy);
  }// checkSort

  /**
   * Create eventSort
   */
  public <T extends Comparable<T>> void checkEventSort(T[] sorted) {
    T[] copy = sorted.clone();
    randomlyPermute(copy);
    checkEventSort(sorted, copy, (arr) -> Sorts.insertionSort(arr));
    checkEventSort(sorted, copy, (arr) -> Sorts.selectionSort(arr));
    checkEventSort(sorted, copy, (arr) -> Sorts.mergeSort(arr));
    checkEventSort(sorted, copy, (arr) -> Sorts.quickSort(arr));
    checkEventSort(sorted, copy, (arr) -> Sorts.heapSort(arr));
  }// checkEventSort

  
  /**
   * Create an unpredictable array of integers in non-decreasing order.
   */
  public static Integer[] randomInts(int size) {
    Integer[] result = new Integer[size];
    result[0] = 50 - rand.nextInt(100);
    for (int i = 1; i < size; i++) {
      result[i] = result[i - 1] + rand.nextInt(3);
    } // for
    return result;
  } // randomInts(int)


  /**
   * Randomly permute an array.
   */
  public static <T> void randomlyPermute(T[] values) {
    for (int i = 0; i < values.length; i++) {
      swap(values, i, rand.nextInt(values.length));
    } // for
  } // randomlyPermute(T[])

  /**
   * Swap two values in an array.
   */
  public static <T> void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap
  
  
  
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  <T extends Comparable<T>> void testEmpty() {
    checkSort(new Comparable[0]);
    checkEventSort(new Comparable[0]);
  } // testEmpty

  @Test
  <T extends Comparable<T>> void testOrdered() {
    for (int size = 1; size < 20; size++) {
      T[] sorted = (T[]) new Comparable[size];
      for (int i = 0; i < size; i++) {
        sorted[i] = (T) new Integer(i);
      } // for
      checkSort(sorted, sorted.clone());
      checkEventSort(sorted);
    } // for
  } // testOrdered

  @Test
  <T extends Comparable<T>> void testBackwards() {
    for (int size = 1; size < 20; size++) {
      T[] sorted = (T[]) new Comparable[size];
      T[] backwards = (T[]) new Comparable[size];
      for (int i = 0; i < size; i++) {
        backwards[i] = (T) new Integer(size - i);
        sorted[i] = (T) new Integer(i + 1);;
      } // for
      checkSort(sorted, backwards);
      checkEventSort(sorted);
    } // for
  } // testBackwards

  @Test
  <T extends Comparable<T>> void testRandom() {
    for (int trials = 0; trials < 5; trials++) {
      // Some comparatively small ones
      for (int size = 1; size < 20; size++) {
        checkSort(randomInts(size));
        checkEventSort(randomInts(size));
      } // for size
      // Some larger ones
      for (int size = 30; size < 1000; size = size * 2 + 1) {
        checkSort(randomInts(size));
        checkEventSort(randomInts(size));
      } // for size
    } // for trials
  } // testRandom()


  // +-------------+-------------------------------------------------
  // | experiments |
  // +-------------+
  /**
   * Our generic experiments.
   *
   * @param pen the location for output
   * @param sorter the algorithm used to sort
   */
  public static void experiment(PrintWriter pen, Consumer<String[]> sorter) {
    String[] arr = new String[] {"Q", "B", "A", "F", "G", "Q", "T", "A"};
    pen.print("Input:  ");
    pen.println(Arrays.toString(arr));
    sorter.accept(arr);
    pen.print("Output: ");
    pen.println(Arrays.toString(arr));
  } // experiment()

  /**
   * Another experiment, this time with our funky event list.
   */
  public static void eventExperiment(PrintWriter pen,
      Function<String[], List<SortEvent<String>>> sorter) {
    String[] strings = new String[] {"A", "Z", "Q", "B", "A", "F", "G", "Q", "T", "A"};
    String[] arr = strings.clone();
    pen.print("Input:  ");
    pen.println(Arrays.toString(arr));
    List<SortEvent<String>> events = sorter.apply(arr);
    pen.print("Output: ");
    pen.println(Arrays.toString(arr));
    arr = strings.clone();
    pen.print("Reset:  ");
    pen.println(Arrays.toString(arr));
    Sorts.eventSort(arr, events);
    pen.print("Replay: ");
    pen.println(Arrays.toString(arr));
  } // eventExperiment

  /**
   * Run a few experiments.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Selection sort");
    experiment(pen, (arr) -> Sorts.selectionSort(arr));
    eventExperiment(pen, (arr) -> Sorts.selectionSort(arr));

    pen.println();
    pen.println("Quick sort");
    experiment(pen, (arr) -> Sorts.heapSort(arr));
  } // main(String[])

}// class
