package com.example.restclientservweb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReportActivity extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText dateE = findViewById(R.id.editText1);
        EditText usernameE = findViewById(R.id.editText2);
        EditText descriptionE = findViewById(R.id.editText3);
        Button denunciar = findViewById(R.id.button_denunciar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/") // Cambiado a 10.0.2.2 para el emulador
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        denunciar.setOnClickListener(v -> {

            String date = dateE.getText().toString();
            String user = usernameE.getText().toString();
            String description = descriptionE.getText().toString();
            if (!date.isEmpty() && !user.isEmpty() && !description.isEmpty()) {
                Denuncia issue = new Denuncia(date, user, description);
                PostIssue(issue);
            } else {
                Toast.makeText(ReportActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void PostIssue(Denuncia issue) {
        Call<Denuncia> call = apiService.PostIssue(issue);

        call.enqueue(new Callback<Denuncia>() {
            @Override
            public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {
                // Manejar la respuesta exitosa aquí
                Toast.makeText(ReportActivity.this, "Denuncia enviada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Denuncia> call, Throwable t) {
                Toast.makeText(ReportActivity.this, "Error", Toast.LENGTH_SHORT).show();
                // Manejar el error aquí
            }
        });
    }

}