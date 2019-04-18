package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

// get help from Rob, Chris, SamR and Ally

public class CopyEvent<T> implements SortEvent<T>{

  //fields
  T val;
  int index;
  
  //constructor
  public CopyEvent(T val, int index) {
    this.val = val;
    this.index = index;
  }//Copy

  //methods
  @Override
  public void apply(T[] arr) {
    // TODO Auto-generated method stub
    arr[index] = val;
  }

  @Override
  public List<Integer> getAffectedIndices() {
    // TODO Auto-generated method stub
    List<Integer> lst = new ArrayList<Integer>();
    lst.add(index);
    return lst;
  }//getAffectedIndices

  @Override
  public boolean isEmphasized() {
    return true;
  }//isEmphasized
}
