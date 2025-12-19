package com.compressVideo.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.util.Date;

public class PhotoUtils {

    /**
     * 获取照片的拍摄时间
     */
    public static Date getPhotoDate(File imageFile){
        Date date = null;
        try {
            // 读取照片的EXIF信息
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

            // 获取ExifSubIFDDirectory，它包含了照片的EXIF信息
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            // 从ExifSubIFDDirectory中提取拍摄时间
            date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 如果找到了拍摄时间信息，则进行格式化并输出
        if (date == null) {
            System.out.println("照片："+imageFile.getAbsolutePath()+" 没有找到拍摄时间信息。");
        }
        return date;
    }

    public static Date getPhotoDate(String path){
        File imageFile = new File(path);
        return getPhotoDate(imageFile);
    }
}
