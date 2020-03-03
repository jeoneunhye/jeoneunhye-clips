32_8 - DAO 클래스의 공통점을 뽑아 수퍼 클래스로 정의하기(generalization)
===

### inheritance(상속)

- specialization(특수화)
    - 수퍼 클래스를 상속받으면서 특별한 기능을 수행하는 서브 클래스를 생성하여 세분화하는 것(위->아래)
- generalization(일반화)
    - 여러 클래스들의 공통점을 뽑아 수퍼 클래스로 만들어 상속 관계를 맺는 것(아래->위)
- 개발 초기에는 설계를 하려고 너무 많은 시간과 노력을 기울이는 것보다 일단 기능들을 만들어 놓고 일반화하는 것이 더 효율적일 수 있다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/AbstractObjectFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/BoardObjectFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/LessonObjectFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/MemberObjectFileDao.java 변경

### 작업1) DAO의 공통점을 뽑아 수퍼 클래스로 정의한다.

- jeoneunhye.vms.dao.AbstractObjectFileDao 클래스 생성
    - 해당 클래스에서 다루는 멤버는 서브 클래스에게 상속하여 사용될 것이므로 modifier를 protected로 선언한다.
    - 서브 클래스에서 파일명으로 수퍼 클래스 객체를 호출하면 List를 생성한다.  
        파일에서 데이터를 로딩하고, 처리한 데이터를 파일로 저장하는 메서드 loadData()와 saveData()를 구현한다.
    - indexOf 메서드는  추후에 추가되는 Domain이 int 타입의 번호를 사용하지 않는 경우를 대비하여 
    generic으로 K 타입을 정의하고 추상 메서드로 선언한다.

### 작업2) VideoObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경한다.

- jeoneunhye.vms.dao.VideoObjectFileDao 클래스 변경
    - AbstractObjectFileDao 클래스를 상속받도록 변경한다.
    - List와 String 필드, loadData()와 saveData()를 삭제한다.
    - indexOf 메서드를 오버라이딩한다.  
        파라미터 타입을 int 대신 K로 변경하고 값을 사용할 때 형변환한다.

### 작업2) MemberObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경한다.

- jeoneunhye.vms.dao.MemberObjectFileDao 클래스 변경
    - AbstractObjectFileDao 클래스를 상속받도록 변경한다.
    - List와 String 필드, loadData()와 saveData()를 삭제한다.
    - indexOf 메서드를 오버라이딩한다.  
        파라미터 타입을 int 대신 K로 변경하고 값을 사용할 때 형변환한다.

### 작업2) BoardObjectFileDao가 위에서 정의한 클래스를 상속받도록 변경한다.

- jeoneunhye.vms.dao.BoardObjectFileDao 클래스 변경
    - AbstractObjectFileDao 클래스를 상속받도록 변경한다.
    - List와 String 필드, loadData()와 saveData()를 삭제한다.
    - indexOf 메서드를 오버라이딩한다.  
        파라미터 타입을 int 대신 K로 변경하고 값을 사용할 때 형변환한다.