package test;

import com.soft1851.spring.ioc.dao.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class PhoneTest extends Phone {
    @Autowired
    Phone phone;
    @Test
    public void test(){
        System.out.println(phone);
    }
}