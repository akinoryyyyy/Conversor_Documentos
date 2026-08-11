package gerador.leitor;

public class LeitorTextoFactory extends LeitorDocumentoFactory {
    @Override
    public LeitorDocumento criarLeitor() {
        return new LeitorTexto();
    }
}
