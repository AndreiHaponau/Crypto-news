package by.itclass.filters;

import by.itclass.constants.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Для того, чтобы настроить фильтр на определнные ресурсы
//нужно указать их url в массих адресов value текущего фильтра
@WebFilter(filterName = "UserNotAuthorizationFilter", value = {AppConstant.CABINET_JSP, AppConstant.MY_NEWS_JSP})
public class UserNotAuthorizationFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //Для того, чтобы понять авторизирован пользователь, обратиться к сессии
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Object user = session.getAttribute(AppConstant.USER_ATTR);

        if (user != null) {
            //Если в цепочке фильтор их несколько, то метод doFilter() позволяет перейти к
            //след. фильтру, или если цепочка закончилась, то в конце перейдет в назначенный ресурс
            //т.е. к тому, к которому был адресован запрос
            chain.doFilter(req, resp);
        } else {
            //Если мы хотим запретить доступ к ресурсу, то метод doFilter() не должен вызываться
            request.setAttribute(AppConstant.MESSAGE_ATTR, AppConstant.USER_IS_NOT_AUTHORIZATION_MESSAGE);
            request.getRequestDispatcher(AppConstant.REG_JSP)
                   .forward(request, resp);
        }

    }
}
