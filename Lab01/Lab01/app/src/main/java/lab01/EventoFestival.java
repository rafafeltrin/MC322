package lab01;

import java.util.ArrayList;
import java.util.List;

public class EventoFestival extends Evento {
    private String TipoFestival;
    private List<String> patrocinadores;

    public EventoFestival(String nome, Local local, String data, double precoIngresso, String tipoFestival) {
        super(nome, local, data, precoIngresso);
        this.TipoFestival = tipoFestival;
        this.patrocinadores = new ArrayList<>();
    }

    public String getTipoFestival() {
        return TipoFestival;
    }

    public void setTipoFestival(String tipoFestival) {
        TipoFestival = tipoFestival;
    }

    public void exibirPatrocinadores() {
        System.out.println("Patrocinadores do festival " + getNome() + ":");
        for (String patrocinador : patrocinadores) {
            System.out.println("- " + patrocinador);
        }
    }

    public void adicionarPatrocinador(String patrocinador) {
        if (!patrocinador.isEmpty()) {
            patrocinadores.add(patrocinador);
        } else {
            System.out.println("Patrocinador inválido.");
        }
        System.out.println("Patrocinador " + patrocinador + " adicionado ao festival " + getNome() + ".");
    }

    public void removerPatrocinador(String patrocinador) {
        if (patrocinadores.remove(patrocinador)) {
            System.out.println("Patrocinador " + patrocinador + " removido do festival " + getNome() + ".");
        } else {
            System.out.println("Patrocinador " + patrocinador + " não encontrado no festival " + getNome() + ".");
        }
    }

    public void exibirDetalhes() {
        System.out.println("===================================");
        System.out.println("Festival: " + getNome());
        System.out.println("Tipo de Festival: " + TipoFestival);
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Data: " + getData());
        System.out.println("Preço do Ingresso: R$" + getPrecoIngresso());
        exibirPatrocinadores();
        System.out.println("===================================");
    }
}
