package com.betta.common.utils.file;

import com.betta.common.config.BettaConfig;
import com.betta.common.constant.Constants;
import com.betta.common.enums.UploadFileType;
import com.betta.common.exception.file.FileNameLengthLimitExceededException;
import com.betta.common.exception.file.FileSizeLimitExceededException;
import com.betta.common.exception.file.InvalidExtensionException;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.StringUtils;
import com.betta.common.utils.uuid.Seq;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = BettaConfig.getProfile();

    public static void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException {
        try {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件类型上传
     *
     * @param type 文件类型
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String type, MultipartFile file) throws IOException {
        // 上传文件文件夹
        String dir = FileUploadUtils.getDir(type);
        try {
            return upload(dir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传 返回API的资源路径
     *
     * @param prePath
     * @param file
     * @param allowedExtension
     * @return
     * @throws InvalidExtensionException
     * @throws IOException
     */
    public static final String upload(String prePath, MultipartFile file, String[] allowedExtension)
            throws InvalidExtensionException, IOException {

        assertAllowed(file, allowedExtension);

        String relativePath = prePath + File.separator + FileUtils.genDateFileName(getExtension(file));

        String absolutePath = FileUtils.getAbsoluteProfilePath(relativePath);
        file.transferTo(Paths.get(absolutePath));
        return Constants.RESOURCE_PREFIX + relativePath;
    }

    /**
     * 获取上传的文件夹
     *
     * @param type
     * @return
     */
    public static final String getDir(String type) {
        String dir = Constants.UPLOAD_PATH;
        if (StringUtils.equals(UploadFileType.WORD.getType(), type)) {
            dir = Constants.WORD_PATH;
        } else if (StringUtils.equals(UploadFileType.ARTICLE.getType(), type)) {
            dir = Constants.ARTICLE_PATH;
        } else if (StringUtils.equals(UploadFileType.NOTICE.getType(), type)) {
            dir = Constants.NOTICE_PATH;
        }
        return dir;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION) {
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }

    /**
     * 根据URL上传文件
     *
     * @param url
     * @return
     * @throws FileSizeLimitExceededException
     * @throws IOException
     * @throws FileNameLengthLimitExceededException
     * @throws InvalidExtensionException
     */
    public static final String upload(String type, String url)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException {
        String extension = "mp3";
        if (url.matches(".m4a")) {
            extension = "m4a";
        }

        String relativePath = getDir(type) + File.separator + FileUtils.genDateFileName(extension);
        FileUtils.writeBytes(url, relativePath);
        return Constants.RESOURCE_PREFIX + relativePath;
    }
}
