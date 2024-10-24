package programaExplicaFabi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoPersistencia {
    private static final String NOME_ARQUIVO = "transacoes.txt";

    public void salvar(List<Transacao> transacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(transacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public List<Transacao> carregar() {
        List<Transacao> transacoes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            transacoes = (List<Transacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transacoes;
    }
}
