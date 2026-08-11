package gerador.leitor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class LeitorExcel implements LeitorDocumento {

    @Override
    public String ler(File arquivo) throws Exception {
        try (FileInputStream fis = new FileInputStream(arquivo);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            StringBuilder texto = new StringBuilder();

            for (Row row : sheet) {
                StringBuilder linha = new StringBuilder();
                for (Cell cell : row) {
                    if (linha.length() > 0) {
                        linha.append(";");
                    }
                    linha.append(cell.toString());
                }
                if (texto.length() > 0) {
                    texto.append("\n");
                }
                texto.append(linha);
            }
            return texto.toString();
        }
    }
}
