//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)
package lab01;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Representa um evento do tipo festival, com informações específicas como tipo de festival
 * e lista de patrocinadores.
 * 
 * @author Rafael Feltrin - 276246
 */
public class EventoFestival extends Evento {
    private String TipoFestival;
    private List<String> patrocinadores;

    /**
     * Construtor da classe EventoFestival.
     * 
     * @param nome           Nome do evento.
     * @param local          Local onde o evento será realizado.
     * @param data           Data do evento.
     * @param precoIngresso  Preço do ingresso do evento.
     * @param tipoFestival   Tipo do festival.
     */
    public EventoFestival(String nome, Local local, String data, double precoIngresso, String tipoFestival) {
        super(nome, local, data, precoIngresso);
        this.TipoFestival = tipoFestival;
        this.patrocinadores = new ArrayList<>();
    }

    /**
     * Obtém o tipo do festival.
     * 
     * @return Tipo do festival.
     */
    public String getTipoFestival() {
        return TipoFestival;
    }

    /**
     * Define o tipo do festival.
     * 
     * @param tipoFestival Novo tipo do festival.
     */
    public void setTipoFestival(String tipoFestival) {
        TipoFestival = tipoFestival;
    }

    /**
     * Exibe a lista de patrocinadores do festival.
     */
    public void exibirPatrocinadores() {
        System.out.println("Patrocinadores do festival " + getNome() + ":");
        if (patrocinadores.isEmpty()) {
            System.out.println("Nenhum patrocinador cadastrado.");
            return;
        } else {
            for (String patrocinador : patrocinadores) {
                System.out.println("- " + patrocinador);
            }
        }
    }

    /**
     * Adiciona um patrocinador à lista de patrocinadores do festival.
     * 
     * @param patrocinador Nome do patrocinador a ser adicionado.
     */
    public void adicionarPatrocinador(String patrocinador) {
        patrocinadores.add(patrocinador);

        System.out.println("Patrocinador " + patrocinador + " adicionado ao festival " + getNome() + ".");
    }

    /**
     * Remove um patrocinador da lista de patrocinadores do festival.
     * 
     * @param patrocinador Nome do patrocinador a ser removido.
     */
    public void removerPatrocinador(String patrocinador) {
        if (patrocinadores.remove(patrocinador)) {
            System.out.println("Patrocinador " + patrocinador + " removido do festival " + getNome() + ".");
        } else {
            System.out.println("Patrocinador " + patrocinador + " não encontrado no festival " + getNome() + ".");
        }
    }

    /**
     * Exibe os detalhes do festival, incluindo informações gerais e patrocinadores.
     */
    public void exibirDetalhes() {
        System.out.println("Festival: " + getNome());
        System.out.println("Tipo de Festival: " + TipoFestival);
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Data: " + getData());
        System.out.println("Preço do Ingresso: R$" + getPrecoIngresso());
        System.out.println("Faturamento total: R$" + calcularFaturamento());
        exibirPatrocinadores();
    }

    /**
     * Filtra eventos do tipo festival com base no tipo de festival.
     * 
     * @param evento Evento a ser comparado.
     * @return true se o tipo do festival for igual, false caso contrário.
     */
    public boolean filtrar(Evento evento) {
        if (evento instanceof EventoFestival) {
            EventoFestival eventoFestival = (EventoFestival) evento;
            return this.TipoFestival.equals(eventoFestival.getTipoFestival());
        }
        return false;
    }

}
