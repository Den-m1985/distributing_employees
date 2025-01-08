package org.example.readExel.open_file;

public class ChoosePath {

    public String choosePath(String extension, String defaultPath) {
        String pathXLS = new GetPathFile().getPathFile(extension, defaultPath);
        if (pathXLS == null) {
            throw new RuntimeException("No file");
        }
        return pathXLS;
    }

}
