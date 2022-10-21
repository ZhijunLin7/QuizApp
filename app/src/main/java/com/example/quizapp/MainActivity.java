package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boton_verdadero;
    private Button boton_falso;
    private ImageButton boton_anterior;
    private ImageButton boton_siguiente;
    private TextView text_pregunta;


    private Preguntas [] preguntas = new Preguntas[]{
            new Preguntas(R.string.Preguntas1,false),
            new Preguntas(R.string.Preguntas2,true),
            new Preguntas(R.string.Preguntas3,true),
            new Preguntas(R.string.Preguntas4,true),
            new Preguntas(R.string.Preguntas5,true),
            new Preguntas(R.string.Preguntas6,false),
            new Preguntas(R.string.Preguntas7,false)

    };

    private int num_preguntas= 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            boolean [] contestado = savedInstanceState.getBooleanArray("contestado");
            for (int i = 0; i < preguntas.length; i++) {
                preguntas[i].setContestado(contestado[i]);
            }

            num_preguntas= savedInstanceState.getInt("num_preguntas");
        }




        //Poner la primera palabra
        text_pregunta =(TextView) findViewById(R.id.pregunta);
        actualizar_pregunta();


        //Boton falso, verdadero, siguiente, anterior
        boton_verdadero=(Button) findViewById(R.id.boton_verdadero);
        boton_falso=(Button) findViewById(R.id.Boton_falso);
        boton_siguiente=(ImageButton) findViewById(R.id.siguiente);
        boton_anterior=(ImageButton) findViewById(R.id.anterior);


        boton_verdadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar_respuesta(true);
            }
        });

        boton_falso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar_respuesta(false);
            }
        });

        boton_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_preguntas=(num_preguntas + 1) % preguntas.length;
                actualizar_pregunta();

            }
        });

        boton_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(num_preguntas==0){
                    num_preguntas=preguntas.length-1;
                }
                else {
                    num_preguntas=(num_preguntas - 1) % preguntas.length;
                }
                actualizar_pregunta();
            }
        });



    }
    public void actualizar_pregunta(){
        boton_verdadero=(Button) findViewById(R.id.boton_verdadero);
        boton_falso=(Button) findViewById(R.id.Boton_falso);

        int pregunta=preguntas[num_preguntas].getId_texto();
        text_pregunta.setText(pregunta);

        if (preguntas[num_preguntas].isContestado()==true){
            boton_verdadero.setEnabled(false);
            boton_falso.setEnabled(false);
        }
        else{
            boton_verdadero.setEnabled(true);
            boton_falso.setEnabled(true);
        }
    }

    public void verificar_respuesta(boolean respuesta){
        boolean truefalse = preguntas[num_preguntas].isRespuesta();

        preguntas[num_preguntas].setContestado(true);
        boton_verdadero.setEnabled(false);
        boton_falso.setEnabled(false);

        int mensaje_retorno =0;

        if (truefalse==respuesta){
            mensaje_retorno=(R.string.verdadero);
        }else {
            mensaje_retorno=(R.string.falso);
        }

        Toast.makeText(this, mensaje_retorno, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        boolean [] contestado = new boolean[preguntas.length];
        for (int i = 0; i < preguntas.length; i++) {
            contestado[i]=preguntas[i].isContestado();
        }
        savedInstanceState.putBooleanArray("contestado",contestado);
        savedInstanceState.putInt("num_preguntas",num_preguntas);
        super.onSaveInstanceState(savedInstanceState);
    }

}