package cat.udl.tidic.amb.tournmaster.services;

import com.google.gson.JsonObject;

import java.util.Map;

import cat.udl.tidic.amb.tournmaster.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    //metodes per fer les instancies a la base de dades tan como get i post!:

        //@Headers("Authorization:656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf")
        @GET("/account/profile")
        Call<User> getUserProfile(@HeaderMap Map<String, String> headers);

        @Headers({"Content-type:application/json"})
        @POST("/users/register")
        Call<Void> createUser2(@Body JsonObject userJson);

        @Headers({"Content-type:application/json"})
        @POST("/account/create_token")
        Call<ResponseBody>createToken(@Header("Authorization") String auth_token);



}
