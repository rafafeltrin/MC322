/*
 * Ingresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab01;

import java.util.Random;

/**
 * Contém a estrutura de implementação de um Ingresso.
 * 
 * @author Gabriel Leite - 216180
 * @author Caio Rhoden - 214129
 */
public abstract class Ingresso {
    private Evento evento;
    private String codigo;

    /**
     * Construtor da classe Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public Ingresso(Evento evento){
        this.evento = evento;
        this.codigo = gerarCodigo();
    }

    /**
     * Gera um número aleatório de 4 dígitos
     * @return A string equivalente ao número gerado
     */
    private String gerarCodigo(){
        Random random = new Random();
        // gerar um número aleatório de 0 a 3
        int n = random.nextInt(1000, 9999);
        return String.valueOf(n);
    }

    protected Evento getEvento(){
        return evento;
    }
    
    /**
     * Retorna o preço do Ingresso
     * @return o preço do Ingresso
     */
    public abstract double getPreco();
    
}
