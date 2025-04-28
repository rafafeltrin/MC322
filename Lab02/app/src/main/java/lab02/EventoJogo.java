/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.List;

public class EventoJogo extends CaracteristicaDeEvento {
    private List<String> times;
    private String esporte;

    public EventoJogo(List<String> times, String esporte) {
        this.times = times;
        this.esporte = esporte;
    }

    @Override
    public String getDescricao() {
        return "Esporte: " + this.esporte + " - Times: " + this.times;
    }
}
