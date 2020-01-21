package jeoneunhye.util;

import jeoneunhye.util.LinkedList;

public class Queue<E> extends LinkedList<E> implements Cloneable {
  public void offer(E value) {
    this.add(value);
  }

  public Object poll() {
    return this.remove(0);
  }
  
  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<>();
    
    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }
    return temp;
  }
}