package controller;

import bo.BOFactory;
import bo.custom.UserManageBO;
import dto.UserDTO;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private final UserManageBO userManageBO = BOFactory.getInstance().getBO(BOFactory.BoType.UserManageBOImpl);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getHeader("txtUserName");
        String password = request.getHeader("txtPassword");
        System.out.println(userName);
        System.out.println(password);
        JsonObjectBuilder resp = Json.createObjectBuilder();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
                UserDTO userDTO = userManageBO.validateUser(userName, password);
                if (null != userDTO) {
                    resp.add("status", "200");
                    resp.add("operation", "successful");
                    resp.add("message", "authentication successful");
                    resp.add("response", "REDIRECT");
                    writer.print(resp.build());
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("user", userDTO);
                } else {
                    resp.add("status", "403");
                    resp.add("operation", "unsuccessful");
                    resp.add("message", "username or Password is wrong");
                    resp.add("response", "REFRESH");
                    writer.print(resp.build());
                }
            } else {
                resp.add("status", "400");
                resp.add("operation", "unsuccessful");
                resp.add("message", "username or Password is missing");
                resp.add("response", "REFRESH");
                writer.print(resp.build());
            }
            writer.close();
        } catch (SQLException | ClassNotFoundException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = null;
        JsonObjectBuilder response = null;
        try {
            HttpSession httpSession = req.getSession();
            httpSession.removeAttribute("user");
            response = Json.createObjectBuilder();
            response.add("logout", "successful");
            writer = resp.getWriter();
            writer.print(response.build());
            writer.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
