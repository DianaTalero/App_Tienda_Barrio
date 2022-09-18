package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "ciclo";
    private EditText cedulaCliente;
    private EditText nombreCliente;
    private EditText dirCliente;
    private EditText telCliente;
    private EditText celCliente;
    private EditText correoCliente;
    private Button btnRegistrar;
    private boolean lenUser, lenPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate 1");
        setContentView(R.layout.activity_menu);
        setup();
    }

    private void setup() {
        cedulaCliente = findViewById(R.id.cedulaCliente);
        nombreCliente = findViewById(R.id.nombreCliente);
        dirCliente = findViewById(R.id.dirCliente);
        telCliente = findViewById(R.id.telCliente);
        celCliente = findViewById(R.id.celCliente);
        correoCliente = findViewById(R.id.correoCliente);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        cedulaCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (cedulaCliente.getText().toString().length()<3) {
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar su numero de cedula",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnRegistrar.setEnabled(true);
                        }
                    }
                }
            }
        });
        nombreCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (nombreCliente.getText().toString().length()<3) {
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar su nombre y apellido",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnRegistrar.setEnabled(true);
                        }
                    }
                }
            }
        });

        dirCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (dirCliente.getText().toString().length()<3) {
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar su dirección de residencia",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnRegistrar.setEnabled(true);
                        }
                    }
                }
            }
        });

        telCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (telCliente.getText().toString().length()<3) {
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar su numero de telefono",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnRegistrar.setEnabled(true);
                        }
                    }
                }
            }
        });

        celCliente.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lenUser=false;
                    if (celCliente.getText().toString().length()<3) {
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar su numero de celular",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenUser=true;
                        if (lenPwd) {
                            btnRegistrar.setEnabled(true);
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
                        Toast.makeText(MenuActivity.this,
                                "Debe ingresar el correo electronico",
                                Toast.LENGTH_SHORT).show();
                        btnRegistrar.setEnabled(false);
                    } else {
                        lenPwd=true;
                        if (lenUser) {
                            btnRegistrar.setEnabled(true);
                        }
                    }
                }
            }
        });

    }

    public void Registro(View view) {
        String user = cedulaCliente.getText().toString();
        String nom = nombreCliente.getText().toString();
        String dir = dirCliente.getText().toString();
        String tel = telCliente.getText().toString();
        String cel = celCliente.getText().toString();
        String pwd = correoCliente.getText().toString();
        if ((user!=null && user.length()>0) &&
                (nom!=null && nom.length()>0) &&
                (dir!=null && dir.length()>0) &&
                (tel!=null && tel.length()>0) &&
                (cel!=null && cel.length()>0) &&
                (pwd!=null && pwd.length()>0)) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("user", user);
            ejecutar_registro.launch(intent);
            //startActivity(intent);
            //startActivityForResult(intent, 200);
        } else {
            Toast.makeText(MenuActivity.this,
                    "Datos Incorrectos",
                    Toast.LENGTH_LONG).show();
        }
    }
    ActivityResultLauncher<Intent> ejecutar_registro = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK) {
                        cedulaCliente.setText(null);
                        nombreCliente.setText(null);
                        dirCliente.setText(null);
                        telCliente.setText(null);
                        celCliente.setText(null);
                        correoCliente.setText(null);
                    } else {
                        Toast.makeText(MenuActivity.this,
                                "Retorno a MenuActivity", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
}
