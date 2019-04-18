package edu.grinnell.sortingvisualizer.events;

import java.util.List;

public interface SortEvent<T> {

  /**
   * @param arr
   * 
   *        apply the sort event to a given list
   */
  public void apply(T[] arr);

  /**
   * @return a list containing all of the indices that this event affects
   */
  public List<Integer> getAffectedIndices();

  /**
   * @return if this event should be emphasized by the visualizer/audibilizer
   */
  public boolean isEmphasized();

}// SortEvent
