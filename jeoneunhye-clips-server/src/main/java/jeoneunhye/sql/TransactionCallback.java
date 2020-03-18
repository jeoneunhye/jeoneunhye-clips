package jeoneunhye.sql;

public interface TransactionCallback {
  Object doInTransaction() throws Exception;
}