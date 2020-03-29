package jeoneunhye.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
  String value() default "";
  // 이 애노테이션을 적용하고 지정할 컴포넌트 이름
  // 만약 값을 지정하지 않으면 빈 문자열이 설정된다.
}