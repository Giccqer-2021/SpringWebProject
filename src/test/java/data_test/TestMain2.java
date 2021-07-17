package data_test;

import malkuth.Administrator;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yesod.IAdministratorOperation;

import java.util.List;

public class TestMain2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml");
        IAdministratorOperation data = context.getBean(IAdministratorOperation.class);
//        List<Administrator> all = data.getAll();
//        System.out.println(all);
        List<String> 安吉拉 = data.getPassword("安吉拉");
        System.out.println(安吉拉);
        context.close();
    }
}
