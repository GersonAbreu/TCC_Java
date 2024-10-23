package programaExplicaFabi;

import java.time.LocalDate;

public class Transacao {
    private double valor;
    private String categoria;
    private LocalDate data;
    private boolean receita; // true para receita, false para despesa
    private String descricao;

    public Transacao(double valor, String categoria, LocalDate data, boolean receita, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
        this.receita = receita;
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isReceita() {
        return receita;
    }

    public String getDescricao() {
        return descricao;
    }
}
