package com.betta.web.controller.common;

import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.core.domain.AjaxResult;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.StringUtils;
import com.betta.common.utils.file.*;
import com.betta.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 保存笔记中的图片，会转成JPG（有损压缩）
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/uploadImg/note")
    public AjaxResult uploadNoteImg(@RequestBody Map param) {
        if (param.containsKey("file")) {

            String file = String.valueOf(param.get("file"));
            Matcher matcher = ImageUtils.BASE64_PATTERN.matcher(file);
            if (matcher.find()) {
                String data = matcher.group(0);//数据
                String type = matcher.group(1);//type
                String imgData = data.split(",")[1];

                String relativePath = Constants.NOTE_PATH + "/" + FileUtils.genDateFileName("jpg");//相对路径
                String absolutePath =  FileUtils.getAbsoluteProfilePath(relativePath);//绝对路径

                Base64Utils.saveBase64Image(imgData, type, absolutePath);
                //返回相对路径
                return AjaxResult.success("操作成功", BettaConfig.getVueResourcePath() + relativePath);
            }
        }
        return AjaxResult.error("没有找到图片信息");
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload/{type}")
    public AjaxResult uploadFile(MultipartFile file, @PathVariable String type) throws Exception {
        try {


            // 上传并返回相对路径
            String relativePath = FileUploadUtils.upload(type, file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", relativePath);
            ajax.put("fileName", FileUtils.getFileName(relativePath));
            ajax.put("newFileName", FileUtils.getFileName(relativePath));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/upload/url/{type}")
    public AjaxResult uploadFile(String url, @PathVariable String type) throws Exception {
        try {
            // 上传并返回相对路径
            String relativePath = FileUploadUtils.upload(type, url);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", relativePath);
            ajax.put("fileName", FileUtils.getFileName(url));
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
        try {
            // 上传文件路径
            String filePath = Constants.UPLOAD_PATH;
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files) {
                // 上传并返回相对路径
                String relativePath = FileUploadUtils.upload(filePath, file);
                String url = Constants.RESOURCE_PREFIX+relativePath;
                urls.add(url);
                fileNames.add(relativePath);
                newFileNames.add(FileUtils.getFileName(relativePath));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            DownloadUtils.checkAllowDownload(fileName);//检查是否允许下载
            // 数据库资源地址
            String downloadPath = BettaConfig.getProfile() + StringUtils.substringAfter(fileName, Constants.RESOURCE_PREFIX);
            DownloadUtils.downloadFile(downloadPath, response);
        } catch (Exception e) {
            throw new ServiceException("下载文件失败");
        }
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest
            request) {
        try {
            DownloadUtils.checkAllowDownload(fileName);//检查是否允许下载
            String filePath = Constants.DOWNLOAD_PATH + fileName;
            DownloadUtils.downloadFile(filePath, response);
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            throw new ServiceException("下载文件失败");
        }
    }
}
