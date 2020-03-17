package jeoneunhye.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    Connection con = connectionLocal.get();
    if (con != null) {
      System.out.println("Thread에 보관된 Connection을 사용합니다.");

      return con;
    }

    con = DriverManager.getConnection(jdbcUrl, username, password);
    System.out.println("새 Connection을 사용합니다.");

    connectionLocal.set(con);

    return con;
  }

  public void removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
    }
  }
}