package netzach;

import hod.ICombineAdministratorDataCommands;
import org.springframework.lang.Nullable;
import yesod.IAdministratorOperation;

import java.util.List;

public class CombineAdministratorDataCommands implements ICombineAdministratorDataCommands {
    private IAdministratorOperation operations;

    public IAdministratorOperation getOperations() {
        return operations;
    }

    public CombineAdministratorDataCommands setOperations(IAdministratorOperation operations) {
        this.operations = operations;
        return this;
    }

    @Override
    @Nullable
    public String getPassword(String name) {
        List<String> passwordList = operations.getPassword(name);
        if (!passwordList.isEmpty()) {
            return passwordList.get(0);
        } else {
            return null;
        }
    }
}
