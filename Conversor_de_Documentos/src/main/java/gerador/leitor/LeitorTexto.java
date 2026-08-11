package gerador.leitor;

import java.io.File;
import java.nio.file.Files;

public class LeitorTexto implements LeitorDocumento {

    @Override
    public String ler(File arquivo) throws Exception {
        // Funciona tanto para .txt quanto para .csv, pois ambos são texto puro
        return Files.readString(arquivo.toPath());
    }
}
