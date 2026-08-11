import gerador.GeradorDocumento;
import gerador.GeradorDocumentoFactory;
import gerador.GeradorExcelFactory;
import gerador.GeradorPDFFactory;
import gerador.GeradorWordFactory;
import gerador.leitor.LeitorDocumento;
import gerador.leitor.LeitorDocumentoFactory;
import gerador.leitor.LeitorFactoryProvider;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String PASTA_ENTRADA = "entrada";
    private static final String PASTA_SAIDA = "saida";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Garante que as pastas de entrada e saída existam
            Files.createDirectories(Path.of(PASTA_ENTRADA));
            Files.createDirectories(Path.of(PASTA_SAIDA));

            File arquivoEscolhido = escolherArquivoDeEntrada(scanner);

            // Lê o conteúdo do arquivo de acordo com a extensão (.pdf, .docx, .xlsx, .txt, .csv)
            LeitorDocumentoFactory leitorFactory = LeitorFactoryProvider.obterFactory(arquivoEscolhido.getName());
            LeitorDocumento leitor = leitorFactory.criarLeitor();
            String conteudo = leitor.ler(arquivoEscolhido);

            if (conteudo.isBlank()) {
                throw new IllegalArgumentException("O arquivo selecionado está vazio!");
            }

            System.out.println("Escolha o tipo de arquivo para gerar (PDF, Excel, Word):");
            String tipoArquivo = scanner.nextLine();

            GeradorDocumentoFactory geradorFactory;
            if (tipoArquivo.equalsIgnoreCase("PDF")) {
                geradorFactory = new GeradorPDFFactory();
            } else if (tipoArquivo.equalsIgnoreCase("Word")) {
                geradorFactory = new GeradorWordFactory();
            } else if (tipoArquivo.equalsIgnoreCase("Excel")) {
                geradorFactory = new GeradorExcelFactory();
            } else {
                throw new IllegalArgumentException("Tipo de argumento inválido!");
            }

            // Nome padrão = mesmo nome do arquivo de entrada, sem extensão
            String nomeBase = removerExtensao(arquivoEscolhido.getName());
            System.out.println("Digite o nome do arquivo de saída (Enter para usar \"" + nomeBase + "\"):");
            String nomeArquivo = scanner.nextLine();
            if (nomeArquivo.isBlank()) {
                nomeArquivo = nomeBase;
            }

            String caminhoSaida = PASTA_SAIDA + File.separator + nomeArquivo;

            GeradorDocumento gerador = geradorFactory.criarGerador();
            gerador.gerar(conteudo, caminhoSaida);

        } catch (Exception e) {
            System.out.println("Erro ao gerar o documento: " + e.getMessage());
        }
    }

    private static File escolherArquivoDeEntrada(Scanner scanner) throws Exception {
        File pasta = new File(PASTA_ENTRADA);
        File[] arquivosBrutos = pasta.listFiles(File::isFile);

        if (arquivosBrutos == null || arquivosBrutos.length == 0) {
            throw new IllegalStateException(
                "Nenhum arquivo encontrado na pasta \"" + PASTA_ENTRADA + "\". " +
                "Coloque o arquivo que deseja converter (PDF, DOCX, XLSX, TXT ou CSV) nessa pasta e rode o programa novamente."
            );
        }

        List<File> arquivos = Arrays.asList(arquivosBrutos);
        arquivos.sort(Comparator.comparing(File::getName));

        if (arquivos.size() == 1) {
            File unico = arquivos.get(0);
            System.out.println("Arquivo encontrado na pasta \"" + PASTA_ENTRADA + "\": " + unico.getName());
            return unico;
        }

        System.out.println("Arquivos encontrados na pasta \"" + PASTA_ENTRADA + "\":");
        for (int i = 0; i < arquivos.size(); i++) {
            System.out.println((i + 1) + " - " + arquivos.get(i).getName());
        }
        System.out.println("Digite o número do arquivo que deseja converter:");

        int escolha = Integer.parseInt(scanner.nextLine().trim());
        if (escolha < 1 || escolha > arquivos.size()) {
            throw new IllegalArgumentException("Opção inválida.");
        }

        return arquivos.get(escolha - 1);
    }

    private static String removerExtensao(String nomeArquivo) {
        int ponto = nomeArquivo.lastIndexOf('.');
        return ponto > 0 ? nomeArquivo.substring(0, ponto) : nomeArquivo;
    }
}
