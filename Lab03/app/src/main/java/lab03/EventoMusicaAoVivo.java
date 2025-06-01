//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03;

/**
 * Representa um evento musical ao vivo, armazenando nome do artista e gênero musical.
 */
public class EventoMusicaAoVivo extends CaracteristicaDeEvento {
    private String nomeDoArtista;
    private String generoMusical;

    /**
     * Construtor do EventoMusicaAoVivo.
     * @param nomeDoArtista o nome do artista
     * @param generoMusical o gênero musical
     */
    public EventoMusicaAoVivo(String nomeDoArtista, String generoMusical) {
        this.nomeDoArtista = nomeDoArtista;
        this.generoMusical = generoMusical;
    }

    /**
     * Retorna a descrição do evento musical ao vivo.
     * @return a descrição do evento
     */
    public String getDescricao(){
        return "Musica ao vivo com " + nomeDoArtista + " do gênero " + generoMusical;
    }
}
