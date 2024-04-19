package com.example.calculadoraimcjava;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculadoraimcjava.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        binding.btCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String peso = binding.EditTextPeso.getText().toString().replace(",", ".");
                String altura = binding.EditTextAltura.getText().toString().replace(",", ".");
                TextView resultado = binding.textResultado;

                if (peso.isEmpty()){

                    binding.EditTextPeso.setError("Informe seu Peso!");

                }else if(altura.isEmpty()){

                    binding.EditTextAltura.setError("Informe sua Altura!");

                }else{

                    calcularIMC(peso, altura, resultado);

                }

            }
        });


    }

private void calcularIMC(String peso, String altura, TextView resultado){

        float pesoConversao = Float.parseFloat(peso);
        float alturaConversao = Float.parseFloat(altura);
        float imc = pesoConversao / (alturaConversao * alturaConversao);

        String imcConversao = String.format("%.2f",imc);


        if (imc < 18.5){

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Desnutrido");
            resultado.setTextColor(getColor(R.color.black));

        }else if (imc <= 24.9){

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Normal");
            resultado.setTextColor(getColor(R.color.blue));

        }else if (imc <= 29.9){

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Sobrepeso");
            resultado.setTextColor(getColor(R.color.green));

        }else if (imc <= 34.9){

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Obesidade (grau 1)");
            resultado.setTextColor(getColor(R.color.orange));

        }else if (imc <= 39.9) {

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Obesidade Severa (grau 2)");
            resultado.setTextColor(getColor(R.color.red));

        }else{

            resultado.setText("Seu IMC é de " + imcConversao + "\n" + "Obesidade Mórbida (grau 3)");
            resultado.setTextColor(getColor(R.color.red));

        }


}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.ic_limpar){
            binding.EditTextAltura.setText("");
            binding.EditTextPeso.setText("");
            binding.textResultado.setText("");
        }

        return super.onOptionsItemSelected(item);
    }


}




