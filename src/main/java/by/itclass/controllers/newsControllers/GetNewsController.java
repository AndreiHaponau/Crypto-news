package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;
import by.itclass.model.enums.NewsAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetNewsController", value = AppConstant.GET_NEWS_CONT_URL)
public class GetNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Параметр action будет хранить название действия, которые
        //мы собираемся выполнять над новостью: просмотреть; редактировать
        String action = request.getParameter(AppConstant.ACTION_LABEL);
        //Получаем id новости, для которой кликнули кнопку "Редактировать"
        String id = request.getParameter(AppConstant.ID_LABEL);
        int idNews = Integer.parseInt(id);
        NewsAction newsAction = NewsAction.valueOf(action.toUpperCase());

        News news = newsDAO.getById(idNews);
        request.setAttribute(AppConstant.NEWS_ATTR, news);//NEWS_ATTR=news
        if (newsAction == NewsAction.VIEW) {
            forward(request, response, AppConstant.VIEW_NEWS_JSP);
        } else if (newsAction == NewsAction.EDIT) {
            forward(request, response, AppConstant.EDIT_NEWS_JSP);
        }
    }
}
