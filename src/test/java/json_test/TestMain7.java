package json_test;

import com.alibaba.fastjson.JSON;
import hod.ICombineEmployeeDataCommands;
import malkuth.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain7 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml", "classpath:command_together.xml");
        ICombineEmployeeDataCommands employeeDataCommands = (ICombineEmployeeDataCommands) context.getBean("employeeService");
        List<Employee> data = employeeDataCommands.getData(0, 3, null);
//        System.out.println(data);
//        String output = JSON.toJSONString(data.get(0));
//        System.out.println(output);
        String output2 = JSON.toJSONString(data);
        System.out.println(output2);
        context.close();
    }
}
