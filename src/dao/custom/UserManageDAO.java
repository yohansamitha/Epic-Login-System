package dao.custom;

import dao.CrudDAO;
import model.User;

import java.sql.SQLException;

public interface UserManageDAO extends CrudDAO<User, String> {
    User validateUser(String userName, String password) throws SQLException, ClassNotFoundException;
}
