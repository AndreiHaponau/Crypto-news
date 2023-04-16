package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;
import by.itclass.model.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikeNewsController", value = AppConstant.LIKE_NEWS_CONT_URL)
public class LikeNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(AppConstant.ID_LABEL);
        int idNews = Integer.parseInt(id);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AppConstant.USER_ATTR);
        News news = new News(idNews);

        newsDAO.like(news, user);
        forward(request, response, AppConstant.GET_NEWS_CONT_URL);
    }
}
