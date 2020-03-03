package jeoneunhye.vms.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {
  protected String filename;
  protected List<T> list;

  public AbstractJsonFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();

    loadData();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void loadData() {
    File file = new File(filename);

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      Class<?> currType = this.getClass();
      // 해당 메서드를 호출한 클래스의 정보를 알아낸다. => class jeoneunhye.vms.dao.json.XxxJsonFileDao

      Type parentType = currType.getGenericSuperclass();
      // generic 타입의 수퍼 클래스 정보를 알아낸다. => class jeoneunhye.vms.dao.json.AbstractJsonFileDao

      ParameterizedType parentType2 = (ParameterizedType) parentType;
      // generic이 적용된 클래스의 정보와 타입 파라미터를 추출한다.

      Type[] typeParams = parentType2.getActualTypeArguments();
      // 타입 파라미터가 담긴 배열을 생성한다.

      Type itemType = typeParams[0];
      // AbstractJsonFileDao의 타입 파라미터는 T 뿐이다. 0번 인덱스에서 T를 꺼낸다.
      // => class jeoneunhye.vms.domain.Video 또는 Member 또는 Board

      T[] arr = (T[]) Array.newInstance((Class)itemType, 0);
      // 크기가 0인 배열을 생성한다. 실제로 사용하려는 것이 아닌 배열의 타입 정보를 구하기 위한 것이다.

      T[] dataArr = (T[]) new Gson().fromJson(in, arr.getClass());
      // arr.getClass()를 호출하면 최종적으로 원하는 도메인 객체의 배열 정보를 얻게 된다.
      // => class [Ljeoneunhye.vms.domain.Video 또는 Member 또는 Board;
      // T 타입의 배열 정보를 이용해 Json 파일로부터 데이터를 읽는다.

      for (T data : dataArr) {
        list.add(data);
      }

      System.out.printf("총 %d개의 객체를 로딩했습니다.\n", list.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
      e.printStackTrace();
    }
  }

  protected void saveData() {
    File file = new File(filename);

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

      out.write(new Gson().toJson(list));

      System.out.printf("총 %d개의 객체를 저장했습니다.\n", list.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  protected abstract <K> int indexOf(K key);
}