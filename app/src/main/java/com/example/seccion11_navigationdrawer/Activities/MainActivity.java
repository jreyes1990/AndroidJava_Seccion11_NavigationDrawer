package com.example.seccion11_navigationdrawer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.seccion11_navigationdrawer.Fragments.AlertsFragment;
import com.example.seccion11_navigationdrawer.Fragments.EmailFragment;
import com.example.seccion11_navigationdrawer.Fragments.InfoFragment;
import com.example.seccion11_navigationdrawer.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
  private DrawerLayout drawerLayout;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setToolbar();

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView = (NavigationView) findViewById(R.id.navView);

    setFragmentByDefault();

    drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
      @Override
      public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

      }

      @Override
      public void onDrawerOpened(@NonNull View drawerView) {
        Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onDrawerClosed(@NonNull View drawerView) {
        Toast.makeText(MainActivity.this, "Close", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onDrawerStateChanged(int newState) {

      }
    });

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (item.getItemId()){
          case  R.id.menu_mail:
            fragment = new EmailFragment();
            fragmentTransaction = true;
            break;
          case  R.id.menu_alert:
            fragment = new AlertsFragment();
            fragmentTransaction = true;
            break;
          case  R.id.menu_info:
            fragment = new InfoFragment();
            fragmentTransaction = true;
            break;
          case R.id.menu_opcion_1:
            Toast.makeText(MainActivity.this, "Has clickeado en la opcion 1", Toast.LENGTH_SHORT).show();
            break;
        }

        if (fragmentTransaction){
          changeFragment(fragment, item);
          drawerLayout.closeDrawers();
        }

        return true;
      }
    });
  }

  private void setToolbar(){
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar); // Cuando usas una barra de la app perteneciente a un fragmento, Google recomienda que uses directamente las APIs de Toolbar. No uses setSupportActionBar() ni las APIs de menú de Fragment, que son adecuadas solo para las barras de la app pertenecientes a una actividad.
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setFragmentByDefault(){
    changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
  }

  private void changeFragment(Fragment fragment, MenuItem item){
    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    item.setChecked(true);
    getSupportActionBar().setTitle(item.getTitle());
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