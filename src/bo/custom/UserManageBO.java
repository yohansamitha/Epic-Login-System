package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

import java.sql.SQLException;

public interface UserManageBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    UserDTO validateUser(String userName, String password) throws SQLException, ClassNotFoundException;
}
