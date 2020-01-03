package jeoneunhye.vms.handler;

import java.util.Arrays;
import jeoneunhye.vms.domain.Board;

public class ArrayList {
  private static final int DEFAULT_CAPACITY = 100;
  private int size = 0;
  private Object[] list;

  public ArrayList() {
    this.list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if (capacity > DEFAULT_CAPACITY &&
        capacity < 10000) {
      this.list = new Object[capacity];
    } else {
      this.list = new Object[DEFAULT_CAPACITY];
    }
  }

  public void add(Object obj) {
    if(this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = obj;
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public Object get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return this.list[idx];
    } else {
      return null;
    }
  }
}