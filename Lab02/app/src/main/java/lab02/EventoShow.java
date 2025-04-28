/*

 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;


public class EventoShow extends CaracteristicaDeEvento {
    private String artista;

    public EventoShow(String artista) {
        this.artista = artista;
    }

    public String getDescricao(){
        return "Artista: " + artista;
    }
}