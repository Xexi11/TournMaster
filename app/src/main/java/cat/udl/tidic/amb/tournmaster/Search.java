package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intent = getIntent();

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
}
