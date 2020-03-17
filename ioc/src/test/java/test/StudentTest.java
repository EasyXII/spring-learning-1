package test;

import com.soft1851.spring.ioc.dao.Phone;
import com.soft1851.spring.ioc.dao.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class StudentTest extends Phone {
    //注入学生类
    @Autowired
    private Student student;
    @Test
    public void test(){
        System.out.println(student);
    }
}
