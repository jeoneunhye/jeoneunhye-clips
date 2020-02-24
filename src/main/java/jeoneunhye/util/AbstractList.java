package jeoneunhye.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      List<E> list;
      int cursor;

      {
        this.list = AbstractList.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < list.size();
      }

      @Override
      public E next() {
        return list.get(cursor++);
      }
    };
  }
}