package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UserManageDAO;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManageDAOImpl implements UserManageDAO {
    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        String sql = "insert into User (name, address, contact, emailAddress, password) VALUES (?,?,?,?,?);";
        return CrudUtil.executeUpdate(sql,
                user.getName(),
                user.getAddress(),
                user.getContact(),
                user.getEmailAddress(),
                user.getPassword()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "delete from user where Id=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        String sql = "update User set name=?,address=?,contact=?,emailAddress=? where Id = ?;";
        return CrudUtil.executeUpdate(sql,
                user.getName(),
                user.getAddress(),
                user.getContact(),
                user.getEmailAddress(),
                user.getId());
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        String sql = "select * from User";
        ArrayList<User> allUsers = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        while (resultSet.next()) {
            allUsers.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    ""
            ));
        }
        return allUsers;
    }

    @Override
    public User validateUser(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from User where emailAddress =? and password=?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, userName, password);
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }
}
