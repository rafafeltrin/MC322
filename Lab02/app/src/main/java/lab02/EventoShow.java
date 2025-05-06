//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;


/**
 * Representa um evento do tipo show, armazenando o nome do artista.
 */
public class EventoShow extends CaracteristicaDeEvento {
    private String artista;

    /**
     * Construtor do EventoShow.
     * @param artista o nome do artista
     */
    public EventoShow(String artista) {
        this.artista = artista;
    }

    /**
     * Retorna a descrição do show.
     * @return a descrição do show
     */
    public String getDescricao(){
        return "Artista: " + artista;
    }
}