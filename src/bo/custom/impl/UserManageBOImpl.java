package bo.custom.impl;

import bo.custom.UserManageBO;
import dao.DAOFactory;
import dao.custom.UserManageDAO;
import dto.UserDTO;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserManageBOImpl implements UserManageBO {

    private final UserManageDAO userManageDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.UserManageDAOImpl);

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userManageDAO.add(new User(userDTO.getName(), userDTO.getAddress(), userDTO.getContact(), userDTO.getEmailAddress(),
                userDTO.getPassword()));
    }

    @Override
    public UserDTO validateUser(String userName, String password) throws SQLException, ClassNotFoundException {
        User user = userManageDAO.validateUser(userName, password);
        return (user != null) ? new UserDTO(user.getId(), user.getName(), user.getAddress(), user.getContact(), user.getEmailAddress(),
                user.getPassword()) : null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userManageDAO.update(new User(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getContact(),
                userDTO.getEmailAddress(), userDTO.getPassword()));
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<User> all = userManageDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User userDTO : all) {
            userDTOS.add(new UserDTO(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getContact(),
                    userDTO.getEmailAddress(), userDTO.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userManageDAO.delete(id);
    }
}
