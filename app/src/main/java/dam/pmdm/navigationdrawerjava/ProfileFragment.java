package dam.pmdm.navigationdrawerjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import dam.pmdm.navigationdrawerjava.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding; // No es necesario lateinit en Java

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inicializar binding
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        // Retornar la vista raíz
        return binding.getRoot();
    }
}
