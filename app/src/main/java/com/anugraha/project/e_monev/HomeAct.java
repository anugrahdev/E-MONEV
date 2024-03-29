package com.anugraha.project.e_monev;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.anugraha.project.e_monev.model.Data;

import java.util.ArrayList;

public class HomeAct extends AppCompatActivity {
    private ArrayList<Data> dataList;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    EditText et_tahun;
    Button btn_tampilkan;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        Toolbar toolbar = findViewById(R.id.toolbar); //Inisialisasi dan Implementasi id Toolbar
        sharedPrefManager = new SharedPrefManager(this);
        setSupportActionBar(toolbar); // Memasang Toolbar pada Aplikasi
        getSupportActionBar().setTitle("E-MONEV"); // Memasang Judul pada Toolbar
        //Menampilkan halaman Fragment yang pertama kali muncul
        getFragmentPage(new Home());

        /*Inisialisasi BottomNavigationView beserta listenernya untuk
         *menangkap setiap kejadian saat salah satu menu item diklik
         */
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new Home();
                        break;

                    case R.id.comment:
                        fragment = new Comment();
                        break;

                    case R.id.user:
                        fragment = new User();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });




    }
    //Code Program pada Method dibawah ini akan Berjalan saat Option Menu Dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Memanggil/Memasang menu item pada toolbar dari layout menu_bar.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Membuat event/kejadian saat salah satu item pada toolbar di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about :
                //Kode disini akan di eksekusi saat tombol about di klik
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();

                break;
            case R.id.logout :
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(HomeAct.this, LoginAct.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
