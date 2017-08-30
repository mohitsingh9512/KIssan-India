package testing.farmer.easyweather.retrofit.api;

import testing.farmer.easyweather.retrofit.models.ForecastResponseModel;
import testing.farmer.easyweather.retrofit.models.WeatherResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ankit Kumar on 18-07-2016.
 */

public interface WeatherInterface {

    @GET("weather")
    Call<WeatherResponseModel> getCityWeather(@Query("appid") String appid,
                                              @Query("q") String city);

    @GET("weather")
    Call<WeatherResponseModel> getLocationWeather(@Query("appid") String appid,
                                                  @Query("lat") String latitude,
                                                  @Query("lon") String longitude);

    @GET("forecast")
    Call<ForecastResponseModel> getCityForcast(@Query("appid") String appid,
                                               @Query("q") String city);

    @GET("forecast")
    Call<ForecastResponseModel> getLocationForecast(@Query("appid") String appid,
                                                    @Query("lat") String latitude,
                                                    @Query("lon") String longitude);

}
