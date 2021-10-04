package com.example.psychometrictest.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.psychometrictest.TEST.LeaderFragment;
import com.example.psychometrictest.R;
import com.example.psychometrictest.TEST.SkillTestsFragment;
import com.example.psychometrictest.TEST.UserAccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout mainframe;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_home:
                    setFragement(new SkillTestsFragment());
                    return true;
                case R.id.nav_leaderboard:
                    setFragement(new LeaderFragment());
                    return true;
                case R.id.nav_account:
                    setFragement(new UserAccountFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        mainframe=findViewById(R.id.main_frame);
       bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
       setFragement(new SkillTestsFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void setFragement(Fragment fragement){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame,fragement);
        transaction.commit();
    }
}


