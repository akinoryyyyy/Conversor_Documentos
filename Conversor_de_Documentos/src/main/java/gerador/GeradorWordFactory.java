package gerador;

public class GeradorWordFactory extends GeradorDocumentoFactory {
    @Override
    public GeradorDocumento criarGerador() {
        return new GeradorWord();
    }
}
