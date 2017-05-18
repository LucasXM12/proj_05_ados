package com.ados.cotuca.proj_05_ados.tools;

import android.support.annotation.*;

public class Disciplina {
    protected int cod;
    protected String materia;

    public int getCod() {
        return this.cod;
    }

    public void setCod(@IntRange(from = 1) int cod) {
        this.cod = cod;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(@NonNull String materia) {
        this.materia = materia;
    }

    public Disciplina(@IntRange(from = 1) int cod, @NonNull String materia) {
        this.cod = cod;
        this.materia = materia;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "cod=" + this.cod + ", materia='" + this.materia + '\'' + '}';
    }

    @Override
    public int hashCode() {
        int ret = this.cod;
        ret = 13 * ret + this.materia.hashCode();

        return ret;
    }
}
