package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {
    private TextView user;
    private EditText mail;
    private EditText rol;
    private EditText sex;
    private EditText name;
    private EditText surname;
    private EditText birthday;
    private EditText position;
    private EditText prefsmash;
    private EditText timeplay;
    private EditText matchaname;
    private EditText club;
    private EditText license;
    private Button iniciar;


    private UserService userService;
    private SharedPreferences mPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        user = findViewById(R.id.text_users);
        mail = findViewById(R.id.text_mail);
        rol = findViewById(R.id.text_rol);
        sex = findViewById(R.id.text_sexo);
        name = findViewById(R.id.text_name);
        surname = findViewById(R.id.text_surname);
        birthday = findViewById(R.id.text_birthday);
        position = findViewById(R.id.text_position);
        prefsmash = findViewById(R.id.text_prefsmash);
        timeplay = findViewById(R.id.text_timeplay);
        matchaname = findViewById(R.id.text_matchname);
        club = findViewById(R.id.text_club);
        license = findViewById(R.id.text_license);
        iniciar = findViewById(R.id.inciarButton);

        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");


        Call<JsonObject> call_get = userService.getUserProfile(token);
        call_get.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("ERROR",response.code()+"");
                if(response.code()==200){
                    //response.body();
                    JsonObject userJson = response.body();
                    String nom_user = userJson.get("username").toString();
                    user.setText(atributs(nom_user));

                    String user_mail = userJson.get("email").toString();
                    mail.setText(atributs(user_mail));
                    String user_sex= userJson.get("genere").toString();
                    user_sex = user_sex.substring(1,user_sex.length()-1);
                    Log.d("TAG",user_sex);

                    if(user_sex.equals("M")){
                        Log.d("TAG","ENTRA");
                        sex.setText("Hombre");
                    }
                    else{
                        sex.setText("Mujer");
                    }
                    Log.d("TAG",user_sex);
                    String user_rol = userJson.get("rol").toString();
                    Log.d("rol",user_rol);
                    user_rol=user_rol.substring(1,user_rol.length()-1);
                    if(user_rol.equals("O")){

                        rol.setText("Organizador");
                    }

                    else{
                        rol.setText("Jugador");
                    }
                    String name_user = userJson.get("name").toString();
                    if(name_user.equals("")) {
                        name.setText(atributs(nom_user));
                    }
                    String surname_user = userJson.get("surname").toString();
                    if(surname_user.equals("")) {
                        surname.setText(atributs(nom_user));
                    }
                    String birthday_user = userJson.get("birthdate").toString();
                    if(birthday_user.equals("")) {
                        birthday.setText(atributs(nom_user));
                    }
                    String position_user = userJson.get("position").toString();
                    if(position_user.equals("")) {
                        position.setText(atributs(nom_user));
                    }
                    String prefsmash_user = userJson.get("prefsmash").toString();
                    if(prefsmash_user.equals("")) {
                        prefsmash.setText(atributs(nom_user));
                    }
                    String timeplay_user = userJson.get("timeplay").toString();
                    if(timeplay_user.equals("")) {
                        timeplay.setText(atributs(nom_user));
                    }
                    String matchname_user = userJson.get("matchname").toString();
                    if(matchname_user.equals("")) {
                        matchaname.setText(atributs(nom_user));
                    }
                    String club_user = userJson.get("club").toString();
                    if(club_user.equals("")) {
                        club.setText(atributs(nom_user));
                    }
                    String license_user = userJson.get("license").toString();
                    if(license_user.equals("")) {
                        license.setText(atributs(nom_user));
                    }


                }
                else{
                    //missatge de error;
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG",t.getMessage().toString());
            }
        });

    }
    public String atributs(String n){

        n = n.substring(1,n.length()-1);

        return n;

    }

    public void cerrarSession(View view){

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");


        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        Call<ResponseBody> call_delete= userService.deleteToken(token);
        call_delete.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                    Log.d("FUNC","entras?");

                        mPreferences.edit().putString("token", "").apply();
                        Intent intent = new Intent(Perfil.this, Login.class);
                        startActivity(intent);
                }



            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void Inico (View view){

        Intent intent = new Intent(Perfil.this,Inicio.class);
        startActivity(intent);
    }
    public void partidos (View view){

        Intent intent = new Intent(Perfil.this,Partidos.class);
        startActivity(intent);
    }
    public void search (View view){

        Intent intent = new Intent(Perfil.this,Search.class);
        startActivity(intent);
    }
    public void perfil (View view){

        Intent intent = new Intent(Perfil.this,Perfil.class);
        startActivity(intent);
    }
    public void editar(View view){
        mail.setEnabled(true);
        sex.setEnabled(true);
        rol.setEnabled(true);
        name.setEnabled(true);
        surname.setEnabled(true);
        birthday.setEnabled(true);
        position.setEnabled(true);
        prefsmash.setEnabled(true);
        timeplay.setEnabled(true);
        matchaname.setEnabled(true);
        club.setEnabled(true);
        license.setEnabled(true);
    }


}