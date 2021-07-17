package Service_test;

import hod.ICombineEmployeeDataCommands;
import malkuth.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMain6 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:data_controller.xml", "classpath:command_together.xml");
        ICombineEmployeeDataCommands employeeDataCommands = (ICombineEmployeeDataCommands) context.getBean("employeeService");
//        employeeDataCommands.insertData("TA", 8, "女", "122177871", 5);
        List<Employee> datas = employeeDataCommands.getData(3, 17, "啊");
        for (Employee data : datas) {
            System.out.println(data.getE_id());
        }
//        employeeDataCommands.deleteData(7);
        context.close();
    }
}
