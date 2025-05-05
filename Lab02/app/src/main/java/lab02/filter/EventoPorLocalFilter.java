package lab02.filter;

import java.util.Objects;

import lab02.Evento;
import lab02.Local;

public class EventoPorLocalFilter implements Filter<Evento>{
    private Local localEventoBusca;

    public EventoPorLocalFilter(Local localEventoBusca){
        this.localEventoBusca = localEventoBusca;
    }

    @Override
    public boolean matches(Evento evento){
        return Objects.equals(evento.getLocal(), localEventoBusca);
    }
}
