package ls.electric.demo.user;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.config.utils.AesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

    @Test
    public void test() throws Exception {
        AesUtil aesUtil = new AesUtil();
        String enc = aesUtil.encrypt("password1234");
        log.info(enc);

        String dec = aesUtil.decrypt(enc);
        log.info(dec);
    }
}
