package br.com.creative.internacionalizacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLanguage(readLanguagePreference());
        setContentView(R.layout.activity_main);
    }

    public void onSelectLanguage(View view){
        String languageCode = null;
        switch (view.getId()){
            case R.id.btnSpain:
                languageCode = getString(R.string.spanish_code);
                break;
            case R.id.btnBrazil:
                languageCode = getString(R.string.portuguese_code);
                break;
            case R.id.btnUsa:
                languageCode = getString(R.string.english_code);
                break;
            default:
                languageCode = getDefaultLanguageCode();
        }

        setLanguage(languageCode);
        saveLanguagePreference(languageCode);
        recreate();
    }

    private  void setLanguage(String languageCode){
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();

        Configuration conf = res.getConfiguration();
        conf.setLocale(locale);

        res.updateConfiguration(conf, dm);
    }

    private void saveLanguagePreference(String languageCode){
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_preference_key),Context.MODE_PRIVATE).edit();
        editor.putString(getString(R.string.shared_preference_language_key), languageCode);
        editor.apply();
    }

    private String readLanguagePreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preference_key),Context.MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.shared_preference_language_key),getDefaultLanguageCode());
    }

    private String getDefaultLanguageCode(){
        return Locale.getDefault().getLanguage();
    }
}