package cat.udl.tidic.amb.tournmaster.services;

import com.google.gson.JsonObject;

import java.util.List;

import cat.udl.tidic.amb.tournmaster.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    //metodes per fer les instancies a la base de dades tan como get i post!:

        //@Headers("Authorization:656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf")
        @GET("/account/profile")
        Call<JsonObject> getUserProfile(@Header("Authorization") String auth_token);

        @GET("/users")
        Call<List<User>> getUsers(@Header("Authorization") String auth_token);

        @GET("/users/show/{username}")
        Call<List<User>> getPerfilPublico(@Header("Authorization") String auth_token,String username);


        @Headers({"Content-type:application/json"})
        @POST("/users/register")
        Call<Void> createUser(@Body JsonObject userJson);

        @Headers({"Content-type:application/json"})
        @PUT("/users/register")
        Call<Void>updateUser(@Body JsonObject userJson);

        @Headers({"Content-type:application/json"})
        @POST("/account/profile/update_profile_image")
        Call<ResponseBody>updateImage(@Header("Authorization") String auth_token);

        @Headers({"Content-type:application/json"})
        @POST("/account/create_token")
        Call<ResponseBody>createToken(@Header("Authorization") String auth_token);
        @Headers({"Content-type:application/json"})

        @DELETE("account/delete_token")
        Call<ResponseBody>deleteToken(@Header("Authorization") String auth_token);




}
