package com.ados.cotuca.proj_05_ados.tools;

import android.support.annotation.*;

public class Disciplina {
    protected Integer cod;
    protected String nome;

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(@IntRange(from = 1) Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public Disciplina(@IntRange(from = 1) int cod, @NonNull String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "cod=" + this.cod + ", nome='" + this.nome + '\'' + '}';
    }

    @Override
    public int hashCode() {
        int ret = this.cod;
        ret = 13 * ret + this.nome.hashCode();

        return ret;
    }

    @Override
    public Object clone() {
        return new Disciplina(this);
    }

    protected Disciplina(Disciplina disciplina) {
        this.cod = disciplina.cod;
        this.nome = disciplina.nome;
    }
}
