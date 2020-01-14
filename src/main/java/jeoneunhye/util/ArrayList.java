package jeoneunhye.util;

import java.util.Arrays;

public class ArrayList<E> {
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

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size)
      return (E[]) Arrays.copyOf(this.list, this.size, arr.getClass());

    System.arraycopy(this.list, 0, arr, 0, this.size);
    return arr;
  }

  public void add(E e) {
    if(this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(list, newCapacity);
    }
    this.list[this.size++] = e;
  }

  public void add(int index, E value) {
    if (index < 0 || index >= this.size)
      return;

    if (this.size == this.list.length) {
      grow();
    }

    for (int i = this.size - 1; i >= index; i--) {
      this.list[i + 1] = this.list[i];
    }

    this.list[index] = value;

    this.size++;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size)
      return null;
    return (E) this.list[index];
  }

  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if (index < 0 || index >= this.size)
      return null;

    E oldValue = (E) this.list[index];
    this.list[index] = e;

    return oldValue;
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= this.size)
      return null;

    E oldValue = (E) this.list[index];

    System.arraycopy(this.list, index + 1, this.list, index, this.size - (index + 1));

    this.size--;

    return oldValue;
  }

  private Object[] grow() {
    return Arrays.copyOf(this.list, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = this.list.length;

    return oldCapacity + (oldCapacity >> 1);
  }
}