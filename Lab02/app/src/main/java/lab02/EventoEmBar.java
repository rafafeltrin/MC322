package lab02;

/**
 * Representa um evento que ocorre em um bar, com informações de nome e horários.
 */
public class EventoEmBar extends CaracteristicaDeEvento {
    private String nomeBar;
    private String horarioInicio;
    private String horarioFim; 

    /**
     * Construtor do EventoEmBar.
     * @param nomeBar nome do bar
     * @param horarioInicio horário de início do evento
     * @param horarioFim horário de término do evento
     */
    public EventoEmBar(String nomeBar, String horarioInicio, String horarioFim) {
        this.nomeBar = nomeBar;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    /**
     * Retorna a descrição do evento no bar.
     * @return descrição do evento
     */
    public String getDescricao() {
        return "Evento no bar " + nomeBar + ", Happy Hour Inicio: " + horarioInicio + ", Duracão: " + horarioFim;
    }
}