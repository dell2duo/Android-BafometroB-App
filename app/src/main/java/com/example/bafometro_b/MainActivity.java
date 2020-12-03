package com.example.bafometro_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateAndSend(View v) {
        Intent newIt = new Intent();
        Intent it = getIntent();

        Float peso = it.getFloatExtra("peso", 0);
        String sexo = it.getStringExtra("sexo");
        Integer numCopos = it.getIntExtra("numCopos", 0);
        String jejum = it.getStringExtra("jejum");

        Float coeficiente;
        if(jejum.equals("s")){
            coeficiente = 1.1f;
        } else if(sexo.equals("m")){
            coeficiente = 0.7f;
        } else {
            coeficiente = 0.6f;
        }

        Float taxaAlcool = (numCopos*(4.8f)) / (peso*coeficiente);

        String classificacao;

        if(taxaAlcool > 0.2) {
            classificacao = "Pessoa Alcoolizada";
        } else {
            classificacao = "Pessoa não Alcoolizada";
        }

        newIt.putExtra("taxaAlcool", taxaAlcool);
        newIt.putExtra("classificacao", classificacao);

        setResult(1, newIt);
        finish();
    }
}