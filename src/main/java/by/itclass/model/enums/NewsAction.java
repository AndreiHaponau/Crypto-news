package by.itclass.model.enums;

import by.itclass.model.db.SQLRequest;

public enum NewsAction {
    //GET_LIST_BY_ID_USER(SELECT_BY_ID_USER)
    //GET_BY_ID(SELECT...)
    VIEW,
    SORTED(SQLRequest.SELECT_NEWS_SORTED_BY_DATE),
    TOP(SQLRequest.SELECT_NEWS_BY_TOP_LIKES),
    ADD(SQLRequest.INSERT_NEWS),
    EDIT(SQLRequest.UPDATE_NEWS);

    private String sql;

    NewsAction() {
    }

    NewsAction(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
