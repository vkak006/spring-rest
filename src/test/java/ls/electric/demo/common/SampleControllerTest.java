package ls.electric.demo.common;

import ls.electric.demo.common.controller.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;


@RunWith(SpringRunner.class)
@WebFluxTest(value = SampleController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class SampleControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void sample() throws Exception{
        this.webTestClient.get().uri("/web-flux")
                .exchange()
                .expectStatus().isOk();
        }
}
