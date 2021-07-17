package malkuth;

import com.alibaba.fastjson.annotation.JSONField;

public class Employee {
    @JSONField(name = "id")
    private int e_id;
    @JSONField(name = "name")
    private String e_name;
    @JSONField(name = "age")
    private int age;
    @JSONField(name = "gender")
    private String gender;
    @JSONField(name = "email")
    private String email;
    @JSONField(name = "department")
    private Department department;

    public Employee(int e_id, String e_name, int age, String gender, String email, Department department) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public Employee() {
    }

    public int getE_id() {
        return e_id;
    }

    public Employee setE_id(int e_id) {
        this.e_id = e_id;
        return this;
    }

    public String getE_name() {
        return e_name;
    }

    public Employee setE_name(String e_name) {
        this.e_name = e_name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Employee setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Employee setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", e_name='" + e_name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
