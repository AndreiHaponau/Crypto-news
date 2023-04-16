package by.itclass.model.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
    //Шаблон вывода даты в формате : день.месяц.год
    //dd - обозначает день
    //MM - обозначает месяц
    //yyyy - обозначает год
    public static final String DD_MM_YYYY = "dd.MM.yyyy";
    //HH - обозначает часы в 24-ом формате
    //mm - обозначает минуты с лидирующими нулями
    public static final String DD_MM_YYYY_HH_MM = "dd.MM.yyyy HH:mm";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();

    public static String format(Date date, String pattern) {
        //Метод applyPattern() применяет формат для представления даты к строке
        dateFormat.applyPattern(pattern);
        //Метод format() представляет дату в строку согласно формату
        return dateFormat.format(date);
    }

//    public static Date parse(Timestamp timestamp) {
//        //Получаем время в мил.сек.
//        long time = timestamp.getTime();
//        return new Date(time);
//    }
}
