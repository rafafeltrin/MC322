//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab01;

/**
 * Representa um ingresso de valor integral (sem descontos).
 * O preço do ingresso é igual ao preço padrão do evento.
 * 
 * @see Ingresso
 * @author Rafael Feltrin - 276246
 */
public class IngressoInteira extends Ingresso {
    /**
     * Construtor da classe IngressoInteira.
     * 
     * @param evento o evento associado ao ingresso
     */
    public IngressoInteira(Evento evento) {
        super(evento);
    }

    /**
     * Retorna o preço do ingresso de valor integral.
     * O preço é igual ao preço padrão do evento.
     * 
     * @return o preço do ingresso integral
     */
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso();
    }
}
