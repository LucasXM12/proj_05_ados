package com.ados.cotuca.proj_05_ados;

import android.os.*;
import android.app.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import android.content.*;
import android.support.v7.app.*;
import android.support.v7.app.AlertDialog;

import com.ados.cotuca.proj_05_ados.tools.*;

public class MainActivity extends AppCompatActivity {

    private final String URL_SRVR = "http://177.220.18.60:9123/api/Disciplina";

    private Utils util;

    private int cod = -1;
    private String nome = "";
    private Disciplina disciplina;

    private TextView edtCod;
    private TextView edtNome;
    private ProgressDialog load;

    private GetJson download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.util = new Utils();
        this.edtCod = (TextView) findViewById(R.id.edtCod);
        this.edtNome = (TextView) findViewById(R.id.edtNome);

        Button btnIr = (Button) findViewById(R.id.btnIr);
        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chama Async Task
                download = new GetJson();
                download.execute();
            }
        });

        this.edtCod.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String codS = s.toString();

                if (!codS.isEmpty())
                    cod = new Integer(codS);
                else
                    cod = -1;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.edtNome.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nome = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private class GetJson extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(MainActivity.this, "Por favor aguarde ...", "Recuperando informações do servidor...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String urlRequest = "";

                if (cod > 0)
                    urlRequest = String.format(URL_SRVR + "?codigo=%d", cod);
                else if (!nome.isEmpty())
                    urlRequest = String.format(URL_SRVR + "?nome=%s", nome);
                else
                    return null;

                disciplina = util.getInformacao(urlRequest);
            } catch (Exception e) {
                disciplina = null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void params) {
            if (disciplina == null) {
                AlertDialog.Builder builder;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                else
                    builder = new AlertDialog.Builder(MainActivity.this);


                builder.setTitle("Erro")
                        .setMessage("Não foi possível recuperar as informações do servidor")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.stat_notify_error)
                        .show();
            } else {
                edtCod.setText(disciplina.getCod().toString());
                edtNome.setText(disciplina.getNome());
            }

            load.dismiss();
        }
    }
}
