package hod;

import malkuth.Employee;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ICombineEmployeeDataCommands {
    List<Employee> getData(int floor, int quantity, @Nullable String query);

    List<Employee> getByID(int id);

    int getCount(@Nullable String query);

    int insertData(String name, int age, String gender, String email, int departmentID);

    List<String> updateData(int id, String name, int age, String gender, String email, int departmentID);

    void deleteData(int id);
}
