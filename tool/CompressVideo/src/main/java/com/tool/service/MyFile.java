package com.tool.service;


public class MyFile {
    private String fileName;
    private String filePath;
    private int fileSize;

    private String date;

    public MyFile(String fileName, String filePath, int fileSize,String date) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getFileSize() {
        return fileSize;
    }
}
