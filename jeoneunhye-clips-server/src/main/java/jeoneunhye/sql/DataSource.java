package jeoneunhye.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {
  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  ArrayList<Connection> conList = new ArrayList<>();

  public DataSource(String jdbcUrl, String username, String password) {
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

    if (conList.size() > 0) {
      con = conList.remove(0);
      System.out.println("반납받은 Connection을 재사용합니다.");
    }

    con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
    System.out.println("새 Connection을 사용합니다.");

    connectionLocal.set(con);
    System.out.printf("DataSource: 현재 보관중인 Connection %d개\n", conList.size());

    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("Thread에 보관된 Connection을 제거합니다!");

      conList.add(con);
    }

    System.out.printf("DataSource: 현재 보관중인 Connection %d개\n", conList.size());
    return con;
  }

  public void clean() {
    while (conList.size() > 0) {
      try {
        ((ConnectionProxy) conList.remove(0)).realClose();
      } catch (Exception e) {}
    }
  }
}