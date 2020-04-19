package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amb.tournmaster.preferences.PreferencesProvider;
import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {
    private Intent intent;
    private ListView listview;
    private ArrayList<String> names;
    private UserService userService;
    private SharedPreferences mPreferences;
    private String token;
    private String searching;
    private ArrayAdapter adaptador;
    private String prueba[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intent = getIntent();
        listview = findViewById(R.id.listView);


        searching="";

        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");
        System.out.println(token);
        Call<List<JsonObject>> call_get = userService.getUsers(token);

        call_get.enqueue(new Callback<List<JsonObject>>() {
            @Override
            public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {

                if(response.code()==200){

                    List<JsonObject> userJson = response.body();
                    String []taulausers = new String[userJson.size()];
                    prueba = new String[userJson.size()];
                    int cont=0;


                    for(int i =0; i< userJson.size();i++){
                        cont++;
                        String namess = getUsername(userJson.get(i));
                        taulausers[i]=namess;
                    }

                        prrueba(taulausers);



                    System.out.println(taulausers[1]);

                }
            }

            @Override
            public void onFailure(Call<List<JsonObject>> call, Throwable t) {

            }
        });



    }
    public void Inico (View view){

        Intent intent = new Intent(Search.this,Inicio.class);
        startActivity(intent);
    }
    public void partidos (View view){

        Intent intent = new Intent(Search.this,Partidos.class);
        startActivity(intent);
    }
    public void search (View view){

        Intent intent = new Intent(Search.this,Search.class);
        startActivity(intent);
    }
    public void perfil (View view){

        Intent intent = new Intent(Search.this,Perfil.class);
        startActivity(intent);
    }



    public String getUsername(JsonObject user){

        String name = user.get("username").toString();
        name = atributs(name);

        for(int i=0;i<prueba.length;i++){
            prueba[i]=name;
        }
        System.out.println(prueba[0]);

        return name;

    }
    public void prrueba(String[] pep){


        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pep));



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    public String atributs(String n){

        n = n.substring(1,n.length()-1);
        
        return n;

    }
}
