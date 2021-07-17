package netzach;

import hod.ICombineEmployeeDataCommands;
import malkuth.Department;
import malkuth.Employee;
import malkuth.EmployeeParameterClass;
import org.springframework.lang.Nullable;
import yesod.IEmployeeOperation;

import java.util.List;

public class CombineEmployeeDataCommands implements ICombineEmployeeDataCommands {
    private IEmployeeOperation operations;

    public IEmployeeOperation getOperations() {
        return operations;
    }

    public CombineEmployeeDataCommands setOperations(IEmployeeOperation operations) {
        this.operations = operations;
        return this;
    }

    @Override
    public List<Employee> getData(int floor, int quantity, @Nullable String query) {
        return operations.getByCondition(new EmployeeParameterClass(floor, quantity, query));
    }

    @Override
    public List<Employee> getByID(int id) {
        return operations.getByID(id);
    }

    @Override
    public int getCount(@Nullable String query) {
        return operations.getCount(new EmployeeParameterClass(0, 0, query));
    }

    @Override
    public int insertData(String name, int age, String gender, String email, int departmentID) {
        operations.insertData(new Employee(0, name, age, gender, email, new Department(departmentID, null)));
        return operations.getNewID();
    }

    @Override
    public List<String> updateData(int id, String name, int age, String gender, String email, int departmentID) {
        operations.modifyData(new Employee(id, name, age, gender, email, new Department(departmentID, null)));
        return operations.getDepartmentName(departmentID);
    }

    @Override
    public void deleteData(int id) {
        operations.deleteData(id);
        //dead code
//        int error = 0 / 0;
    }
}
