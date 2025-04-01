package lab01;

/**
 * Representa um ingresso com desconto para clientes do Banco do Brasil.
 * O preço do ingresso é calculado como 70% do preço do ingresso padrão.
 * 
 * @see Ingresso
 * @author Rafael Feltrin - 276246
 */
public class IngressoBancoDoBrasil extends Ingresso {

    /**
     * Construtor da classe IngressoBancoDoBrasil.
     * 
     * @param evento o evento associado ao ingresso
     */
    public IngressoBancoDoBrasil(Evento evento) {
        super(evento);
    }

    /**
     * Retorna o preço do ingresso com desconto para clientes do Banco do Brasil.
     * O preço é calculado como 70% do preço do ingresso padrão.
     * 
     * @return o preço do ingresso com desconto
     */
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso() * 0.7;
    }
}