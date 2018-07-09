package a4.folio;

import java.util.List;

import a4.folio.ApiManger.FolioClient;
import a4.folio.ApiManger.RetrofitManager;
import a4.folio.DataType.Movie;
import a4.folio.DataType.News;
import a4.folio.DataType.PersonalCapital;
import a4.folio.DataType.PersonalInfo;
import a4.folio.DataType.ResultMessage;
import a4.folio.DataType.Stock;
import a4.folio.Listeners.BourseInfoDateListener;
import a4.folio.Listeners.HomePageDataListener;
import a4.folio.Listeners.LogoutResultListener;
import a4.folio.Listeners.MovieListPageDataListener;
import a4.folio.Listeners.NewsDataListener;
import a4.folio.Listeners.ResultListener;
import a4.folio.Listeners.TradeConfirmListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amir on 5/26/2018.
 */

public class ConnectionManager {

    static private FolioClient folioClient;
    static private String username = "ali";

    private HomePageDataListener homePageDataListener;
    private NewsDataListener newsDataListener;
    private BourseInfoDateListener bourseInfoDateListener;
    private TradeConfirmListener tradeConfirmListener;
    private MovieListPageDataListener movieListPageDataListener;
    private ResultListener loginListener, createUserListener;
    private LogoutResultListener logoutResultListener;

    private List<Stock> stocksCash;
    private List<PersonalCapital> personalCapitalsCash;
    private PersonalInfo personalInfoCash;

    private boolean isDoneStocks, isDoneCapitals, isDonePersonal;

    public ConnectionManager() {

        folioClient = RetrofitManager.createService(FolioClient.class);

        isDoneCapitals = false;
        isDonePersonal = false;
        isDoneStocks = false;
    }

    public ConnectionManager(String username, String password) {
        folioClient = RetrofitManager.createService(FolioClient.class, username, password);

        isDoneCapitals = false;
        isDonePersonal = false;
        isDoneStocks = false;
    }


    private void changeSymbolAmountOnServer(String symbol, int newAmount, int newCash) {
        final Call<Void> trade = folioClient.changeSymbolAmountOnServer(username + "$" + String.valueOf(newAmount) + "$" + String.valueOf(newCash) + "$" + symbol);
        trade.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                tradeConfirmListener.onTradeComplete(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
                tradeConfirmListener.onTradeComplete(false);

            }
        });
    }

    private void getPInfoAPI() {
        Call<PersonalInfo> personalInfoCall = folioClient.getPersonalInfo(username);
        personalInfoCall.enqueue(new Callback<PersonalInfo>() {
            @Override
            public void onResponse(Call<PersonalInfo> call, Response<PersonalInfo> response) {
                if (isDoneCapitals) {
                    homePageDataListener.onDataLoaded(personalCapitalsCash, response.body());
                    isDoneCapitals = false;
                } else {
                    personalInfoCash = response.body();
                    isDonePersonal = true;
                }
            }

            @Override
            public void onFailure(Call<PersonalInfo> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void getPCapitalAPI() {
        Call<List<PersonalCapital>> personalCapitalCall = folioClient.getPersonalCapital(username);
        personalCapitalCall.enqueue(new Callback<List<PersonalCapital>>() {
            @Override
            public void onResponse(Call<List<PersonalCapital>> call, Response<List<PersonalCapital>> response) {
                if (isDoneStocks) {
                    bourseInfoDateListener.onDataLoaded(stocksCash, response.body());
                    isDoneStocks = false;
                } else {
                    personalCapitalsCash = response.body();
                    isDoneCapitals = true;
                }

                if (isDonePersonal) {
                    homePageDataListener.onDataLoaded(response.body(), personalInfoCash);
                    isDonePersonal = false;
                } else {
                    personalCapitalsCash = response.body();
                    isDoneCapitals = true;
                }
            }

            @Override
            public void onFailure(Call<List<PersonalCapital>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void getBInfoAPI() {
        final Call<List<Stock>> bourseInfoCall = folioClient.getBourseInfo();
        bourseInfoCall.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if (isDoneCapitals) {
                    bourseInfoDateListener.onDataLoaded(response.body(), personalCapitalsCash);
                    isDoneCapitals = false;
                } else {
                    isDoneStocks = true;
                    stocksCash = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void getBNewsAPI() {
        Call<List<News>> bourseNewsCall = folioClient.getBourseNews();
        bourseNewsCall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsDataListener.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void getBMoviesAPI() {
        Call<List<Movie>> bourseMoviesCall = folioClient.getBourseMovies();
        bourseMoviesCall.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieListPageDataListener.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    //// FIXME: 7/9/2018
    private void loginApi(String username, String password) {
        Call<ResultMessage> logincall = folioClient.login(username + "*" + password);
        System.out.println("login");
        logincall.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                loginListener.onResultReceived(response.body());

            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable throwable) {
                throwable.printStackTrace();
                loginListener.onResultReceived(new ResultMessage(false, "connectionProblem"));
            }
        });
    }

    //// FIXME: 7/9/2018
    private void logoutApi(String username) {
        Call<ResultMessage> logoutCall = folioClient.logout(username + "*" + "0000");
        System.out.println("logout");
        logoutCall.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                logoutResultListener.onResultReceived(response.body());
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable throwable) {
                throwable.printStackTrace();
                logoutResultListener.onResultReceived(new ResultMessage(false, "connectionProblem"));
            }
        });
    }

    //// FIXME: 7/9/2018
    private void createUserApi(String username, String password) {
        Call<ResultMessage> user = folioClient.createUser("");
        user.enqueue(new Callback<ResultMessage>() {
            @Override
            public void onResponse(Call<ResultMessage> call, Response<ResultMessage> response) {
                createUserListener.onResultReceived(response.body());
            }

            @Override
            public void onFailure(Call<ResultMessage> call, Throwable throwable) {
                throwable.printStackTrace();
                createUserListener.onResultReceived(new ResultMessage(false, "connectionProblem"));
            }
        });
    }

    public void requestLogin(String username, String password) {
        loginApi(username, password);
    }

    public void requestLogout(String username) {
        logoutApi(username);
    }

    public void requestCreateUser(String username, String password) {
        createUserApi(username, password);
    }

    public void requestHomePageInfo() {
        getPInfoAPI();
        getPCapitalAPI();
    }

    public void requestStockListPageInfo() {
        getPCapitalAPI();
        getBInfoAPI();
    }

    public void requestNewsPageInfo() {
        getBNewsAPI();
    }

    public void requestMoviesPageInfo() {
        getBMoviesAPI();
    }

    public boolean trade(String symbol, int newAmount, int newCash) {
        changeSymbolAmountOnServer(symbol, newAmount, newCash);
        return true;
    }

    public HomePageDataListener getHomePageDataListener() {
        return homePageDataListener;
    }

    public void setHomePageDataListener(HomePageDataListener homePageDataListener) {
        this.homePageDataListener = homePageDataListener;
    }

    public NewsDataListener getNewsDataListener() {
        return newsDataListener;
    }

    public void setNewsDataListener(NewsDataListener newsDataListener) {
        this.newsDataListener = newsDataListener;
    }

    public BourseInfoDateListener getBourseInfoDateListener() {
        return bourseInfoDateListener;
    }

    public void setBourseInfoDateListener(BourseInfoDateListener bourseInfoDateListener) {
        this.bourseInfoDateListener = bourseInfoDateListener;
    }

    public TradeConfirmListener getTradeConfirmListener() {
        return tradeConfirmListener;
    }

    public void setTradeConfirmListener(TradeConfirmListener tradeConfirmListener) {
        this.tradeConfirmListener = tradeConfirmListener;
    }

    public MovieListPageDataListener getMovieListPageDataListener() {
        return movieListPageDataListener;
    }

    public void setMovieListPageDataListener(MovieListPageDataListener movieListPageDataListener) {
        this.movieListPageDataListener = movieListPageDataListener;
    }

    public ResultListener getLoginListener() {
        return loginListener;
    }

    public void setLoginListener(ResultListener loginListener) {
        this.loginListener = loginListener;
    }

    public LogoutResultListener getLogoutResultListener() {
        return logoutResultListener;
    }

    public void setLogoutResultListener(LogoutResultListener logoutResultListener) {
        this.logoutResultListener = logoutResultListener;
    }

    public ResultListener getCreateUserListener() {
        return createUserListener;
    }

    public void setCreateUserListener(ResultListener createUserListener) {
        this.createUserListener = createUserListener;
    }

    static public String getUsername() {
        return username;
    }

    static public void setUsername(String un) {
        username = un;
    }
}
