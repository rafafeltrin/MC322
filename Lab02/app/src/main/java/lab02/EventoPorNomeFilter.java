package lab02;

public class EventoPorNomeFilter implements Filter<Evento>{
    private String nomeEvento;
    
    public EventoPorNomeFilter(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
    
    @Override
    public boolean matches(Evento evento) {
        return evento.getNome().equalsIgnoreCase(nomeEvento);
    }
} 
