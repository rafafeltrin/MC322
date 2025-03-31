package lab01;

public class IngressoInteira extends Ingresso{
    public IngressoInteira(Evento evento) {
        super(evento);
    }
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso();
    }
}
