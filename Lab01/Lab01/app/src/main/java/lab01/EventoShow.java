package lab01;

public class EventoShow extends Evento {
    private String artista;
    private String genero;
    private String duracao;

    public EventoShow(String nome, String local, String data, double precoIngresso, String artista, String genero, String duracao) {
        super(nome, local, data, precoIngresso);
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }
}
