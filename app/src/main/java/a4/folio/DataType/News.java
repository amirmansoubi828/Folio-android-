package a4.folio.DataType;

/**
 * Created by amir on 6/1/2018.
 */

public class News {
    private String title , news_date , news_Quote , news_Body ;
    int day , month , year ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews_date() {
        return news_date;
    }

    public void setNews_date(String news_date) {
        this.news_date = news_date;
    }

    public String getNews_Quote() {
        return news_Quote;
    }

    public void setNews_Quote(String news_Quote) {
        this.news_Quote = news_Quote;
    }

    public String getNews_Body() {
        return news_Body;
    }

    public void setNews_Body(String news_Body) {
        this.news_Body = news_Body;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
