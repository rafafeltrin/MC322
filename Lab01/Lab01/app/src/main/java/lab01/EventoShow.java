package lab01;

/**
 * Representa um evento do tipo show, com informações adicionais como artista, gênero e duração.
 * Extende a classe Evento.
 * 
 * @author Rafael Feltrin - 276246
 */
public class EventoShow extends Evento {
    private String artista;
    private String genero;
    private int duracao;

    /**
     * Construtor da classe EventoShow.
     * 
     * @param nome           Nome do evento.
     * @param local          Local onde o evento será realizado.
     * @param data           Data do evento.
     * @param precoIngresso  Preço do ingresso do evento.
     * @param artista        Nome do artista principal do show.
     * @param genero         Gênero musical do show.
     * @param duracao        Duração do show em minutos.
     */
    public EventoShow(String nome, Local local, String data, double precoIngresso, String artista, String genero, int duracao) {
        super(nome, local, data, precoIngresso);
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }

    /**
     * Obtém o nome do artista principal do show.
     * 
     * @return Nome do artista.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Define o nome do artista principal do show.
     * 
     * @param artista Nome do artista.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Obtém o gênero musical do show.
     * 
     * @return Gênero musical.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Define o gênero musical do show.
     * 
     * @param genero Gênero musical.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtém a duração do show em minutos.
     * 
     * @return Duração do show.
     */
    public String getDuracao() {
        return String.valueOf(duracao);
    }

    /**
     * Define a duração do show em minutos.
     * 
     * @param duracao Duração do show.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Exibe os detalhes do show, incluindo informações como nome, local, data,
     * artista, gênero, duração, preço do ingresso e faturamento total.
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("Show: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Data: " + getData());
        System.out.println("Artista: " + artista);
        System.out.println("Gênero: " + genero);
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Preço do Ingresso: R$" + getPrecoIngresso());
        System.out.println("Faturamento total: R$" + calcularFaturamento());
    }

    /**
     * Filtra eventos do tipo show com base no gênero musical.
     * 
     * @param evento Evento a ser comparado.
     * @return true se o gênero do evento for igual ao gênero deste show, false caso contrário.
     */
    public boolean filtrar(Evento evento) {
        if (evento instanceof EventoShow) {
            EventoShow eventoShow = (EventoShow) evento;
            return this.genero.equals(eventoShow.getGenero());
        }
        return false;
    }
}
