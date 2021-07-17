package Service_test;

import hod.ICombineAdministratorDataCommands;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain5 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml", "classpath:command_together.xml");
        ICombineAdministratorDataCommands administratorDataCommands = (ICombineAdministratorDataCommands) context.getBean("administratorService");
        System.out.println(administratorDataCommands.getPassword("安吉拉"));
        System.out.println(administratorDataCommands.getPassword("萨尔瓦多"));
        context.close();
    }
}
