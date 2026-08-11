package gerador;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorWord implements GeradorDocumento {

    @Override
    public void gerar(String conteudo, String nomeArquivo) throws IOException {
        try (XWPFDocument documento = new XWPFDocument()) {
            XWPFParagraph paragrafo = documento.createParagraph();
            XWPFRun run = paragrafo.createRun();
            run.setText(conteudo);

            String arquivoFinal = garantirExtensao(nomeArquivo, ".docx");
            try (FileOutputStream out = new FileOutputStream(arquivoFinal)) {
                documento.write(out);
            }
            System.out.println("Word gerado com sucesso em: " + new File(arquivoFinal).getAbsolutePath());
        }
    }

    private String garantirExtensao(String nome, String extensao) {
        return nome.toLowerCase().endsWith(extensao) ? nome : nome + extensao;
    }
}
