package a4.folio.ApiManger;

import java.util.List;

import a4.folio.DataType.News;
import a4.folio.DataType.Stock;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by amir on 5/26/2018.
 */

public interface FolioClient {
    @GET("/Personapp_api/information/{user}")
    Call<PersonalInfo> getPersonalInfo(@Path("user") String username);
    @GET("/Bourseapp_api/all_information")
    Call<List<Stock>> getBourseInfo();
    @GET("/Membership_api/person_have/{user}")
    Call<List<PersonalCapital>> getPersonalCapital(@Path("user") String username);
    @GET("/Membership/buy_sell")
    Call<Void> changeSymbolAmountOnServer(@Query("change") String info );
    @GET ("/Newsapp_api/see_News")
    Call<List<News>> getBourseNews();


}
