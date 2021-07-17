package data_test;

import malkuth.EmployeeParameterClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yesod.IEmployeeOperation;

public class TestMain3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml");
        IEmployeeOperation data = context.getBean(IEmployeeOperation.class);
//        List<Employee> all = data.getByCondition(new EmployeeParameterClass());
//        System.out.println(all);
//        List<Employee> byCondition = data.getByCondition(new EmployeeParameterClass(0, 9, ""));
//        System.out.println(byCondition);
        int count = data.getCount(new EmployeeParameterClass(0,0,"加"));
        System.out.println(count);
        context.close();
    }
}
