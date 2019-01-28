package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.codenation.Validador.*;

public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;
    private boolean capitao;

    public Jogador (Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.capitao = false;

        if(!validarJogador(this)){
            throw new IllegalArgumentException("Jogador invalido");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(validarId(id)) this.id = id;
        else throw new IllegalArgumentException();
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(validarNome(nome)) this.nome = nome;
        else throw new IllegalArgumentException();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if(validarData(dataNascimento)) this.dataNascimento = dataNascimento;
        else throw new IllegalArgumentException();
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
       if(validarNivelHabilidade(nivelHabilidade)) this.nivelHabilidade = nivelHabilidade;
       else throw new IllegalArgumentException();
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if(validarSalario(salario)) this.salario = salario;
        else throw new IllegalArgumentException();
    }

    public void setCapitao(boolean valor){
        capitao = true;
    }

    public boolean getCapitao(){
        return capitao;
    }

    public Long getIdade(){
        Long idade;
        return idade = Long.valueOf(getDataNascimento().getYear() - LocalDate.now().getYear());
    }

}
