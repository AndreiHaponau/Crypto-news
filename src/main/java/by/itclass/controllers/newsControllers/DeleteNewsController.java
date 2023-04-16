package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteNewsController", value = AppConstant.DELETE_NEWS_CONT_URL)
public class DeleteNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(AppConstant.ID_LABEL);

        int idNews = Integer.parseInt(id);
        News news = new News(idNews);

        newsDAO.delete(news);

        redirect(response, AppConstant.USER_LIST_NEWS_CONT_URL);
    }
}
