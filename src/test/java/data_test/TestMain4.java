package data_test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import yesod.IEmployeeOperation;

public class TestMain4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml");
        IEmployeeOperation data = context.getBean(IEmployeeOperation.class);
//        data.insertData(new Employee(0, "Hod", 18, "女", "1221112121@qq.com", new Department(4, null)));
//        data.modifyData(new Employee(6, "Net", 20, "男", "32213342@qq.com", new Department(3, null)));
        data.deleteData(6);
        context.close();
    }
}
