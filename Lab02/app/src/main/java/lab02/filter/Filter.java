//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab02.filter;

/**
 * Interface genérica para filtros de objetos.
 *
 * @param <T> o tipo de objeto a ser filtrado
 */
public interface Filter<T> {
    /**
     * Avalia se um dado objeto atende aos critérios deste filtro.
     *
     * @param t o objeto a ser testado
     * @return {@code true} se o objeto satisfaz o filtro, {@code false} caso contrário
     */
    boolean matches(T t);
}
