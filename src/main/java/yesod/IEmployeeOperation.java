package yesod;

import malkuth.Employee;
import malkuth.EmployeeParameterClass;

import java.util.List;

public interface IEmployeeOperation {
    List<Employee> getAll();

    List<Employee> getByCondition(EmployeeParameterClass parms);

    List<Employee> getByID(int eid);

    int getCount(EmployeeParameterClass parms);

    void insertData(Employee employee);

    void modifyData(Employee employee);

    void deleteData(int e_id);

    int getNewID();

    List<String> getDepartmentName(int departmentID);
}
