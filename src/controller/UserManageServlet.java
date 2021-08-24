package controller;

import bo.BOFactory;
import bo.custom.UserManageBO;
import dto.UserDTO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserManageServlet", urlPatterns = "/userManageServlet")
public class UserManageServlet extends HttpServlet {

    private final UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonReader reader = Json.createReader(request.getReader());
            JsonObject user = reader.readObject();
            String userName = user.getString("userName");
            String userAddress = user.getString("userAddress");
            String phoneNumber = user.getString("phoneNumber");
            String emailAddress = user.getString("emailAddress");
            String password = user.getString("password");
            userManageBO.saveUser(new UserDTO(userName, userAddress, phoneNumber, emailAddress, password));
            PrintWriter writer = response.getWriter();
            response.setContentType("application/json");
            JsonObjectBuilder responseObject = Json.createObjectBuilder();
            responseObject.add("User Add", "Successful");
            writer.print(responseObject.build());
            writer.close();
            reader.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }
}
