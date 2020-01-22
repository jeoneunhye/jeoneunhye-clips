package jeoneunhye.util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return size;
  }
}