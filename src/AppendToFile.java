import java.io.*;

public class AppendToFile {

    public AppendToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();

                if (line == null){
                    writer.write(content);
                } else {
                    writer.newLine();
                    writer.write(content);
                }
            } catch (IOException e){
                System.err.println("Erro ao ler conteúdo do arquivo: " + e.getMessage());

            }

        } catch (IOException e) {
            System.err.println("Erro ao adicionar conteúdo ao arquivo: " + e.getMessage());
        }
    }
}