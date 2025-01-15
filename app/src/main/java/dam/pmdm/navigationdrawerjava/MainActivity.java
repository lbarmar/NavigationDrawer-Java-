package dam.pmdm.navigationdrawerjava;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import dam.pmdm.navigationdrawerjava.databinding.ActivityMainBinding;
import dam.pmdm.navigationdrawerjava.databinding.GuideBinding;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
    private GuideBinding guideBinding;
    private NavController navController;

    private Boolean needToStartGuide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        guideBinding = binding.includeLayout;
        setContentView(binding.getRoot());

        // Obtener el NavController desde el NavHostFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Configurar menú toggle
        configureToggleMenu();

        // Configurar la navegación
        configureNavigation();

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeGuide();
    }

    private void initializeGuide() {
        guideBinding.exitGuide.setOnClickListener(this::onExitGuide);

        if (needToStartGuide){
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            guideBinding.guideLayout.setVisibility(View.VISIBLE);

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(
                    guideBinding.pulseImage, "scaleX", 1f, 0.5f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(
                    guideBinding.pulseImage, "scaleY", 1f, 0.5f);
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(
                    guideBinding.textStep, "alpha", 0f, 1f);

            scaleX.setRepeatCount(3);
            scaleY.setRepeatCount(3);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(scaleX).with(scaleY).before(fadeIn);
            animatorSet.setDuration(1000);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (needToStartGuide) {
                        super.onAnimationEnd(animation);
                        binding.drawerLayout.open();
                        guideBinding.pulseImage.setVisibility(View.GONE);
                        guideBinding.textStep.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    private void onExitGuide(View view) {
        needToStartGuide = false;
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        guideBinding.guideLayout.setVisibility(View.GONE);
        binding.drawerLayout.close();
    }

    private void configureToggleMenu() {
        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.toolbar,
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
            else if (menuItem.getItemId() == R.id.nav_paint) {
                navController.navigate(R.id.paintFragment); // Navegar al fragmento car
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
