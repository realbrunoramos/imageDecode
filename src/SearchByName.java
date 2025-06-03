import java.io.File;
import java.util.ArrayList;

public class SearchByName {
    private static ArrayList<String> listPaths = new ArrayList<>();;
    public SearchByName(String name) {
        listPaths = new ArrayList<>();
        search(name);
    }
    private static boolean conditions(String name, String fileName){
        String nameU = name.replaceAll("\\.png|\\.jpg|\\.jpeg", "").toUpperCase();
        String fileNameU = fileName.replaceAll("\\.png|\\.jpg|\\.jpeg", "").toUpperCase();
        double low = Math.min(nameU.length(), fileNameU.length());
        double high = Math.max(nameU.length(), fileNameU.length());
        double ratio = (low / high);
        return (nameU.contains(fileNameU)) && ( ratio > 0.09) ;
    }
    private static void searchImage(String directory, String fileName) {
        File dir = new File(directory);
        File[] listFiles = dir.listFiles();

        if (listFiles != null) {
            for (File file : listFiles) {
                String name = file.getName();
                if (file.isDirectory()) {
                    searchImage(file.getAbsolutePath(), fileName);
                } else if ((name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"))){
                    if (conditions(name, fileName)){
                        listPaths.add(file.getAbsolutePath());

                    }
                }
            }
        } else {
            System.out.println("Invalid directory");
        }
    }
    private static ArrayList<String> search(String input){
        listPaths.clear();
        String desktopPath = System.getProperty("user.home") + "\\OneDrive\\Ambiente de Trabalho";
        searchImage(desktopPath, input);
        if (!listPaths.isEmpty()){
            return listPaths;
        }
        return null;
    }
    public static ArrayList<String> getListPaths() {
        return listPaths;
    }

}
