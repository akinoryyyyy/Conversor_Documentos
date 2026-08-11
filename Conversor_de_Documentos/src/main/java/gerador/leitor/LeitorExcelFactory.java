package gerador.leitor;

public class LeitorExcelFactory extends LeitorDocumentoFactory {
    @Override
    public LeitorDocumento criarLeitor() {
        return new LeitorExcel();
    }
}
