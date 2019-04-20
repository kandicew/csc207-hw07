package edu.grinnell.sortingvisualizer.audio;

import java.util.*;

/**
 * A collection of indices into a Scale object. These indices are the subject of the various sorting
 * algorithms in the program.
 */
public class NoteIndices {

  public Integer[] arr;
  public int n;
  public boolean[] high;

  /**
   * @param n the size of the scale object that these indices map into
   */
  public NoteIndices(int n) {
    // TODO: fill me in
    this.n = n;
    this.arr = new Integer[n];
    this.high = new boolean[n];
    for (int i = 0; i < n; i++) {
      this.high[i] = false;
    } // for
  }// NoteIndices

  /**
   * Reinitializes this collection of indices to map into a new scale object of the given size. The
   * collection is also shuffled to provide an initial starting point for the sorting process.
   * 
   * @param n the size of the scale object that these indices map into
   */
  public void initializeAndShuffle(int n) {
    // TODO: fill me in
    // initialize the array in order
    this.n = n;
    this.arr = new Integer[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i;
    } // for
      // shuffle it
    List<Integer> arrlst = Arrays.asList(arr);
    Collections.shuffle(arrlst);
    arrlst.toArray();
  }// initializeAndShuffle

  /** @return the indices of this NoteIndices object */
  public Integer[] getNotes() {
    // TODO: fill me in
    this.initializeAndShuffle(n);
    return this.arr;
  }// getNotes

  /**
   * Highlights the given index of the note array
   * 
   * @param index the index to highlight
   */
  public void highlightNote(int index) {
    // TODO: fill me in
    high[index] = true;
  }// highlightNote

  /** @return true if the given index is highlighted */
  public boolean isHighlighted(int index) {
    // TODO: fill me in
    return high[index];
  }// isHighlighted

  /** Clears all highlighted indices from this collection */
  public void clearAllHighlighted() {
    // TODO: fill me in
    for (int i = 0; i < n; i++) {
      this.high[i] = false;
    } // for
  }// clearAllHighlighted
}// NoteIndices
