package a4.folio;

import java.util.ArrayList;
import java.util.List;

import a4.folio.ApiManger.FolioClient;
import a4.folio.ApiManger.PersonalCapital;
import a4.folio.ApiManger.PersonalInfo;
import a4.folio.ApiManger.RetrofitManager;
import a4.folio.DataType.News;
import a4.folio.DataType.Stock;
import a4.folio.PageInfo.HomePageInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amir on 5/26/2018.
 */

public class ConnectionManager {
    private FolioClient folioClient;
    private PersonalInfo personalInfo;
    private List<Stock> bourseInformation;
    private List<PersonalCapital> personalCapitalList;
    private List<News> bourseNews;
    static String username = "ali";
    private boolean isDonePersonalInfo, isDonePersonalCapital, isDoneBourseInformation, answer, isDoneChangeAmount, isDoneBourseNews;
    public ConnectionManager() {
        folioClient = RetrofitManager.createService(FolioClient.class);
    }


    public HomePageInfo getHomepageInfo() throws Exception {
        HomePageInfo homePageInfo = new HomePageInfo();

        getPInfoAPI();

        while (!isDonePersonalInfo) {
            synchronized (this) {
                System.out.println("Waiting for personal info");
                wait(1);
            }
        }
        getPCapitalAPI();
        while (!isDonePersonalCapital) {
            synchronized (this) {
                System.out.println("Waiting for personal capital");
                wait(1);
            }
        }
        homePageInfo.setCashMoney((int) personalInfo.getMoney());
        ArrayList<Stock> posi = new ArrayList<>();
        ArrayList<Stock> nega = new ArrayList<>();
        for (int i = 0; i < personalCapitalList.size(); i++) {
            Stock newStock = personalCapitalList.get(i).getBourse();
            newStock.setMojoodi(personalCapitalList.get(i).getNumber_of_stocks_person_has());
            try {
                if (Double.valueOf(newStock.getLastest_Percentage()) < 0) {
                    nega.add(newStock);
                } else {
                    posi.add(newStock);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(newStock.getNamad());
            }
        }
        homePageInfo.setPositives(posi);
        homePageInfo.setNegatives(nega);
        // personalcapital + personalinfo ==>>> homepageinfo
        return homePageInfo;
    }

    public List<Stock> getBourseInformation() throws InterruptedException {
        getBInfoAPI();
        while (!isDoneBourseInformation) {
            synchronized (this) {
                System.out.println("Waiting for bourse info");
                wait(1);
            }
        }

        getPCapitalAPI();
        while (!isDonePersonalCapital) {
            synchronized (this) {
                System.out.println("Waiting for personal capital");
                wait(1);
            }
        }
        for (int i = 0; i < personalCapitalList.size(); i++) {
            int sci = personalCapitalList.get(i).getNumber_of_stocks_person_has();
            Stock scs = personalCapitalList.get(i).getBourse();
            for (int j = 0; j < bourseInformation.size(); j++) {
                if (bourseInformation.get(j).getNamad().equals(scs.getNamad())) {
                    bourseInformation.get(j).setMojoodi(sci);
                }
            }
        }
        return bourseInformation;
    }

    public boolean trade(String symbol, int newAmount, int newCash) {
        changeSymbolAmountOnServer(symbol, newAmount, newCash);
        while (!isDoneChangeAmount) {
            synchronized (this) {
                try {
                    wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return answer;
    }

    public List<News> getBourseNews() throws Exception {
        getBNewsAPI();
        while (!isDoneBourseNews) {
            synchronized (this) {
                System.out.println("Waiting for bourse news");
                wait(1);
            }
        }
        return bourseNews;
    }

    private void changeSymbolAmountOnServer(String symbol, int newAmount, int newCash) {
        isDoneChangeAmount = false;
        answer = false;
        Call<Void> trade = folioClient.changeSymbolAmountOnServer(username + "$" + String.valueOf(newAmount) + "$" + String.valueOf(newCash) + "$" + symbol);
        trade.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                isDoneChangeAmount = true;
                answer = true;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
                isDoneChangeAmount = true;
                answer = false;
            }
        });
    }

    private void getPInfoAPI() {
        isDonePersonalInfo = false;
        Call<PersonalInfo> personalInfoCall = folioClient.getPersonalInfo(username);
        personalInfoCall.enqueue(new Callback<PersonalInfo>() {
            @Override
            public void onResponse(Call<PersonalInfo> call, Response<PersonalInfo> response) {
                personalInfo = response.body();
                isDonePersonalInfo = true;
            }

            @Override
            public void onFailure(Call<PersonalInfo> call, Throwable throwable) {
                throwable.printStackTrace();
                isDonePersonalInfo = true;
            }
        });
    }

    private void getPCapitalAPI() {
        isDonePersonalCapital = false;
        Call<List<PersonalCapital>> personalCapitalCall = folioClient.getPersonalCapital(username);
        personalCapitalCall.enqueue(new Callback<List<PersonalCapital>>() {
            @Override
            public void onResponse(Call<List<PersonalCapital>> call, Response<List<PersonalCapital>> response) {
                personalCapitalList = response.body();
                isDonePersonalCapital = true;
            }

            @Override
            public void onFailure(Call<List<PersonalCapital>> call, Throwable throwable) {
                throwable.printStackTrace();
                isDonePersonalCapital = true;
            }
        });

    }

    private void getBInfoAPI() {
        isDoneBourseInformation = false;
        Call<List<Stock>> bourseInfoCall = folioClient.getBourseInfo();
        bourseInfoCall.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                bourseInformation = response.body();
                isDoneBourseInformation = true;
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable throwable) {
                throwable.printStackTrace();
                isDoneBourseInformation = true;
            }
        });
    }

    private void getBNewsAPI() {
        isDoneBourseNews = false;
        Call<List<News>> bourseNewsCall = folioClient.getBourseNews();
        bourseNewsCall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                bourseNews = response.body();
                isDoneBourseNews = true;
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable throwable) {
                isDoneBourseNews = true;
                throwable.printStackTrace();
            }
        });

    }


}
