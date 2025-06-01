//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia os locais disponíveis para realização de eventos.
 */
public class ImobiliariaDeEventos {
    
    private List<Local> locais = new ArrayList<>();
    private String nome;


    /**
     * Construtor da classe ImobiliariaDeEventos
     * @param nome o nome da imobiliária de eventos
     */
    public ImobiliariaDeEventos(String nome) {
        this.nome = nome;
    }

    /**
     * Adiciona um local à lista de locais disponíveis
     * @param local o local a ser adicionado
     */
    public void adicionarLocal(Local local) {
        this.locais.add(local);
    }

    /**
     * Busca um local pelo nome.
     * @param nome nome do local
     * @return o local encontrado ou null se não existir
     */
    public Local buscarLocal(String nome) {
        for (Local local : locais) {
            if (local.getNome().equals(nome)) {
                return local;
            }
        }
        return null;
    }

    /**
     * Busca um local pela capacidade máxima.
     * @param capacidadeMaxima capacidade em número de pessoas
     * @return o local encontrado ou null se não existir
     */
    public Local buscarLocal(int capacidadeMaxima){
        for (Local local : locais) {
            if (local.getCapacidade() == capacidadeMaxima) {
                return local;
            }
        }
        return null; 
    }
    
}
