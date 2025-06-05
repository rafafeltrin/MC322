//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lab03.model.cliente.Cliente;
import lab03.model.evento.Evento;
import lab03.model.marketplace.Marketplace;
import lab03.exceptions.IngressoEsgotadoException;

import java.io.IOException;
import java.util.List;

/**
 * Controller para a janela principal da aplicação (MainWindow.fxml).
 * Responsável por popular a tabela de eventos e lidar com as interações do usuário.
 */
public class MainWindowController {
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
    private ComboBox<Cliente> clienteComboBox;

    @FXML
    private Label detalhesOrganizadorLabel;

    @FXML
    private Label detalhesCapacidadeLabel;

    @FXML
    private Label detalhesDescricaoLabel;

    @FXML
    private Label clienteSaldoLabel;


    private Marketplace marketplace;
    private Cliente clienteAtual;

    /**
     * Método chamado automaticamente pelo JavaFX após o FXML ser carregado.
     * Usado para configurar os componentes iniciais da tela.
     */
    @FXML
    public void initialize() {
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

        clienteComboBox.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente == null ? null : cliente.getNome();
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });

        // Adiciona uma espécie de ouvinte para o ComboBox de clientes
        clienteComboBox.valueProperty().addListener((obs, oldCliente, newCliente) -> {
            if (newCliente != null) {
                this.clienteAtual = newCliente;
                atualizarHeaderCliente(this.clienteAtual);
            }
        });
    }

    /**
     * Método para receber os dados da classe principal (App) e popular a interface.
     * @param eventos A lista de todos os eventos a serem exibidos.
     * @param marketplace A instância do marketplace do sistema.
     * @param todosClientes A lista de todos os clientes disponíveis.
     * @param clienteInicial O cliente que deve ser selecionado inicialmente no ComboBox.
     */
    public void initData(List<Evento> eventos, Marketplace marketplace,List<Cliente> todosClientes, Cliente clienteInicial) {
        // Atributos para guardar a referência aos dados do sistema
        this.marketplace = marketplace;
        this.clienteAtual = clienteInicial;

        // Popula o ComboBox com todos os clientes
        clienteComboBox.setItems(FXCollections.observableArrayList(todosClientes));
        clienteComboBox.setValue(this.clienteAtual);
        atualizarHeaderCliente(this.clienteAtual);

        // Popula a tabela com os eventos.
        eventosTable.setItems(FXCollections.observableArrayList(eventos));
    }

    /**
     * Exibe os detalhes do evento selecionado na tabela.
     * Atualiza os labels com as informações do evento.
     * @param evento O evento selecionado.
     */
    private void exibirDetalhesDoEvento(Evento evento) {
        detalhesNomeLabel.setText(evento.getNome());
        detalhesLocalLabel.setText(evento.getLocal() != null ? evento.getLocal().getNome() : "Não definido");
        detalhesOrganizadorLabel.setText(evento.getOrganizadora() != null ? evento.getOrganizadora().getNome() : "Não definido");
        detalhesCapacidadeLabel.setText(String.valueOf(evento.getCapacidadeMaxima()));
        detalhesDescricaoLabel.setText(evento.getCaracteristica() != null ? evento.getCaracteristica().getDescricao() : "Sem descrição");
    }

    /**
     * Método chamado quando o usuário clica no botão "Comprar Ingresso".
     * Verifica se um evento está selecionado e tenta vender um ingresso para o cliente logado.
     */
    @FXML
    public void handleComprarIngresso() {
        Evento eventoSelecionado = eventosTable.getSelectionModel().getSelectedItem();
        Cliente clienteLogado = this.clienteAtual;

        if (eventoSelecionado == null) {
            exibirAlerta("Erro", "Nenhum evento selecionado.");
            return;
        }

        try {
            eventoSelecionado.venderIngresso(clienteLogado);

            exibirAlerta("Sucesso", "Ingresso para " + eventoSelecionado.getNome() + " comprado com sucesso!");
            exibirDetalhesDoEvento(eventoSelecionado);
            atualizarHeaderCliente(clienteLogado);
        } catch (IngressoEsgotadoException e) {
            exibirAlerta("Falha na Compra", e.getMessage());
        }
    }

    /**
     * Exibe um alerta com o título e a mensagem fornecidos.
     * @param titulo O título do alerta.
     * @param mensagem A mensagem a ser exibida no alerta.
     */
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Atualiza o label do saldo do cliente no header.
     * Se o cliente for nulo, exibe "R$ --,--".
     * @param cliente O cliente cujos dados devem ser exibidos.
     */
    private void atualizarHeaderCliente(Cliente cliente) {
        if (cliente != null) {
            clienteSaldoLabel.setText(String.format("R$ %.2f", cliente.getSaldo()));
        } else {
            clienteSaldoLabel.setText("R$ --,--");
        }
    }

    /**
     * Método chamado quando o usuário clica no botão "Sair".
     * Fecha a aplicação.
     */
    @FXML
    private void handleVerMeusIngressos() {
        if (this.clienteAtual == null) {
            exibirAlerta("Atenção", "Nenhum cliente está selecionado.");
            return;
        }

        try {
            carregarTelaMeusIngressos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carrega a tela de Meus Ingressos em uma nova janela.
     * Passa o cliente atual e o marketplace para o controller da nova tela.
     * @throws IOException Se houver um erro ao carregar o FXML.
     */
    private void carregarTelaMeusIngressos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MeusIngressos.fxml"));
        Parent root = loader.load();

        MeusIngressosController controller = loader.getController();

        controller.initData(this.clienteAtual, this.marketplace);

        Stage stage = new Stage();
        stage.setTitle("Ingressos de " + this.clienteAtual.getNome());
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        atualizarHeaderCliente(clienteAtual);
    }


    /**
     * Método chamado quando o usuário clica no botão "Acessar Marketplace".
     * Verifica se um cliente está selecionado e tenta abrir a tela do marketplace.
     */
    @FXML
    private void handleAcessarMarketplace() {
        if (this.clienteAtual == null) {
            exibirAlerta("Atenção", "Selecione um cliente para acessar o marketplace.");
            return;
        }

        try {
            carregarTelaMarketplace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carrega a tela do Marketplace em uma nova janela.
     * Passa o marketplace e o cliente atual para o controller da nova tela.
     * @throws IOException Se houver um erro ao carregar o FXML.
     */
    private void carregarTelaMarketplace() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Marketplace.fxml"));
        Parent root = loader.load();

        MarketplaceController controller = loader.getController();
        controller.initData(this.marketplace, this.clienteAtual);

        Stage stage = new Stage();
        stage.setTitle("Marketplace de Ingressos");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        atualizarHeaderCliente(this.clienteAtual);
    }
}