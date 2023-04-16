package by.itclass.controllers.userControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.User;
import by.itclass.model.utils.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthorizationServlet", value = "/auth")
public class AuthorizationServlet extends AbstractUserController {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(AppConstant.EMAIL_LABEL);
        String password = request.getParameter(AppConstant.PASSWORD_LABEL);

        User user = userDAO.authorization(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            ImageUtil.createImageFile(getServletContext().getRealPath(AppConstant.IMAGE_FOLDER_NAME), user.getImage());
            session.setAttribute(AppConstant.USER_ATTR, user);
            redirect(response, AppConstant.CABINET_JSP);
        } else {
            forward(request, response, AppConstant.REG_JSP, AppConstant.NOT_USER_REGISTRATION_MESSAGE);
        }
    }
}
