package com.altrovis.jakhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.altrovis.jakhub.Business.AddPerpanjangKTPAsyncTask;
import com.altrovis.jakhub.Entities.GlobalVariable;


public class ActivityHome extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("");

            actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                    | ActionBar.DISPLAY_SHOW_CUSTOM);
            ImageView imageView = new ImageView(actionBar.getThemedContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageResource(R.drawable.icon);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                    | Gravity.CENTER_VERTICAL);
            layoutParams.rightMargin = 40;
            imageView.requestLayout();
            imageView.setLayoutParams(layoutParams);
            actionBar.setCustomView(imageView);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new FragmentLayanan();
            } else if (position == 1) {
                return new FragmentInformasi();
            } else if (position == 2) {
                return new FragmentNotifikasi();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0) {
                return "Layanan";
            } else if (position == 1) {
                return "Informasi";
            } else if (position == 2) {
                return "Notifikasi";
            }

            return null;
        }
    }

    public void backToMenu(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //kosongkan fragment navigation backstack
        while (fragmentManager.popBackStackImmediate()) {
        }
        //apabila fragment navigation backstack sudah kosong maka munculkan tampilan utama
        RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.RelativeLayoutGridViewMenu);
        mainContainer.setVisibility(RelativeLayout.VISIBLE);
    }

    public void backToMenuNotifikasi(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //kosongkan fragment navigation backstack
        while (fragmentManager.popBackStackImmediate()) {
        }
        //apabila fragment navigation backstack sudah kosong maka munculkan tampilan utama
        RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.RelativeLayoutListViewNotifikasi);
        mainContainer.setVisibility(RelativeLayout.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_home, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }

        //supaya tidak terjadi bug container fragment menjadi blank
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            //apabila fragment navigation backstack sudah kosong maka munculkan tampilan utama
            RelativeLayout mainContainer = (RelativeLayout) findViewById(R.id.RelativeLayoutGridViewMenu);
            mainContainer.setVisibility(RelativeLayout.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuKeluar) {
            Intent intent = new Intent(ActivityHome.this, ActivityMain.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddPerpanjangKTPtoWebService(View ev) {

        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        String nik = login.getString("nik", "");
        String tanggal = GlobalVariable.tanggalPelayanan;

        new AddPerpanjangKTPAsyncTask(this, nik, tanggal).execute();

    }
}
