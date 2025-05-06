/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.List;

/**
 * Representa um evento de jogo, armazenando a modalidade esportiva e os times participantes.
 */
public class EventoJogo extends CaracteristicaDeEvento {
    private List<String> times;
    private String esporte;

    /**
     * Construtor do EventoJogo.
     * @param times lista de times participantes
     * @param esporte o tipo de esporte
     */
    public EventoJogo(List<String> times, String esporte) {
        this.times = times;
        this.esporte = esporte;
    }

    /**
     * Retorna a descrição do jogo.
     * @return a descrição do jogo
     */
    @Override
    public String getDescricao() {
        return "Esporte: " + this.esporte + " - Times: " + this.times;
    }
}
