package yesod;

import malkuth.Administrator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAdministratorOperation {
    List<Administrator> getAll();

    List<String> getPassword(@Param("u_name") String name);
}
