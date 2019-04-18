package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class CompareEvent<T> implements SortEvent<T> {
  // fields
  int val1;
  int val2;

  // constructor
  public CompareEvent(int val1, int val2) {
    this.val1 = val1;
    this.val2 = val2;
  }//Compare

  // methods
  @Override
  public void apply(T[] arr) {
    return;
  }//apply

  @Override
  public List<Integer> getAffectedIndices() {
    // TODO Auto-generated method stub
    List<Integer> lst = new ArrayList<Integer>();
    lst.add(val1);
    lst.add(val2);
    return lst;
  }//getAffectedIndices

  @Override
  public boolean isEmphasized() {
    return false;
  }//isEmphasized

}//Compare<T>
