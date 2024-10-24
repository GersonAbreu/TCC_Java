package programaExplicaFabi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class ExtratoFinanceiro {
    private ControladorFinanceiro controlador;
    private Principal controleFinanceiro;
    private ObservableList<Transacao> transacoesObservableList;

    public ExtratoFinanceiro(ControladorFinanceiro controlador, Principal controleFinanceiro) {
        this.controlador = controlador;
        this.controleFinanceiro = controleFinanceiro;
        this.transacoesObservableList = FXCollections.observableArrayList(controlador.listarTransacoes());
    }

    @SuppressWarnings("unchecked")
	public void show() {
        Stage extratoStage = new Stage();
        extratoStage.setTitle("Extrato Financeiro");

        TableView<Transacao> tableView = new TableView<>();
        TableColumn<Transacao, String> colunaValor = new TableColumn<>("Valor");
        colunaValor.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getValor())));

        TableColumn<Transacao, String> colunaCategoria = new TableColumn<>("Categoria");
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        TableColumn<Transacao, String> colunaData = new TableColumn<>("Data");
        colunaData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getData().toString()));

        TableColumn<Transacao, String> colunaTipo = new TableColumn<>("Tipo");
        colunaTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isReceita() ? "Receita" : "Despesa"));

        TableColumn<Transacao, String> colunaDescricao = new TableColumn<>("Descrição");
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        tableView.getColumns().addAll(colunaValor, colunaCategoria, colunaData, colunaTipo, colunaDescricao);
        tableView.setItems(transacoesObservableList);

        // Calcular totais
        double totalReceitas = 0.0;
        double totalDespesas = 0.0;
        
        for (Transacao transacao : transacoesObservableList) {
            if (transacao.isReceita()) {
                totalReceitas += transacao.getValor();
            } else {
                totalDespesas += transacao.getValor();
            }
        }
        
        double totalGeral = totalReceitas - totalDespesas;

        // Labels para mostrar totais
        Label labelTotalReceitas = new Label("Total Receitas: R$ " + String.format("%.2f", totalReceitas));
        Label labelTotalDespesas = new Label("Total Despesas: R$ " + String.format("%.2f", totalDespesas));
        Label labelTotalGeral = new Label("Total Geral: R$ " + String.format("%.2f", totalGeral));

        Button btnRemover = new Button("Remover Transação Selecionada");
        btnRemover.setOnAction(e -> {
            Transacao transacaoSelecionada = tableView.getSelectionModel().getSelectedItem();
            if (transacaoSelecionada != null) {
                controlador.removerTransacao(transacaoSelecionada);
                transacoesObservableList.remove(transacaoSelecionada);
                controleFinanceiro.atualizarSaldo();
            }
        });

        VBox vbox = new VBox(tableView, labelTotalReceitas, labelTotalDespesas, labelTotalGeral, btnRemover);
        Scene scene = new Scene(vbox);
        extratoStage.setScene(scene);
        extratoStage.show();
    }
}
