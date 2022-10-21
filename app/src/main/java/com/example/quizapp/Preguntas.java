package com.example.quizapp;

public class Preguntas {

    private int id_texto;
    private boolean respuesta;
    private boolean contestado;

    public Preguntas(int id_texto, boolean respuesta) {
        this.id_texto = id_texto;
        this.respuesta = respuesta;
        this.contestado=false;
    }

    public int getId_texto() {
        return id_texto;
    }

    public void setId_texto(int id_texto) {
        this.id_texto = id_texto;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isContestado() {return contestado;}

    public void setContestado(boolean contestado) {this.contestado = contestado;}
}
