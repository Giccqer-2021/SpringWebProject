package hod;

import org.springframework.lang.Nullable;

public interface ICombineAdministratorDataCommands {
    @Nullable
    String getPassword(String name);
}
