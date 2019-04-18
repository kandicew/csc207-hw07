package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.grinnell.sortingvisualizer.events.*;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
class SortTest {

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
   *//**
  public static void eventExperiment(PrintWriter pen,
      Function<String[], List<SortEvent<String>>> sorter) {
    String[] strings =
        new String[] {"A", "Z", "Q", "B", "A", "F", "G", "Q", "T", "A"};
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
  } // eventExperiment */

  /**
   * Run a few experiments.
   */  /**
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Selection sort");
    experiment(pen, (arr) -> Sorts.selectionSort(arr));
    //eventExperiment(pen, (arr) -> Sorts.selectionSort(arr));

    pen.println();
    pen.println("Quick sort");
    experiment(pen, (arr) -> Sorts.quickSort(arr));
  } // main(String[])   */
}
