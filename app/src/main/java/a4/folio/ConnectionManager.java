package a4.folio;

import java.util.List;

import a4.folio.ApiManger.FolioClient;
import a4.folio.ApiManger.PersonalCapital;
import a4.folio.ApiManger.PersonalInfo;
import a4.folio.ApiManger.RetrofitManager;
import a4.folio.DataType.News;
import a4.folio.DataType.Stock;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amir on 5/26/2018.
 */

public class ConnectionManager {

    private FolioClient folioClient;
    static String username = "ali";  //will be changed after Login feature

    private PersonalInfoDataListener personalInfoDataListener;
    private PersonalCapitalDataListener personalCapitalDataListener;
    private NewsDataListener newsDataListener;
    private BourseInfoDateListener bourseInfoDateListener;
    private TradeConfirmListener tradeConfirmListener;

    public ConnectionManager() {
        folioClient = RetrofitManager.createService(FolioClient.class);
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
                personalInfoDataListener.onDataLoaded(response.body());
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
                personalCapitalDataListener.onDataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<PersonalCapital>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    private void getBInfoAPI() {
        Call<List<Stock>> bourseInfoCall = folioClient.getBourseInfo();
        bourseInfoCall.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                bourseInfoDateListener.onDataLoaded(response.body());
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

    public void requestHomePageInfo(){
        getPInfoAPI();
        getPCapitalAPI();
    }

    public PersonalInfoDataListener getPersonalInfoDataListener() {
        return personalInfoDataListener;
    }

    public void setPersonalInfoDataListener(PersonalInfoDataListener personalInfoDataListener) {
        this.personalInfoDataListener = personalInfoDataListener;
    }

    public PersonalCapitalDataListener getPersonalCapitalDataListener() {
        return personalCapitalDataListener;
    }

    public void setPersonalCapitalDataListener(PersonalCapitalDataListener personalCapitalDataListener) {
        this.personalCapitalDataListener = personalCapitalDataListener;
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
}
