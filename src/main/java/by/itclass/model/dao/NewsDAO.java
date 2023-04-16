package by.itclass.model.dao;

import by.itclass.model.beans.News;
import by.itclass.model.beans.User;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.db.SQLRequest;
import by.itclass.model.enums.NewsAction;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class NewsDAO {

    public boolean like(News news, User user) {
        boolean isLiked = false;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.INSERT_LIKE_FOR_NEWS)) {
            pst.setInt(1, news.getId());
            pst.setInt(2, user.getId());
            isLiked = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isLiked;
    }

    public void delete(News news) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.DELETE_NEWS_BY_ID)) {
            pst.setInt(1, news.getId());
            pst.executeUpdate();//delete,update,insert
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<News> news(NewsAction action) {
        List<News> newsList = new ArrayList<>();
        String sql = action.getSql();

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(SQLRequest.ID_COLUMN);
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                Timestamp date = rs.getTimestamp(SQLRequest.DATE_COLUMN);
                int likes = rs.getInt(SQLRequest.LIKES_COLUMN);
                newsList.add(new News(id, title, text, new Date(date.getTime()), likes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public boolean save(News news, NewsAction action) {
        boolean isSave = false;
        String sql = action.getSql();

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            int id = (action == NewsAction.ADD) ? news.getUser().getId() : news.getId();
            pst.setString(1, news.getTitle());
            pst.setString(2, news.getText());
            pst.setInt(3, id);
            //Если сохранение будет успешно выполнено, то метод
            //executeUpdate() вернет количество обновленных строк
            //в нашем случае, это всегда будет 1 или 0
            //если обновили то isSave = 1 > 0 - true
            isSave = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSave;
    }

    public News getById(int idNews) {
        News news = null;

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_NEWS_BY_ID)) {
            pst.setInt(1, idNews);
            pst.setInt(2, idNews);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                Timestamp date = rs.getTimestamp(SQLRequest.DATE_COLUMN);
                int likes = rs.getInt(SQLRequest.LIKES_COLUMN);
                news = new News(idNews, title, text, new Date(date.getTime()), likes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> getAllByIdUser(User user) {
        List<News> newsList = new ArrayList<>();

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SQLRequest.SELECT_NEWS_BY_ID_USER)) {
            pst.setInt(1, user.getId());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(SQLRequest.ID_COLUMN);
                String title = rs.getString(SQLRequest.TITLE_COLUMN);
                String text = rs.getString(SQLRequest.TEXT_COLUMN);
                Timestamp date = rs.getTimestamp(SQLRequest.DATE_COLUMN);
                newsList.add(new News(id, title, text, user,  new Date(date.getTime())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
