package gerador.leitor;

public class LeitorFactoryProvider {

    // Decide qual Factory usar de acordo com a extensão do arquivo colocado na pasta "entrada"
    public static LeitorDocumentoFactory obterFactory(String nomeArquivo) {
        String nome = nomeArquivo.toLowerCase();

        if (nome.endsWith(".pdf")) {
            return new LeitorPDFFactory();
        } else if (nome.endsWith(".docx")) {
            return new LeitorWordFactory();
        } else if (nome.endsWith(".xlsx")) {
            return new LeitorExcelFactory();
        } else if (nome.endsWith(".txt") || nome.endsWith(".csv")) {
            return new LeitorTextoFactory();
        } else {
            throw new IllegalArgumentException("Extensão de arquivo não suportada: " + nomeArquivo);
        }
    }
}
