package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private Time time;
	private Jogador jogador;
	private List<Time> listaTimes = new ArrayList<>();
	private List<Jogador> listaJogadores= new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(timeExiste(id)) throw new IdentificadorUtilizadoException();
	   	else
	   		time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
	    listaTimes.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

		if(jogadorExiste(id)) throw new IdentificadorUtilizadoException();
		if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();

		else
			jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		listaJogadores.add(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		if(!jogadorExiste(idJogador)) throw new JogadorNaoEncontradoException();
		else
			for(Jogador j : listaJogadores){
			if(j.getId()==idJogador){
				j.setCapitao(true);
			}
			}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
        List<Jogador> todosJogadores;
        Long capitao;

	    if(!timeExiste(idTime)) throw new TimeNaoEncontradoException();

	    else
	    todosJogadores = buscarJogadores(idTime);
	    for(Jogador j : todosJogadores){
	        if(j.getCapitao()==true)
	           return capitao = j.getId();
        }
		throw new CapitaoNaoInformadoException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
	    String nome="";

		  for(Jogador j : listaJogadores){
		      if(j.getId()==idJogador){
		          nome = j.getNome();
              }
          }

        if(nome.isEmpty())throw new JogadorNaoEncontradoException();
		  else return  nome;
    }

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
	    String nome = "";
	    if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();

	    else {
	        for (Time t : listaTimes) {
	            if (t.getId() == idTime) {
	                nome = t.getNome();
	            }
	        }

            if(nome.isEmpty())throw new TimeNaoEncontradoException();
	        else return nome;
	    }
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Jogador> jogadores;
		List<Long> ids = new ArrayList<>();

		if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();

			else  {
				jogadores = buscarJogadores(idTime);
				for(Jogador j : jogadores){
					ids.add(j.getId());
				}
			return ids;
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
        List<Jogador> jogadores;
	    Long melhor;

        if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();

        else
		jogadores = buscarJogadores(idTime);
        melhor = jogadores.stream().max(Comparator.comparingInt(Jogador::getNivelHabilidade)).get().getId();
        return melhor;
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
        List<Jogador> jogadores;
        Long velho;

        if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();
        else
		jogadores = buscarJogadores(idTime);
        velho = jogadores.stream().max(Comparator.comparingLong(Jogador::getIdade)).get().getId();
        return velho;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> timesId = new LinkedList<>();

		for (Time t : listaTimes){
			timesId.add(t.getId());
		}

		return timesId;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
        List<Jogador> jogadores;
        Long salario;

        if (!timeExiste(idTime)) throw new TimeNaoEncontradoException();
        else
        jogadores = buscarJogadores(idTime);
        salario = jogadores.stream().max(Comparator.comparing(Jogador::getSalario)).get().getId();

        return salario;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        BigDecimal salario;
        Jogador jog;

        if(!jogadorExiste(idJogador)) throw new JogadorNaoEncontradoException();
        else
	    jog = buscarJogador(idJogador);
	    salario = jog.getSalario();

		return salario;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
	    List<Jogador> topJogadores;
        List<Jogador> topMelhores;
        List<Long> idMelhores = new LinkedList<>();

	    if(top<1) throw new UnsupportedOperationException();

        else
        topJogadores = listaJogadores.stream().sorted(Comparator.comparingInt(Jogador::getNivelHabilidade)).collect(toList());
        topMelhores = topJogadores.subList(0,top);

        for(Jogador m: topMelhores){
            idMelhores.add(m.getId());
        }

	    return idMelhores;
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

	    if(!timeExiste(timeDaCasa) || !timeExiste(timeDeFora)) throw new TimeNaoEncontradoException();

	    String corPrincipalTimeCasa = buscarTime(timeDaCasa).getCorUniformePrincipal();
	    String corPrincipalTimeFora = buscarTime(timeDeFora).getCorUniformePrincipal();
	    String corOutraTimeFora = buscarTime(timeDeFora).getCorUniformeSecundario();

	    if(corPrincipalTimeCasa.equalsIgnoreCase(corPrincipalTimeFora))
	        return corOutraTimeFora;

	    else if (!corPrincipalTimeCasa.equalsIgnoreCase(corPrincipalTimeFora))
	        return corPrincipalTimeFora;

	    else throw new TimeNaoEncontradoException();
	}

	private boolean timeExiste(Long id) {
		for (Time t : listaTimes){
			if (t.getId() == id)
				return true;
	}
		return false;
    }

    private boolean jogadorExiste(Long id){
	   for(Jogador j : listaJogadores)
		   if(j.getId()==id)
	        return true;

	   return false;
    }

    private Jogador buscarJogador(Long id){
	    return listaJogadores.stream()
                      .filter(j -> j.getId()==id)
                      .findFirst()
                      .get();
    }

    private List<Jogador> buscarJogadores(Long idTime){
        return listaJogadores.stream()
                .filter(j -> j.getIdTime()==idTime)
                .collect(toList());
    }

    private Time buscarTime(Long id){
	    Time resultado;
	    return resultado = listaTimes.stream()
                        .filter(t -> t.getId()==id)
                        .findFirst()
                        .get();
    }

}
