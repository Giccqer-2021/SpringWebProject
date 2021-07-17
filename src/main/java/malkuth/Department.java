package malkuth;

import com.alibaba.fastjson.annotation.JSONField;

public class Department {
    @JSONField(name = "departmentID")
    private int d_id;
    @JSONField(name = "departmentName")
    private String d_name;

    public Department(int d_id, String d_name) {
        this.d_id = d_id;
        this.d_name = d_name;
    }

    public Department() {
    }

    public int getD_id() {
        return d_id;
    }

    public Department setD_id(int d_id) {
        this.d_id = d_id;
        return this;
    }

    public String getD_name() {
        return d_name;
    }

    public Department setD_name(String d_name) {
        this.d_name = d_name;
        return this;
    }

    @Override
    public String toString() {
        return "Department{" +
                "d_id=" + d_id +
                ", d_name='" + d_name + '\'' +
                '}';
    }
}
