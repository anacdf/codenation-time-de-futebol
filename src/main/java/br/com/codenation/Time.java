package br.com.codenation;

import java.time.LocalDate;

import static br.com.codenation.Validador.*;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;

        if(!validarTime(this)){
            throw new IllegalArgumentException("Time invalido");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(validarId(id)) this.id = id;
        else throw new IllegalArgumentException();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(validarNome(nome)) this.nome = nome;
        else throw new IllegalArgumentException();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        if(validarData(dataCriacao)) this.dataCriacao = dataCriacao;
        else throw new IllegalArgumentException();
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        if(validarCorUniformePrincipal(corUniformePrincipal, getCorUniformeSecundario())) this.corUniformePrincipal = corUniformePrincipal;
        else throw new IllegalArgumentException();
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        if(validarCorUniformeSecundario(getCorUniformePrincipal(),corUniformeSecundario))this.corUniformeSecundario = corUniformeSecundario;
        else throw new IllegalArgumentException();
    }



}
