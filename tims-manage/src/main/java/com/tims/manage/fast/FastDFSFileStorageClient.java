//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tims.manage.fast;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.exception.FdfsUnsupportImageTypeException;
import com.github.tobato.fastdfs.exception.FdfsUploadImageException;
import com.github.tobato.fastdfs.proto.storage.StorageSetMetadataCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadFileCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadSlaveFileCommand;
import com.github.tobato.fastdfs.proto.storage.enums.StorageMetdataSetType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

@Component
public class FastDFSFileStorageClient extends DefaultGenerateStorageClient{
    private static final String[] SUPPORT_IMAGE_TYPE = new String[]{"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"};
    private static final List<String> SUPPORT_IMAGE_LIST;
    @Resource
    private ThumbImageConfig thumbImageConfig;

    public FastDFSFileStorageClient() {
    }


    public StorePath[] uploadImageAndCrtThumbImage(String groupName,InputStream inputStream, long fileSize, String fileExtName, Set<MateData> metaDataSet) {
        Validate.notNull(inputStream, "上传文件流不能为空", new Object[0]);
        Validate.notBlank(fileExtName, "文件扩展名不能为空", new Object[0]);
        if (!this.isSupportImage(fileExtName)) {
            throw new FdfsUnsupportImageTypeException("不支持的图片格式" + fileExtName);
        } else {
            StorePath[] storePaths=new StorePath[2];
            StorageNode client = this.trackerClient.getStoreStorage(groupName);
            byte[] bytes = this.inputStreamToByte(inputStream);
            StorePath path = this.uploadFileAndMateData(client, new ByteArrayInputStream(bytes), fileSize, fileExtName, metaDataSet);
            StorePath thumbPath =this.uploadThumbImage(client, new ByteArrayInputStream(bytes), path.getPath(), fileExtName);
            storePaths[0]=path;
            storePaths[1]=thumbPath;
            return storePaths;
        }
    }

    private byte[] inputStreamToByte(InputStream inputStream) {
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException var3) {
            LOGGER.error("image inputStream to byte error", var3);
            throw new FdfsUploadImageException("upload ThumbImage error", var3.getCause());
        }
    }

    private boolean hasMateData(Set<MateData> metaDataSet) {
        return null != metaDataSet && !metaDataSet.isEmpty();
    }

    private boolean isSupportImage(String fileExtName) {
        return SUPPORT_IMAGE_LIST.contains(fileExtName.toUpperCase());
    }

    private StorePath uploadFileAndMateData(StorageNode client, InputStream inputStream, long fileSize, String fileExtName, Set<MateData> metaDataSet) {
        StorageUploadFileCommand command = new StorageUploadFileCommand(client.getStoreIndex(), inputStream, fileExtName, fileSize, false);
        StorePath path = (StorePath)this.connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
        if (this.hasMateData(metaDataSet)) {
            StorageSetMetadataCommand setMDCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(), metaDataSet, StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
            this.connectionManager.executeFdfsCmd(client.getInetSocketAddress(), setMDCommand);
        }

        return path;
    }

    private StorePath  uploadThumbImage(StorageNode client, InputStream inputStream, String masterFilename, String fileExtName) {
        ByteArrayInputStream thumbImageStream = null;

        try {
            thumbImageStream = this.getThumbImageStream(inputStream);
            long fileSize = (long)thumbImageStream.available();
            String prefixName = this.thumbImageConfig.getPrefixName();
            StorageUploadSlaveFileCommand command = new StorageUploadSlaveFileCommand(thumbImageStream, fileSize, masterFilename, prefixName, fileExtName);
            StorePath thumbPath = (StorePath)this.connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
            return thumbPath;
        } catch (IOException var13) {
            LOGGER.error("upload ThumbImage error", var13);
            throw new FdfsUploadImageException("upload ThumbImage error", var13.getCause());
        } finally {
            IOUtils.closeQuietly(thumbImageStream);
        }
    }

    private ByteArrayInputStream getThumbImageStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Thumbnails.of(new InputStream[]{inputStream}).size(this.thumbImageConfig.getWidth(), this.thumbImageConfig.getHeight()).toOutputStream(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    static {
        SUPPORT_IMAGE_LIST = Arrays.asList(SUPPORT_IMAGE_TYPE);
    }
}
