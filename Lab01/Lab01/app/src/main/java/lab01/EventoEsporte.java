package lab01;

public class EventoEsporte extends Evento {
    private String tipoEsporte;
    private String categoria;
    private String time1;
    private String time2;

    public EventoEsporte(String nome, Local local, String data, double precoIngresso, String tipoEsporte, String categoria, String time1, String time2) {
        super(nome, local, data, precoIngresso);
        this.tipoEsporte = tipoEsporte;
        this.categoria = categoria;
        this.time1 = time1;
        this.time2 = time2;
    }

    public EventoEsporte(String nome, Local local, String data, double precoIngresso, String tipoEsporte, String categoria) {
        super(nome, local, data, precoIngresso);
        this.tipoEsporte = tipoEsporte;
        this.categoria = categoria;
    }

    public String getTipoEsporte() {
        return tipoEsporte;
    }

    public void setTipoEsporte(String tipoEsporte) {
        this.tipoEsporte = tipoEsporte;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void exibirDetalhes(){
        System.out.println("Nome: " + getNome());
        System.out.println("Local: " + getLocal().getNome());
        System.out.println("Capaciadade: " + getLocal().getCapacidade());
        System.out.println("Data: " + getData());
        System.out.println("Tipo de esporte: " + tipoEsporte);
        System.out.println("Categoria: " + categoria);

        if (time1 != null && time2 != null){
            System.out.println("Times: "+ time1 + " x " + time2);
        }

        System.out.println("Faturamento total: R$" + calcularFaturamento());
    }
}
