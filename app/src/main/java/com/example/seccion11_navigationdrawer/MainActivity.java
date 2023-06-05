package com.example.seccion11_navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
  private DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setToolbar();

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
  }

  private void setToolbar(){
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar); // Cuando usas una barra de la app perteneciente a un fragmento, Google recomienda que uses directamente las APIs de Toolbar. No uses setSupportActionBar() ni las APIs de menú de Fragment, que son adecuadas solo para las barras de la app pertenecientes a una actividad.
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case android.R.id.home:
        // Abrir el menu lateral
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}