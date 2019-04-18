package edu.grinnell.sortingvisualizer.events;

import java.util.ArrayList;
import java.util.List;

public class SwapEvent <T> implements SortEvent<T>{
  //fields
  int index1;
  int index2;
  
  //constructor
  public SwapEvent(int val1, int val2){
    this.index1 = val1;
    this.index2 = val2;
  }//Swap
  
//methods
  @Override
  public void apply(T[] arr) {
    // TODO Auto-generated method stub
    T temp = arr[index1];
    arr[index1] = arr[index2];
    arr[index2] = temp;
  }//apply

  @Override
  public List<Integer> getAffectedIndices() {
    // TODO Auto-generated method stub
    List<Integer> lst = new ArrayList<Integer>();
    lst.add(index1);
    lst.add(index2);
    return lst;
  }//getAffectedIndices

  @Override
  public boolean isEmphasized() {
    return true;
  }//isEmphasized

}//Swap
