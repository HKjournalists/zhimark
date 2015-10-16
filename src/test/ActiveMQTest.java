import com.spring.mvc.service.ActiveMQ.MessageSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by liluoqi on 15/8/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/activeMqContext.xml"})
public class ActiveMQTest {
    @Autowired
    private MessageSenderService messageSenderService;

//    @Test
//    public void testSend() {
//        for (int i = 0; i < 2; i++) {
//            messageSenderService.sendPlainMessage("你好，生产者！这是消息：" + (i + 1));
//        }
//    }
}
