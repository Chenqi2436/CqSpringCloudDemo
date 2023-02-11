import com.cq.springcloud.PaymentMain8001;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentMain8001.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApplicationTests {

    @Test
    public void test(){
        System.out.println(123);
    }
}
