package dam.pmdm.navigationdrawerjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.pmdm.navigationdrawerjava.databinding.FragmentPaintBinding;
import dam.pmdm.navigationdrawerjava.databinding.FragmentProfileBinding;

public class PaintFragment extends Fragment {

    FragmentPaintBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaintBinding.inflate(inflater, container, false);


        // Retornar la vista raíz
        return binding.getRoot();
    }
}