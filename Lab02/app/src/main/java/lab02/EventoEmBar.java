package lab02;

public class EventoEmBar extends CaracteristicaDeEvento{
    private String nomeBar;
    private String horarioInicio;
    private String horarioFim; 

    public EventoEmBar(String nomeBar, String horarioInicio, String horarioFim) {
        this.nomeBar = nomeBar;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    public String getDescricao() {
        return "Evento no bar " + nomeBar + ", Happy Hour Inicio: " + horarioInicio + ", Duracão: " + horarioFim;
    }
}