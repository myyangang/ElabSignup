package com.elab.elabsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elab.elabsignup.event.SignupEvent;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.EventListener;
import java.util.List;

public class LogViewActivity extends AppCompatActivity {

    protected Fragment createFragment(){
        return LogViewFragment.newInstance();
    }

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_view);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.log_view_container);
        if (fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.log_view_container,fragment)
                    .commit();
        }
    }

}