package org.example.readExel.open_file;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class GetPathFile {

    public String getPathFile(String extension, String defaultPath) {

        JFileChooser fileOpen = new JFileChooser();

        fileOpen.setPreferredSize(new Dimension(600, 500));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("files " + extension, extension);
        fileOpen.setFileFilter(filter);

        // open directory by default.
        fileOpen.setCurrentDirectory(new File(defaultPath));
        int ret = fileOpen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            return file.getAbsolutePath();
        }

        return null;
    }

}
