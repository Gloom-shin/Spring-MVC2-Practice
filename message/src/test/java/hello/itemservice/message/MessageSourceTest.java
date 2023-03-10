package hello.itemservice.message;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;


@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        // default가 선택이 된다.
        String result = ms.getMessage("hello", null, null);
        System.out.println(Locale.getDefault());


        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()->ms.getMessage("no_code", null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "default-Message", null);
        assertThat(result).isEqualTo("default-Message");
    }

    @Test
    void argumentMessage(){
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, null,Locale.KOREAN)).isEqualTo("안녕");
    }
    @Test
    void enLang(){
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

}
