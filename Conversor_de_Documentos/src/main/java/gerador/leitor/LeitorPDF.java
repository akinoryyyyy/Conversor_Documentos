package gerador.leitor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class LeitorPDF implements LeitorDocumento {

    @Override
    public String ler(File arquivo) throws Exception {
        try (PDDocument documento = PDDocument.load(arquivo)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(documento);
        }
    }
}
