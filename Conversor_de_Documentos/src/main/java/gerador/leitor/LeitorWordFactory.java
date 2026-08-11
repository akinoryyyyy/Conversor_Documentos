package gerador.leitor;

public class LeitorWordFactory extends LeitorDocumentoFactory {
    @Override
    public LeitorDocumento criarLeitor() {
        return new LeitorWord();
    }
}
