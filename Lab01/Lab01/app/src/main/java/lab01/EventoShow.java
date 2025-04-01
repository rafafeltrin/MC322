package lab01;

public class EventoShow extends Evento {
    private String artista;
    private String genero;
    private String duracao;

    public EventoShow(String nome, Local local, String data, double precoIngresso, String artista, String genero, String duracao) {
        super(nome, local, data, precoIngresso);
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("===================================");
        System.out.println("Show: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Data: " + getData());
        System.out.println("Preço do Ingresso: R$" + getPrecoIngresso());
        System.out.println("Artista: " + artista);
        System.out.println("Gênero: " + genero);
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("===================================");
    }

}
