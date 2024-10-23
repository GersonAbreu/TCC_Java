package programaExplicaFabi;

import java.util.ArrayList;
import java.util.List;

public class ControladorFinanceiro {
    private List<Transacao> transacoes;

    public ControladorFinanceiro() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void removerTransacao(Transacao transacao) {
        transacoes.remove(transacao);
    }

    public double calcularSaldo() {
        double saldo = 0.0;
        for (Transacao transacao : transacoes) {
            if (transacao.isReceita()) {
                saldo += transacao.getValor();
            } else {
                saldo -= transacao.getValor();
            }
        }
        return saldo;
    }

    public List<Transacao> listarTransacoes() {
        return transacoes;
    }
}
