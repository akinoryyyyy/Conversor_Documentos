package gerador.leitor;

public class LeitorPDFFactory extends LeitorDocumentoFactory {
    @Override
    public LeitorDocumento criarLeitor() {
        return new LeitorPDF();
    }
}
