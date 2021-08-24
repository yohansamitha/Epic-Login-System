package dao;

import dao.custom.impl.UserManageDAOImpl;

import java.io.InvalidClassException;

public class DAOFactory {

    static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case UserManageDAOImpl:
                return (T) new UserManageDAOImpl();
            default:
                return (T) new InvalidClassException("Invalid Class In DAO factory");
        }
    }

    public enum DAOTypes {
        UserManageDAOImpl
    }
}
