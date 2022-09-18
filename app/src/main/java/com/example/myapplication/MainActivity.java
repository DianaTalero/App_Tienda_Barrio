package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ciclo";
    private EditText cedulaCliente;
    private EditText correoCliente;
    private Button btnVerificar;
    private boolean lenUser, lenPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate 1");
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        cedulaCliente = findViewById(R.id.cedulaCliente);
        correoCliente = findViewById(R.id.correoCliente);
        btnVerificar = findViewById(R.id.btnVerificar);

        cedulaCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (cedulaCliente.getText().toString().length()<3) {
                        Toast.makeText(MainActivity.this,
                                "Debe ingresar su numero de cedula",
                                Toast.LENGTH_SHORT).show();
                        btnVerificar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnVerificar.setEnabled(true);
                        }
                    }
                }
            }
        });

        correoCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenPwd=false;
                    if (correoCliente.getText().toString().length()<3) {
                        Toast.makeText(MainActivity.this,
                                "Debe ingresar el correo electronico",
                                Toast.LENGTH_SHORT).show();
                        btnVerificar.setEnabled(false);
                    } else {
                        lenPwd=true;
                        if (lenUser) {
                            btnVerificar.setEnabled(true);
                        }
                    }
                }
            }
        });

    }

    public void login(View view) {
        String user = cedulaCliente.getText().toString();
        String pwd = correoCliente.getText().toString();
        if ((user!=null && user.length()>0) &&
                (pwd!=null && pwd.length()>0)) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("user", user);
            ejecutar_login.launch(intent);
            //startActivity(intent);
            //startActivityForResult(intent, 200);
        } else {
            Toast.makeText(MainActivity.this,
                    "Datos Incorrectos",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop 5");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy 7");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause 4");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume 3");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart 6");
    }

    ActivityResultLauncher<Intent> ejecutar_login = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK) {
                        cedulaCliente.setText(null);
                        correoCliente.setText(null);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Retorno a MainActivity", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}