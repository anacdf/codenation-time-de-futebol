package br.com.codenation;

import br.com.codenation.Jogador;
import br.com.codenation.Time;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validador {

    public static boolean validarId(Long id){
        if(id<=0 || id%2!=0){
            return false;
        } else return true;
    }

    public static boolean validarNome(String nome){
        if(nome.isEmpty()){
            return false;
        } else return true;
    }

    public static boolean validarData(LocalDate data){
        LocalDate dataBase = LocalDate.of(1500, 01, 01);
        if(data.isBefore(dataBase)){
            return false;
        }
        if(data.isAfter(LocalDate.now())){
            return false;
        } else return true;
    }

    public static boolean validarCorUniformePrincipal(String corUniformePrincipal, String corUniformeSecundario){
        if(corUniformePrincipal.isEmpty()){
            return false;
        }
        if(corUniformePrincipal.equalsIgnoreCase(corUniformeSecundario)){
            return false;
        } else return true;
    }

    public static boolean validarCorUniformeSecundario(String corUniformePrincipal, String corUniformeSecundario){
        if(corUniformeSecundario.isEmpty()){
            return false;
        }
        if(corUniformeSecundario.equalsIgnoreCase(corUniformePrincipal)){
            return false;
        } else return true;
    }

    public static boolean validarTime(Time time){
        if(!validarId(time.getId())) {
            return false;
        }
        if(!validarNome(time.getNome())){
            return false;
        }
        if(!validarData(time.getDataCriacao())){
            return false;
        }
        if(!validarCorUniformePrincipal(time.getCorUniformePrincipal(), time.getCorUniformeSecundario())){
            return false;
        }
        if(!validarCorUniformeSecundario(time.getCorUniformeSecundario(), time.getCorUniformePrincipal())){
            return false;
        }
        else return true;
    }

    public static boolean validarNivelHabilidade(Integer nivelHabilidade){
        if(nivelHabilidade<0 && nivelHabilidade>100){
            return false;
        } else return true;
    }

    public static boolean validarSalario(BigDecimal salario){
        double val = salario.doubleValue();
        if(val<=0.0) return false;
        else return true;
    }

    public static boolean validarJogador(Jogador jogador){
        if(!validarId(jogador.getId())) {
            return false;
        }
        if(!validarNome(jogador.getNome())){
            return false;
        }
        if(!validarData(jogador.getDataNascimento())){
            return false;
        }
        if(!validarNivelHabilidade(jogador.getNivelHabilidade())){
            return false;
        }
        if(!validarSalario(jogador.getSalario())){
            return false;
        }
        else return true;
    }
}
