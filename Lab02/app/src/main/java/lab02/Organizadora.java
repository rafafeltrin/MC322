package lab02;

import java.util.List;

public class Organizadora {

    private String nome;
    private int cnpj;
    private String endereco;
    private List<Evento> eventos;

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
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> lineup, int duracao){
        Evento evento = new Evento(nome, local, precoIngresso, organizadora, data, capacidadeMaxima, new EventoFestival(lineup, duracao));
        eventos.add(evento);
    }

    //Show
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String artista){
        Evento evento = new Evento(nome, local, precoIngresso, organizadora, data, capacidadeMaxima, new EventoShow(artista));
        eventos.add(evento);
    }
    
    //Esporte
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> times, String esporte){
        Evento evento = new Evento(nome, local, precoIngresso, organizadora, data, capacidadeMaxima, new EventoJogo(times, esporte));
        eventos.add(evento);
    }

    //Música ao vivo
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeDoArtista, String generoMusical){
        Evento evento = new Evento(nome, local, precoIngresso, organizadora, data, capacidadeMaxima, new EventoMusicaAoVivo(nomeDoArtista, generoMusical));
        eventos.add(evento);
    }

    //Em bar
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeBar, String horarioInicio, String horarioFim){
        Evento evento = new Evento(nome, local, precoIngresso, organizadora, data, capacidadeMaxima, new EventoEmBar(nomeBar, horarioInicio, horarioFim));
        eventos.add(evento);
    }
}
