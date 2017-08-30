package testing.farmer.easyweather.Helper;

import testing.farmer.easyweather.retrofit.models.WeatherResponseModel;

/**
 * Created by Ankit Kumar on 18-07-2016.
 */
public abstract class WeatherCallback {

    public abstract void success(WeatherResponseModel response);

    public abstract void failure(String message);
}
