package dam.pmdm.navigationdrawerjava;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import dam.pmdm.navigationdrawerjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtener el NavController desde el NavHostFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar el icono del menú en la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.homeFragment); // Navegar al fragmento de inicio
            }
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;
        });

        // Maneja la opción de perfil del header del menú
        ImageView profileImageView = binding.navView.getHeaderView(0).findViewById(R.id.header_image);

        profileImageView.setOnClickListener(v -> {
            navController.navigate(R.id.profileFragment); // Navegar al fragmento de perfil
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar clics en el icono del menú
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = NavHostFragment.findNavController(navHostFragment);
            return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

}
