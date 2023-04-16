package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthorizationController", value = AppConstant.AUTHORIZATION_CONT_URL)
public class AuthorizationController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Временный контроллер авторизации для работы в классе
        User user = new User(1, "admin", "admin@mail.ru");

        HttpSession session = request.getSession();
        session.setAttribute(AppConstant.USER_ATTR, user);

        redirect(response, AppConstant.CABINET_JSP);
    }
}
