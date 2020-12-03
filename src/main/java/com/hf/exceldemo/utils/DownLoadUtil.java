package com.hf.exceldemo.utils;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 下载文件通用控制器
 */
public class DownLoadUtil {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

//    byte[]
//    通常在读取非文本文件时（如du图片，声音，可执行zhi文件）需要用字节数组dao保存文件的内容。
//    在下载文件时，也是用byte数组作临时的缓冲器接收文件内容

    public ResponseEntity<byte[]> downLoadExcel(InputStream is , String name) {
        logger.info("-----------------------------开始下载文件-----------------");
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("下载" + name);
        }
        HttpHeaders header = new HttpHeaders();
        //获得.+1 下标位
        int i = name.indexOf('.') + 1;
        //由下标位得到文件后缀 xml
        String substring = name.substring(i);
        //吧字符串  大写转化为小写
        String fileSuffix = substring.toLowerCase();

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        hashMap.put("xls", "application/vnd.ms-excel");
        //动态获得上传文件后缀名   的到value
        String contentType = hashMap.get(fileSuffix);

        ResponseEntity<byte[]> responseEntity = null;
        try {
            if (is != null && is.available() != 0) {
                header.add("Content-Type", (StringUtils.hasText(contentType) ? contentType : "application/x-download"));
                header.add("Content-Length", String.valueOf(is.available()));
                header.add("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(name, "UTF-8"));
                byte[] bs = IOUtils.toByteArray(is);
                logger.info(">>>>>>>>>>>>>>>>>>>>结束下载文件-有记录>>>>>>>>>>");
                logger.info(">>>>>>>>>>结束导出excel>>>>>>>>>>");
                responseEntity = new ResponseEntity<>(bs, header, HttpStatus.OK);
            } else {
                String string = "数据为空";
                header.add("Content-Length", "0");
                header.add("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + URLEncoder.encode(name, "UTF-8"));
                logger.info(">>>>>>>>>>>>>>>>>>>>结束下载文件-无记录>>>>>>>>>>");
                logger.info(">>>>>>>>>>结束导出excel>>>>>>>>>>");
                responseEntity = new ResponseEntity<>(string.getBytes(), header, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseEntity;
    }
}
