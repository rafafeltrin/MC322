package lab01;

public class IngressoBancoDoBrasil extends Ingresso{
    public IngressoBancoDoBrasil(Evento evento) {
        super(evento);
    }
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso() * 0.7;
    }
}