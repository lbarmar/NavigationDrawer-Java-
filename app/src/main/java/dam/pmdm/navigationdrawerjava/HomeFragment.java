package dam.pmdm.navigationdrawerjava;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import dam.pmdm.navigationdrawerjava.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding; // No es necesario lateinit en Java, solo declaración

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inicializar binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(this::OnClickButton);

        // Retornar la vista raíz
        return binding.getRoot();
    }

    private void OnClickButton(View view) {
//        // Usar SoundPool (comentado como en Kotlin)
//         SoundPool soundPool = new SoundPool.Builder().setMaxStreams(1).build();
//         int soundId = soundPool.load(requireContext(), R.raw.pulse, 1);
//
//         soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//             @Override
//             public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                 if (status == 0) {  // Carga exitosa
//                     soundPool.play(soundId, 1f, 1f, 0, 0, 1f);
//                 }
//             }
//         });

//        Usar MediaPlayer
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.pulse);
        mediaPlayer.start();

//        Establecer el listener para cuando la reproducción termine
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Liberar el MediaPlayer cuando la reproducción termine
                mp.release();
            }
        });
    }
}
