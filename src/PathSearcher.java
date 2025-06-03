
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class PathSearcher {
    File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
    String base = desktopDir.getAbsolutePath();
    private String thePath;

    public PathSearcher(String base, String name, boolean isDirectory) {
        if (isDirectory){
            searchFolder(base, name);
        } else {
            searchImage(base, name);
        }
    }

    public void searchFolder(String directory, String folderName) {
        if (directory.isEmpty()){
            directory = base;
        }
        File dir = new File(directory);
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String name = file.getName();
                if (file.isDirectory()) {
                    if (name.equals(folderName)){
                        thePath = file + "\\";
                    }
                    searchFolder(file.getAbsolutePath(), folderName);
                }
            }
        } else {
            System.out.println("Invalid directory");
        }
    }

    public void searchImage(String directory, String fileName) {
        if (directory.isEmpty()){
            directory = base;
        }
        File dir = new File(directory);
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                String name = file.getName();
                if (file.isDirectory()) {
                    searchImage(file.getAbsolutePath(), fileName);
                } else if ((name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"))){
                    String imageName = name.replaceAll("\\.png|\\.jpeg|\\.jpg", "");
                    if (fileName.equals(imageName)){
                        thePath = file.getAbsolutePath();
                    }
                }
            }
        } else {
            System.out.println("Invalid directory");
        }
    }

    public String getThePath(){
        return thePath;
    }

}

