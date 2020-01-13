package jeoneunhye.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LinkedList<E> {
  Node first;
  Node last;
  int size;

  public void add(E value) {
    Node newNode = new Node();

    newNode.value = value;

    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }

    size++;
  }

  public E get(int index) {
    if (index < 0 || index >= size)
      return null;

    Node cursor = first;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return (E) cursor.value;
  }

  public void add(int index, E value) {
    if (index < 0 || index >= size)
      return;

    Node newNode = new Node();
    newNode.value = value;

    Node cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    if (index == 0) {
      newNode.next = first;
      first = newNode;
    } else {
      newNode.next = cursor.next;
      cursor.next = newNode;
    }

    size++;
  }

  public E remove(int index) {
    if (index < 0 || index >= size)
      return null;

    Node cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    Node deletedNode = null;

    if (index == 0) {
      deletedNode = first;
      first = deletedNode.next;
    } else {
      deletedNode = cursor.next;
      cursor.next = deletedNode.next;
    }

    deletedNode.next = null;

    size--;

    return (E) deletedNode.value;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= size)
      return null;

    Node cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    E oldValue = (E) cursor.value;
    cursor.value = value;

    return oldValue;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node cursor = first;

    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    
    return arr;
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < size) {
      arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), size);
    }
    
    Node<E> cursor = first;
    
    for (int i = 0; i < size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    
    return arr;
  }
  
  public int size() {
    return this.size;
  }

  static class Node<T> {
    T value;
    Node next;
  }
}