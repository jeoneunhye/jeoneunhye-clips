# 56_7 - 필터를 사용하여 사용자 접근 제어하기 

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/filter/AuthFilter.java 추가

### 작업1) 로그인 여부를 검사하는 필터를 추가한다.

- AuthFilter 클래스 추가
    - Filter 인터페이스를 구현하고 doFilter 메서드를 오버라이딩한다.  
    => 로그인한 사용자만 데이터를 추가, 변경, 삭제할 수 있도록 구현한다.