package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.Cliente;
import lab03.Ingresso;
import lab03.exceptions.CancelamentoNaoPermitidoException;
import lab03.exceptions.IngressoNaoEncontradoException;

public class MeusIngressosController {

    @FXML
    private TableView<Ingresso> ingressosTable;

    @FXML
    private TableColumn<Ingresso, String> eventoColumn;

    @FXML
    private TableColumn<Ingresso, String> dataColumn;

    @FXML
    private TableColumn<Ingresso, Double> precoColumn;

    @FXML
    private Button venderButton;

    @FXML
    private Button cancelarButton;

    private Cliente clienteLogado;

    @FXML
    public void initialize() {
        eventoColumn.setCellValueFactory(new PropertyValueFactory<>("nomeEventoCorrespondente"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataEventoCorrespondente"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }

    /**
     * Recebe os dados do cliente logado para poder exibir seus ingressos.
     * @param cliente O cliente que está visualizando seus ingressos.
     */
    public void initData(Cliente cliente) {
        this.clienteLogado = cliente;

        atualizarTabela();
    }

    private void atualizarTabela() {
        ingressosTable.setItems(FXCollections.observableArrayList(clienteLogado.getIngressos()));
    }

    @FXML
    private void handleVerMeusIngressos(){
        //Ingresso selecionado
        Ingresso ingressoSelecionado = ingressosTable.getSelectionModel().getSelectedItem();

        try {
            clienteLogado.cancelarIngresso(ingressoSelecionado);

            exibirAlerta("Sucesso", "Ingresso cancelado com sucesso!\nO valor já foi reembolsado.");
        } catch (IngressoNaoEncontradoException e){
            exibirAlerta("Erro ao realizar a venda", "Ingresso não encontrado: " + e.getMessage());
        } catch (CancelamentoNaoPermitidoException e) {
            exibirAlerta("Erro ao realizar a venda", "O ingresso não permite cancelamento" + e.getMessage());
        } finally {
            atualizarTabela();
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
