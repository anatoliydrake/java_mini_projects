package com.anatoliydrake.DataCollector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesScanner {
    private final List<File> files;
    private final String[] extensions = {".json", ".csv"};

    public FilesScanner() {
        this.files = new ArrayList<>();
    }

    private void findFiles(String path) {
        File file = new File(path);
        if (file.isFile()) {
            for (String extension : extensions) {
                if (file.getName().contains(extension)) {
                    this.files.add(file);
                }
            }
            return;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                findFiles(f.getPath());
            }
        }
    }

    public List<File> getFiles(String path) {
        findFiles(path);
        return files;
    }
}
