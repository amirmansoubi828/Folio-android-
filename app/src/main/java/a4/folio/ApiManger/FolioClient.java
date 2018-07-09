package a4.folio.ApiManger;

import java.util.List;

import a4.folio.DataType.Movie;
import a4.folio.DataType.News;
import a4.folio.DataType.PersonalCapital;
import a4.folio.DataType.PersonalInfo;
import a4.folio.DataType.ResultMessage;
import a4.folio.DataType.Stock;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    Call<Void> changeSymbolAmountOnServer(@Query("change") String info);

    @GET("/Newsapp_api/see_News")
    Call<List<News>> getBourseNews();

    @GET("/Education_api/all_videos")
    Call<List<Movie>> getBourseMovies();

    @GET("/login/in/")
    Call<ResultMessage> login(@Query("login") String info);

    @POST("Personapp_api")
    Call<ResultMessage> createUser(@Path("info") String info);

    @GET("/login/out/")
    Call<ResultMessage> logout(@Query("logout") String info);


}
