package it.fides.project.servlet;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import it.fides.project.models.entities.UserEntity;
import it.fides.project.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Query1Servlet")
public class Query1Servlet extends HttpServlet {
	
	@Autowired
	private UserService userService;

	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<UserEntity> youngestUsers = userService.getTwoYoungestUsers();
		String startWithConsonantRegex = "(?i)[^aeiou].*";

		for (UserEntity user : youngestUsers) {
			if (user.getFirstNameUser().matches(startWithConsonantRegex)) {
				List<UserEntity> users = userService.setYoungUsers(youngestUsers);
				request.setAttribute("result", users);
		        RequestDispatcher rd = request.getRequestDispatcher("/query1.jsp");
		        rd.forward(request, response);
			}
		}
    }
}
