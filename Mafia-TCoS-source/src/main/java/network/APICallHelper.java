package network;
/**
 * Created by SKYLIFE on 01/05/2017.
 */
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APICallHelper {

    private static Retrofit retrofit;

    private static final String DEV_URL = "https://mafia-tcos.herokuapp.com/api/";

    private static final String API_URL = "https://SECRET_URL-PROD-ENV";


    static {
        setupRestClient();
    }

    public static Retrofit getInstance() {

        return retrofit;
    }

    private static void setupRestClient() {

        String restApiURL = DEV_URL;


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
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
