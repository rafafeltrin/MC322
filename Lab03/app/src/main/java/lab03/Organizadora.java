//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03;

import java.util.ArrayList;
import java.util.List;

import lab03.exceptions.CapacidadeInsuficienteException;
import lab03.exceptions.LocalIndisponivelException;
import lab03.filter.Filter;

/**
 * Representa uma organizadora responsável por criar e gerenciar eventos.
 */
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
    
    /**
     * Cria um evento de festival.
     * @param nome nome do evento
     * @param local local onde o evento ocorrerá
     * @param precoIngresso preço do ingresso
     * @param organizadora organizadora responsável
     * @param data data do evento
     * @param capacidadeMaxima capacidade máxima do local
     * @param lineup lista de artistas
     * @param duracao duração em horas
     * @throws LocalIndisponivelException se o local já estiver ocupado
     * @throws CapacidadeInsuficienteException se a capacidade do local for insuficiente
     */
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> lineup, int duracao) throws LocalIndisponivelException, CapacidadeInsuficienteException {  
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoFestival(lineup, duracao));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    /**
     * Cria um evento de show.
     * @param nome nome do show
     * @param local local onde ocorrerá
     * @param precoIngresso preço do ingresso
     * @param organizadora organizadora responsável
     * @param data data do show
     * @param capacidadeMaxima capacidade máxima do local
     * @param artista artista principal
     * @throws LocalIndisponivelException se o local já estiver ocupado
     * @throws CapacidadeInsuficienteException se a capacidade do local for insuficiente
     */
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String artista) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoShow(artista));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }
    
    /**
     * Cria um evento esportivo.
     * @param nome nome do evento
     * @param local local onde ocorrerá
     * @param precoIngresso preço do ingresso
     * @param organizadora organizadora responsável
     * @param data data do evento
     * @param capacidadeMaxima capacidade máxima do local
     * @param times lista de times participantes
     * @param esporte tipo de esporte
     * @throws LocalIndisponivelException se o local já estiver ocupado
     * @throws CapacidadeInsuficienteException se a capacidade do local for insuficiente
     */
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, List<String> times, String esporte) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoJogo(times, esporte));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    /**
     * Cria um evento do tipo música ao vivo.
     * @param nome nome do evento
     * @param local local onde ocorrerá
     * @param precoIngresso preço do ingresso
     * @param organizadora organizadora responsável
     * @param data data do evento
     * @param capacidadeMaxima capacidade máxima do local
     * @param nomeDoArtista nome do artista
     * @param generoMusical gênero musical
     * @throws LocalIndisponivelException se o local já estiver ocupado
     * @throws CapacidadeInsuficienteException se a capacidade do local for insuficiente
     */
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeDoArtista, String generoMusical) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoMusicaAoVivo(nomeDoArtista, generoMusical));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    /**
     * Cria um evento em bar.
     * @param nome nome do evento
     * @param local local onde ocorrerá
     * @param precoIngresso preço do ingresso
     * @param organizadora organizadora responsável
     * @param data data do evento
     * @param capacidadeMaxima capacidade máxima do local
     * @param nomeBar nome do bar
     * @param horarioInicio horário de início
     * @param horarioFim horário de término
     * @throws LocalIndisponivelException se o local já estiver ocupado
     * @throws CapacidadeInsuficienteException se a capacidade do local for insuficiente
     */
    public void criarEvento(String nome, Local local, double precoIngresso, Organizadora organizadora, String data, int capacidadeMaxima, String nomeBar, String horarioInicio, String horarioFim) throws LocalIndisponivelException, CapacidadeInsuficienteException {
        Evento evento = new Evento(nome, precoIngresso, organizadora, data, capacidadeMaxima, new EventoEmBar(nomeBar, horarioInicio, horarioFim));
        local.alocarParaEvento(evento);
        eventos.add(evento);
    }

    /**
     * Busca eventos que satisfaçam um filtro.
     * @param filtro critério de busca
     * @return lista de eventos que atendem ao filtro
     */
    public List<Evento> buscarEventos(Filter<Evento> filtro){
        List<Evento> eventosBusca = new ArrayList<>();

        for (Evento e : eventos){
            if (filtro.matches(e)){
                eventosBusca.add(e);
            }
        }

        return eventosBusca;
    }

    /**
     * Retorna o nome da organizadora.
     * @return o nome da organizadora
     */
    public String getNome() {
        return nome;
    }
}
