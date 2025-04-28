package lab02;

public class EventoPorDataFilter implements Filter<Evento>{
    private String DataBusca;

    public EventoPorDataFilter(String Databusca){
        this.DataBusca = Databusca;
    }

    @Override
    public boolean matches(Evento evento){
        return evento.getData().equalsIgnoreCase(DataBusca);
    }
}