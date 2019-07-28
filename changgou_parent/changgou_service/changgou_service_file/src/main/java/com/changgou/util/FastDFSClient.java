package com.changgou.util;

import com.changgou.file.FastFDSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @作者:qipeiqing
 * @时间:2019/07/27 15:47
 * 实现FastDFS文件上传，下载等功能
 */
public class FastDFSClient {
    /** 文件上传，读取Tracker服务的配置
     */
    static {
        try {
            //获取fdfs_client.conf的路径，new ClassPathResource()获取类路径下的文件路径信息
            String path = new ClassPathResource("fdfs_client.conf").getPath();
            //加载读取fdfs_client.conf配置文件内容
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param:FastFDSFile:上传文件封装对象
     */
    public static String[] upload(FastFDSFile file) {
        String[] uploadResuls = null;
        try {
            //附加数据,附加了作者的信息
            NameValuePair[] meta_list = new NameValuePair[1];
            meta_list[0] = new NameValuePair("author", file.getAuthor());

            //创建一个TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();

            //通过TranckerClient对象链接TranckerClient
            TrackerServer trackerServer = trackerClient.getConnection();

            //通过TrackerServer来获取Storage信息，将Storage信息封装到一个StorageClient中
            StorageClient storageClient = new StorageClient(trackerServer, null);
            /**
             * 通过StorageClient操作Storage【文件上传】，并获取返回的文件上传信息
             * 参数1：要上传的文件的字节数组
             * 参数2：文件的扩展名
             * 参数3：附加的数据,文件作者等...
             * 返回数据： 1：当前文件所存储的Storage的组，例：group1  2：文件存储的路径 例：M00/02/44
             */
            uploadResuls = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResuls;
    }

    /**
     * 获取文件的信息
     *
     * @param groupName              ：组名
     * @param remoteFileName：文件存储的名字
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            //trackerserver获取
            TrackerServer trackerServer = getTrackerServer();

            //通过TranckerServer获取Storage信息，并且将信息存储到StorageClient中
            StorageClient storageClient = new StorageClient(trackerServer, null);

            //通过StorageClient获取图片信息
            return storageClient.get_file_info(groupName, remoteFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param groupName：组名
     * @param remoteFileName：文件储存的名字
     * @return
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) {
        try {
            //trackerserver获取
            TrackerServer trackerServer = getTrackerServer();

            //通过TrackerServer获取Storage信息，并将信息存储到StorageClient中
            StorageClient storageClient = new StorageClient(trackerServer, null);

            //通过StorageClient获取图片信息
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName：组名
     * @param remoteFileName：文件存储的名字
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        try {
            //trackerserver获取
            TrackerServer trackerServer = getTrackerServer();
            //通过TranckServer获取Storage信息,并且将信息存储到StorageClient中
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //通过StroageClient删除图片
            storageClient.delete_file(groupName, remoteFileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Storage信息
     *
     * @param groupName：stroage的组名
     * @return
     */
    public static StorageServer getStorage(String groupName) {
        try {
            //创建一个TrackerClient
            TrackerClient trackerClient = new TrackerClient();
            //用TrackerClient获取TranckerServer的链接信息
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Storage信息
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, groupName);
            return storeStorage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取stroage的端口信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) {
        try {
            //创建一个TrackerClient
            TrackerClient trackerClient = new TrackerClient();
            //用TrackerClient获取TrackerServer的链接信息
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Stroage信息
            return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Tracker服务地址
     *
     * @return
     */
    public static String getTrackerUrl() {
        try {
            //trackerserver获取
            TrackerServer trackerServer = getTrackerServer();
            //获取TrackerServer的Http请求地址
            return "http://" + trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * trackerServer获取
     */
    public static TrackerServer getTrackerServer() throws Exception {
        //创建一个TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //用TrackerClient获取TrackerServer的链接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

}
