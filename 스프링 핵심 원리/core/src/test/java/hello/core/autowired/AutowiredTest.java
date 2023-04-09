package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        //Member class 는 일반 class , 스프링 빈으로 등록 X
        //수행시 noBean1 은 호출 자체가 안됨, required false는 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        //noBean2 = null
        //noBean3 = Optional.empty

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }


    }
}
