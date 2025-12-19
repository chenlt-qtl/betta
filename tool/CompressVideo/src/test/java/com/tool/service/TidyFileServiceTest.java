package com.tool.service;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TidyFileServiceTest {


    @Test
    public void tidyFile_WithSubdirectory_ProcessesSubdirectory() throws IOException {
        TidyFileService tidyFileService = new TidyFileService();
        String path = "H:\\图片";
        File sourceDir = new File(path);
        tidyFileService.tidyFileByDate(sourceDir,path+"after" );
    }

    @Test
    public void testTidyFileByName() throws IOException {
        TidyFileService tidyFileService = new TidyFileService();
        String path = "F:\\1";
        File sourceDir = new File(path);
        tidyFileService.tidyFileByName(sourceDir,"G:\\after" );
    }

    @Test
    public void test(){
        System.out.println("IMG_7880.JPG".matches("^[\\w\\W]+\\.(?i)(jpg|png|mp4|avi)$"));
        int index = "IMG_7880.JPG".lastIndexOf(".");

        System.out.println("IMG_7880.JPG".substring(0,index));
        System.out.println("IMG_7880.JPG".substring(index));
    }
}
