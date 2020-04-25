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

        // Accounts
        /*
         * @JordiMateoUdl: Heu de diferenciar el registre de la operació d'actualitzar el perfil!!!
         */

        @GET("/account/profile")
        Call<JsonObject> getUserProfile(@Header("Authorization") String auth_token);

        @PUT("/account/update_profile")
        Call<Void> updateAccount(@Header("Authorization") String auth_token, @Body JsonObject userJson);

        @POST("/account/profile/update_profile_image")
        Call<ResponseBody>updateImage(@Header("Authorization") String auth_token);

        @POST("/account/create_token")
        Call<ResponseBody>createToken(@Header("Authorization") String auth_token);

        @POST("account/delete_token")
        Call<ResponseBody>deleteToken(@Header("Authorization") String auth_token);

        // Users

        @GET("/users")
        Call<List<User>> getUsers(@Header("Authorization") String auth_token);

        @GET("/users/show/{username}")
        Call<List<User>> getPerfilPublico(@Header("Authorization") String auth_token,String username);

        @POST("/users/register")
        Call<Void> createUser(@Body JsonObject userJson);


}
