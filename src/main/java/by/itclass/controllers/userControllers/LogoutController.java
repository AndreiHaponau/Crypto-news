package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = AppConstant.LOGOUT_CONT_URL)
public class LogoutController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        //Можно переходить и на авторизацию
        redirect(response, AppConstant.REG_JSP);
    }
}
