package lab02.filter;

import lab02.Evento;

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
