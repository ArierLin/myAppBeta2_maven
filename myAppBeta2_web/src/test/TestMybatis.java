import com.jr.entity.User;
import com.jr.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jql
 * @create 2017-11-02 16:55
 * @desc mybatis单元测试类
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextTest.xml")
public class TestMybatis {

//    private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContextTest.xml");
//
//    private UserService userService = (UserService) ac.getBean("userServiceImpl");

    @Autowired
    private UserService userService;

    @Test
    public void testGetOneUser(){
        String username = "测试";
        User user = userService.getOneUserByName(username);
        Assert.assertNull(user);
    }

}
