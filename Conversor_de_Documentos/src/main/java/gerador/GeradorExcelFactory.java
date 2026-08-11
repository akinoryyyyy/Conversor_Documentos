package gerador;

public class GeradorExcelFactory extends GeradorDocumentoFactory {
    @Override
    public GeradorDocumento criarGerador() {
        return new GeradorExcel();
    }
}
