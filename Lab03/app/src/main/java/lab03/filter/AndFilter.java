//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.filter;

/**
 * Filtro genérico que combina dois filtros usando conjunção lógica (AND).
 *
 * @param <T> o tipo de objeto a ser testado pelos filtros
 */
public class AndFilter<T> implements Filter<T> {
    private Filter<T> filter1;
    private Filter<T> filter2;

    /**
     * Cria um filtro que retorna {@code true} somente se ambos os filtros
     * fornecidos também retornarem {@code true}.
     *
     * @param filter1 o primeiro filtro
     * @param filter2 o segundo filtro
     */
    public AndFilter(Filter<T> filter1, Filter<T> filter2){
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    /**
     * Verifica se ambos os filtros correspondem ao objeto informado.
     *
     * @param t o objeto a ser avaliado
     * @return {@code true} se primeiro e segundo filtros corresponderem; {@code false} caso contrário
     */
    @Override
    public boolean matches(T t){
        return filter1.matches(t) && filter2.matches(t);
    }
}
