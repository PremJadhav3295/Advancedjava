import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/register")
public class StudentRegistration extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String firstname = servletRequest.getParameter("firstname");
        String lastname = servletRequest.getParameter("lastname");
        String email = servletRequest.getParameter("email");


        String url = "jdbc:mysql://localhost:3306/csj25";
        String username = "root";
        String password = "Prem@2001";
        String query = "insert into student( firstname, lastname, email)values(?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter writer = servletResponse.getWriter();
        writer.println("Successfully registered" + firstname + "\t" + lastname + "\t" + email);

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
