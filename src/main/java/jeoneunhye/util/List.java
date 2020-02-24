package jeoneunhye.util;

public interface List<E> {
  int size();

  void add(E value);

  void add(int index, E value);

  E get(int index);

  E set(int index, E value);

  E remove(int index);

  Object[] toArray();

  E[] toArray(E[] arr);

  Iterator<E> iterator();
}