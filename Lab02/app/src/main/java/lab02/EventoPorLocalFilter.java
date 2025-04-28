package lab02;

import java.util.Objects;

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
