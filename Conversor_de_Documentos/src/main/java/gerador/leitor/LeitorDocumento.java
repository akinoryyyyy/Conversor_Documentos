package gerador.leitor;

import java.io.File;

public interface LeitorDocumento {
    String ler(File arquivo) throws Exception;
}
