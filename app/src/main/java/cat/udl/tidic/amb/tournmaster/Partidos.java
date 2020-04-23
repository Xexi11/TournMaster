package cat.udl.tidic.amb.tournmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Partidos extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidos);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.Partidos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.Inicio:
                        startActivity(new Intent(getApplicationContext(),
                                Inicio.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Partidos:
                        startActivity(new Intent(getApplicationContext(),
                                Partidos.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Buscar:
                        startActivity(new Intent(getApplicationContext(),
                                Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Perfil:
                        startActivity(new Intent(getApplicationContext(),
                                Perfil.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
    public void Inico (View view){

        Intent intent = new Intent(Partidos.this,Inicio.class);
        startActivity(intent);
    }
    public void partidos (View view){

        Intent intent = new Intent(Partidos.this,Partidos.class);
        startActivity(intent);
    }
    public void search (View view){

        Intent intent = new Intent(Partidos.this,Search.class);
        startActivity(intent);
    }
    public void perfil (View view){

        Intent intent = new Intent(Partidos.this,Perfil.class);
        startActivity(intent);
    }
}
