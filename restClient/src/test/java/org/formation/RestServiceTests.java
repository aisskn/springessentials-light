package org.formation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class RestServiceTests {
    @Autowired
    public RestService restService;

    @Test
    public void testUserLoad(){

        User user = restService
                    .loadUser(1L)
                    .block();
        assertThat(user).isNotNull();

//               Block ou subcribe
////               .subscribe( u -> {
//                   System.out.println(u);
//                   assertThat(u).isNotNull();
//                       } );

    }
}
