package malkuth;

public class Administrator {
    private int u_id;
    private String u_name;
    private String password;

    public Administrator(int u_id, String u_name, String password) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.password = password;
    }

    public Administrator() {
    }

    public int getU_id() {
        return u_id;
    }

    public Administrator setU_id(int u_id) {
        this.u_id = u_id;
        return this;
    }

    public String getU_name() {
        return u_name;
    }

    public Administrator setU_name(String u_name) {
        this.u_name = u_name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Administrator setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
