package id.kido1611.clickboot;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.kido1611.clickboot.fragment.FragmentMain;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new FragmentMain()).commit();
    }

    public void showSnackbar(String message){
        showSnackbar(message, Snackbar.LENGTH_LONG);
    }

    public void showSnackbar(String message, int length){
        Snackbar.make(mCoordinatorLayout, message, length).show();
    }
}
