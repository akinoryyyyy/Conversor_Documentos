package gerador;

public class GeradorPDFFactory extends GeradorDocumentoFactory {
    @Override
    public GeradorDocumento criarGerador() {
        return new GeradorPDF();
    }
}
