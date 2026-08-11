package gerador;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class GeradorPDF implements GeradorDocumento {

    private static final int LARGURA_MAXIMA_LINHA = 90;

    @Override
    public void gerar(String conteudo, String nomeArquivo) throws IOException {
        try (PDDocument documento = new PDDocument()) {
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            try (PDPageContentStream stream = new PDPageContentStream(documento, pagina)) {
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.newLineAtOffset(50, 700);

                for (String linha : quebrarLinhas(conteudo)) {
                    stream.showText(linha);
                    stream.newLineAtOffset(0, -15);
                }

                stream.endText();
            }

            String arquivoFinal = garantirExtensao(nomeArquivo, ".pdf");
            documento.save(arquivoFinal);
            System.out.println("PDF gerado com sucesso em: " + new File(arquivoFinal).getAbsolutePath());
        }
    }

    private String[] quebrarLinhas(String texto) {
        // Divide o texto em blocos de tamanho fixo para não estourar a largura da página
        return texto.split("(?<=\\G.{" + LARGURA_MAXIMA_LINHA + "})");
    }

    private String garantirExtensao(String nome, String extensao) {
        return nome.toLowerCase().endsWith(extensao) ? nome : nome + extensao;
    }
}
