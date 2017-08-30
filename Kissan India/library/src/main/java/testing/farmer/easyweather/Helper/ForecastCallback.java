package testing.farmer.easyweather.Helper;

import testing.farmer.easyweather.retrofit.models.ForecastResponseModel;

/**
 * Created by Ankit Kumar on 18-07-2016.
 */
public abstract class ForecastCallback {

    public abstract void success(ForecastResponseModel response);

    public abstract void failure(String message);
}
