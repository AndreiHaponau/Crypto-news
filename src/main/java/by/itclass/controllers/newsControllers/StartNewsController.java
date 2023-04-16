package by.itclass.controllers.newsControllers;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.News;
import by.itclass.model.enums.NewsAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StartNewsController", value = AppConstant.START_NEWS_CONT_URL)
public class StartNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> newsListSorted = newsDAO.news(NewsAction.SORTED);
        List<News> newsListTop = newsDAO.news(NewsAction.TOP);

        request.setAttribute(AppConstant.NEWS_LIST_ATTR, newsListSorted);
        request.setAttribute(AppConstant.NEWS_LIST_TOP_ATTR, newsListTop);//newsListTop
        forward(request, response, AppConstant.INDEX_JSP);
    }
}
