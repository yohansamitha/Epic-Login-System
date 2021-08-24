package bo;

import bo.custom.impl.UserManageBOImpl;

import java.io.InvalidClassException;

public class BOFactory {

    static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BoType boType) {
        if (boType == BoType.UserManageBOImpl) {
            return (T) new UserManageBOImpl();
        }
        return (T) new InvalidClassException("Invalid Class Type");
    }

    public enum BoType {
        UserManageBOImpl
    }
}
