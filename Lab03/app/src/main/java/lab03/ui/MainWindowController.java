package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lab03.Cliente;
import lab03.Evento;
import lab03.Marketplace;
import lab03.exceptions.IngressoEsgotadoException;

import java.io.IOException;
import java.util.List;

/**
 * Controller para a janela principal da aplicação (MainWindow.fxml).
 * Responsável por popular a tabela de eventos e lidar com as interações do usuário.
 */
public class MainWindowController {

    // Anotação @FXML para injetar os componentes do arquivo FXML.
    // O nome da variável DEVE ser igual ao fx:id no FXML.
    @FXML
    private TableView<Evento> eventosTable;

    @FXML
    private TableColumn<Evento, String> nomeColumn;

    @FXML
    private TableColumn<Evento, String> dataColumn;

    @FXML
    private TableColumn<Evento, Double> precoColumn;

    @FXML
    private Label detalhesNomeLabel;

    @FXML
    private Label detalhesLocalLabel;

    @FXML
    private Label detalhesOrganizadorLabel;

    @FXML
    private Label detalhesCapacidadeLabel;

    @FXML
    private Label detalhesDescricaoLabel;

    @FXML
    private Label clienteNomeLabel;

    @FXML
    private Label clienteSaldoLabel;


    // Atributos para guardar a referência aos dados do sistema
    private List<Evento> listaDeEventos;
    private Marketplace marketplace;

    private Cliente clienteAtual;

    /**
     * Método chamado automaticamente pelo JavaFX após o FXML ser carregado.
     * Usado para configurar os componentes iniciais da tela.
     */
    @FXML
    public void initialize() {
        // Configura as colunas da tabela para saber de onde pegar os dados de um objeto Evento.
        // O valor "nome", "data", "precoIngresso" DEVE corresponder exatamente
        // ao nome do método getter na classe Evento (ex: getNome(), getData(), getPrecoIngresso()).
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("precoIngresso"));

        eventosTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        exibirDetalhesDoEvento(newValue);
                    }
                }
        );
    }

    /**
     * Método para receber os dados da classe principal (App) e popular a interface.
     * @param eventos A lista de todos os eventos a serem exibidos.
     * @param marketplace A instância do marketplace do sistema.
     */
    public void initData(List<Evento> eventos, Marketplace marketplace, Cliente clienteLogado) {
        this.listaDeEventos = eventos;
        this.marketplace = marketplace;

        // Atualiza os detalhes do cliente logado na interface.
        clienteNomeLabel.setText(clienteLogado.getNome());
        clienteSaldoLabel.setText(String.format("R$ %.2f", clienteLogado.getSaldo()));
        this.clienteAtual = clienteLogado;

        // Popula a tabela com os eventos.
        // FXCollections.observableArrayList cria uma lista "observável" que a TableView entende.
        eventosTable.setItems(FXCollections.observableArrayList(this.listaDeEventos));
    }

    private void exibirDetalhesDoEvento(Evento evento) {
        detalhesNomeLabel.setText(evento.getNome());
        detalhesLocalLabel.setText(evento.getLocal() != null ? evento.getLocal().getNome() : "Não definido");
        detalhesOrganizadorLabel.setText(evento.getOrganizadora() != null ? evento.getOrganizadora().getNome() : "Não definido");
        detalhesCapacidadeLabel.setText(String.valueOf(evento.getCapacidadeMaxima()));
        detalhesDescricaoLabel.setText(evento.getCaracteristica() != null ? evento.getCaracteristica().getDescricao() : "Sem descrição");
    }

    @FXML
    public void handleComprarIngresso() {
        Evento eventoSelecionado = eventosTable.getSelectionModel().getSelectedItem();
        Cliente clienteLogado = this.clienteAtual; // Supondo que você tenha uma variável para o cliente logado

        if (eventoSelecionado == null) {
            exibirAlerta("Erro", "Nenhum evento selecionado.");
            return;
        }

        try {
            eventoSelecionado.venderIngresso(clienteLogado);

            // 3. Atualizar a UI com o sucesso
            exibirAlerta("Sucesso", "Ingresso para " + eventoSelecionado.getNome() + " comprado com sucesso!");

            exibirDetalhesDoEvento(eventoSelecionado);

            atualizarHeaderCliente(clienteLogado);

        } catch (IngressoEsgotadoException e) {
            // Captura a exceção da lógica de negócio e mostra uma mensagem amigável
            exibirAlerta("Falha na Compra", e.getMessage());
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void atualizarHeaderCliente(Cliente cliente) {
        clienteSaldoLabel.setText(String.format("R$ %.2f", cliente.getSaldo()));
    }

    @FXML
    private void handleVerMeusIngressos() {
        // Verifique se um cliente está "logado"
        if (this.clienteAtual == null) {
            // exibirAlerta("Atenção", "Nenhum cliente está selecionado.");
            System.out.println("Nenhum cliente logado para mostrar os ingressos.");
            return;
        }

        try {
            // 1. Carrega o arquivo FXML da nova janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MeusIngressos.fxml"));
            Parent root = loader.load();

            // 2. Pega o controller da nova janela
            MeusIngressosController controller = loader.getController();
            // 3. Passa os dados necessários para o novo controller
            controller.initData(this.clienteAtual);

            // 4. Cria e configura a nova janela (Stage)
            Stage stage = new Stage();
            stage.setTitle("Ingressos de " + this.clienteAtual.getNome());
            Scene scene = new Scene(root);

            // Aplica o mesmo CSS para manter a consistência visual
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.setScene(scene);

            // 5. Define a modalidade para que a nova janela seja modal (bloqueia a janela principal)
            stage.initModality(Modality.APPLICATION_MODAL);

            // 6. Mostra a janela e espera o usuário fechá-la
            stage.showAndWait();

            atualizarHeaderCliente(clienteAtual);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}