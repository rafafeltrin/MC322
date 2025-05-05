package lab02;

import java.util.ArrayList;
import java.util.List;

import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.filter.Filter;

public class Organizadora {

    private String nome;
    private int cnpj;
    private String endereco;
    private List<Evento> eventos = new ArrayList<>();

    /**
     * Construtor da classe Organizadora
     * @param nome o nome da organizadora
     * @param cnpj o CNPJ da organizadora
     * @param endereco o endereço da organizadora
     */
    public Organizadora(String nome, int cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    //Aqui vou fazer vários criar eventos um para cada tipo de evento
    
    //Festival
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> lineup, int duracao) throws LocalIndisponivelException, CapacidadeInsuficienteException {  
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoFestival(lineup, duracao));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    //Show
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String artista) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoShow(artista));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }
    
    //Esporte
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> times, String esporte) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoJogo(times, esporte));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    //Música ao vivo
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeDoArtista, String generoMusical) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoMusicaAoVivo(nomeDoArtista, generoMusical));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    //Em bar
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeBar, String horarioInicio, String horarioFim) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoEmBar(nomeBar, horarioInicio, horarioFim));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    public List<Evento> buscarEventos(Filter<Evento> filtro){
        List<Evento> eventosBusca = new ArrayList<>();

        for (Evento e : eventos){
            if (filtro.matches(e)){
                eventosBusca.add(e);
            }
        }

        return eventosBusca;
    }
}
