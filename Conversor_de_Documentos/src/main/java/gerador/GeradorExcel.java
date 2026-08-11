package gerador;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorExcel implements GeradorDocumento {

    @Override
    public void gerar(String conteudo, String nomeArquivo) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Conteúdo");

            // Cada linha do texto vira uma linha da planilha
            String[] linhas = conteudo.split("\n");
            for (int i = 0; i < linhas.length; i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(linhas[i]);
            }

            String arquivoFinal = garantirExtensao(nomeArquivo, ".xlsx");
            try (FileOutputStream out = new FileOutputStream(arquivoFinal)) {
                workbook.write(out);
            }
            System.out.println("Excel gerado com sucesso em: " + new File(arquivoFinal).getAbsolutePath());
        }
    }

    private String garantirExtensao(String nome, String extensao) {
        return nome.toLowerCase().endsWith(extensao) ? nome : nome + extensao;
    }
}
