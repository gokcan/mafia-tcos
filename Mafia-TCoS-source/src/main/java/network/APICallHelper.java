/**
 * Created by SKYLIFE on 25/04/2017.
 */
package network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class APICallHelper {

    private static Retrofit retrofit;

    private static final String DEV_URL = "https://SECRET-URL-DEV-ENV";
    private static final String API_URL = "https://SECRET_URL-PROD-ENV";

    static {
        setupRestClient();
    }

    public static Retrofit getInstance() {

        return retrofit;
    }

    private static void setupRestClient() {

        String restApiURL = DEV_URL;

        if (BuildConfig.BUILD_TYPE.startsWith("release")
                || BuildConfig.BUILD_TYPE.startsWith("live")
                || BuildConfig.BUILD_TYPE.startsWith("prod")

                ) {
            restApiURL = API_URL;
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(restApiURL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}


