package lab02;

public class EventoMusicaAoVivo extends CaracteristicaDeEvento {
    private String nomeDoArtista;
    private String generoMusical;

    public EventoMusicaAoVivo(String nomeDoArtista, String generoMusical) {
        this.nomeDoArtista = nomeDoArtista;
        this.generoMusical = generoMusical;
    }

    public String getDescricao(){
        return "Musica ao vivo com " + nomeDoArtista + " do gênero " + generoMusical;
    }
}
