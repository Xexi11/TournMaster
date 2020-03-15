package cat.udl.tidic.amb.tournmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cat.udl.tidic.amb.tournmaster.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    User us = new User();
    EditText tokenEditText;
    Button checkButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // elements del layout:
        //----------------------
        //tokenEditText = findViewById(R.id.editText_token);
        //checkButton = findViewById(R.id.button_check);
        //resultTextView = findViewById(R.id.textView_result);
        UserService userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserService.class);

        //Instancia per crear usuari en la base de dades
        User user = new User();
        user.setUsername("mane9mb");
        user.setName("Manel");
        user.setSurname("Moreno");
        user.setPassword("170719");
        user.setGenere("M");
        user.setEmail("Manel_9mb@outlook.com");
        Call<Void> call_post = userService.createUser(user);
        call_post.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                Log.d("MainActivity",String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t.getMessage().toString());
                Log.d("MainActivity",t.getMessage());
            }
        });

    }
}
