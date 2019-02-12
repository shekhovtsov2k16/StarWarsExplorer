package nikita.shekhovtsov.starwarsexplorer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import nikita.shekhovtsov.starwarsexplorer.DataBases.BeingDataReader;
import nikita.shekhovtsov.starwarsexplorer.DataBases.BeingDataSource;
import nikita.shekhovtsov.starwarsexplorer.Model.Being;
import nikita.shekhovtsov.starwarsexplorer.R;

public class BeingActivity extends AppCompatActivity {

    private TextView nameView;
    private TextView heightView;
    private TextView massView;
    private TextView hairColorView;
    private TextView skinColorView;
    private TextView eyeColorView;
    private TextView birthYearView;
    private TextView genderView;
    private Being being;
    private BeingDataReader beingDataReader;
    private BeingDataSource beingDataSource;

    private void initDataSource() {
        beingDataSource = new BeingDataSource(getApplicationContext());
        beingDataSource.open();
        beingDataReader = beingDataSource.getNoteDataReader();
        beingDataSource.addBeing(being.getName(), being.getHeight(),
                being.getMass(), being.getHairColor(),
                being.getSkinColor(), being.getEyeColor(),
                being.getBirthYear(), being.getGender());
        dataUpdated();
        try {
            beingDataSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataUpdated() {
        beingDataReader.Refresh();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_being);

        initExtras();
        initGUI();


        initDataSource();
    }

    private void initGUI() {
        nameView = findViewById(R.id.text_view_name);
        heightView = findViewById(R.id.text_view_height);
        massView = findViewById(R.id.text_view_mass);
        hairColorView = findViewById(R.id.text_view_hair_color);
        skinColorView = findViewById(R.id.text_view_skin_color);
        eyeColorView = findViewById(R.id.text_view_eye_color);
        birthYearView = findViewById(R.id.text_view_birth_date);
        genderView = findViewById(R.id.text_view_gender);
        if (being != null) {
            nameView.setText(being.getName());
            heightView.setText(being.getHeight());
            massView.setText(being.getMass());
            hairColorView.setText(being.getHairColor());
            skinColorView.setText(being.getSkinColor());
            eyeColorView.setText(being.getEyeColor());
            birthYearView.setText(being.getBirthYear());
            genderView.setText(being.getGender());
        }
    }


    private void initExtras() {
        Bundle arguments = getIntent().getExtras();
        try {
            being = (Being) arguments.getSerializable(Being.class.getSimpleName());
        } catch (NullPointerException e) {
            being = null;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BeingActivity.this, MainActivity.class));
    }
}