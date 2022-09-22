package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uncc.hw04.databinding.ActivityMainBinding;
import edu.uncc.hw04.fragments.BACFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new BACFragment())
                .commit();
    }
}