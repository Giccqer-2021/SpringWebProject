package tiphereth;

import com.alibaba.fastjson.JSON;
import hod.ICombineEmployeeDataCommands;
import malkuth.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class EmployeePageControl {
    public static ICombineEmployeeDataCommands employeeDataCommands;

    static {
        employeeDataCommands = (ICombineEmployeeDataCommands) LoginControl.context.getBean("employeeService");
    }

    @RequestMapping("/employee.html")
    public String getEmployeePage() {
        return "employee";
    }

    @RequestMapping(value = "/getEmployeeData.action", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public String getEmployeeData(@RequestParam("floor") String strFloor, @RequestParam("quantity") String strQuantity, @RequestParam("query") String query) {
        int floor;
        int quantity;
        try {
            floor = Integer.parseInt(strFloor);
            quantity = Integer.parseInt(strQuantity);
        } catch (NumberFormatException e) {
            floor = 0;
            quantity = 0;
        }
        if ("".equals(query)) {
            query = null;
        }
        List<Employee> datas = employeeDataCommands.getData(floor, quantity, query);
        int totalCount = employeeDataCommands.getCount(query);
        String strData = JSON.toJSONString(datas);
        String strDataAndCount;
        if (totalCount == 0) {
            strDataAndCount = "[{\"total\":0}]";
        } else {
            strDataAndCount = "[{\"total\":" + totalCount + "}," + strData.substring(1);
        }
        return strDataAndCount;
    }

    @RequestMapping("/operation.html")
    public ModelAndView getOperation(String id, String method) {
        ModelAndView mv = new ModelAndView("operation");
        mv.addObject("operationID", id);
        mv.addObject("operationMethod", method);
        return mv;
    }

    @RequestMapping(value = "/operationConfirm.action", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.PUT)
    @ResponseBody
    public String setOperation(int id, String name, String age, String gender, String email, String departmentID) {
        int intAge;
        int intDepartmentID;
        try {
            intAge = Integer.parseInt(age);
            intDepartmentID = Integer.parseInt(departmentID);
        } catch (NumberFormatException e) {
            intAge = 0;
            intDepartmentID = 1;
        }
        return employeeDataCommands.updateData(id, name, intAge, gender, email, intDepartmentID).get(0);
    }

    @RequestMapping(value = "/operationConfirm.action", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String insertOperation(String name, String age, String gender, String email, String departmentID) {
        int intAge;
        int intDepartmentID;
        try {
            intAge = Integer.parseInt(age);
            intDepartmentID = Integer.parseInt(departmentID);
        } catch (NumberFormatException e) {
            intAge = 0;
            intDepartmentID = 1;
        }
        int id = employeeDataCommands.insertData(name, intAge, gender, email, intDepartmentID);
        return JSON.toJSONString(employeeDataCommands.getByID(id).get(0));
//        return "redirect:/employee.html";
    }

    //    @RequestMapping(value = "/deleteConfirm.action", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.GET)
    @RequestMapping(value = "/operationConfirm.action", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteOperation(int id) {
        employeeDataCommands.deleteData(id);
//        return "redirect:/employee.html";
    }

    private String decodeString(String original) {
        return new String(original.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
