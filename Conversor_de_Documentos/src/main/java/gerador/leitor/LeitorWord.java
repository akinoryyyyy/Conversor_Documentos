package gerador.leitor;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;

public class LeitorWord implements LeitorDocumento {

    @Override
    public String ler(File arquivo) throws Exception {
        try (FileInputStream fis = new FileInputStream(arquivo);
             XWPFDocument documento = new XWPFDocument(fis)) {

            StringBuilder texto = new StringBuilder();
            for (XWPFParagraph paragrafo : documento.getParagraphs()) {
                if (texto.length() > 0) {
                    texto.append("\n");
                }
                texto.append(paragrafo.getText());
            }
            return texto.toString();
        }
    }
}
