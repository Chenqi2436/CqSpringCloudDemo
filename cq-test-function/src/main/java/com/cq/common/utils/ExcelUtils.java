/**
 * ExcelUtils.java
 * Created at 2020-09-29
 * Created by ZhangWei
 * Copyright (C) 2020 SAIC VOLKSWAGEN, All rights reserved.
 */
package com.cq.common.utils;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.cq.common.web.RestResponse;
import com.cq.common.web.RestStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * <p>ClassName: ExcelUtils</p>
 * <p>Description: Excel 工具</p>
 * <p>Author: ZhangWei</p>
 * <p>Date: 2020-09-29</p>
 */
@Slf4j
public class ExcelUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * <p>Description: 读取指定行Excel</p>
     *
     * @param file:           Excel文件
     * @param headerMap:      表头
     * @param headerRowIndex: 表头所在行
     * @param startRowIndex:  数据起始行
     * @param endRowIndex:    数据终止行,不指定时传入null
     * @throws
     * @return: com.svw.asmpcloud.common.core.web.RestResponse<java.util.List < java.util.Map < java.lang.String, java.lang.Object>>>
     */
    public static RestResponse<List<Map<String, Object>>> readExcel(MultipartFile file, Map<String, String> headerMap, Integer headerRowIndex, Integer startRowIndex, Integer endRowIndex) {
//        if (!CheckUtil.isExcel(file)) {
//            return new RestResponse<>(RestStatusCode.BAD_REQUEST.code(), "文件不是标准的文件,请从服务器上下载标准的模版文件!");
//        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ExcelReader reader = ExcelUtil.getReader(inputStream);
            List<Object> objects = reader.readRow(headerRowIndex);
            //存在列名 则判断是否为标准文档导入
            if (headerMap != null && headerMap.size() > 0) {
                if (objects == null || objects.size() == 0) {
                    return new RestResponse<>(RestStatusCode.BAD_REQUEST.code(), "文件不是标准的文件,请从服务器上下载标准的模版文件!");
                } else {
                    //标题头长度不一致反馈异常
                    if (!(objects.size() == headerMap.size())) {
                        return new RestResponse<>(RestStatusCode.BAD_REQUEST.code(), "文件不是标准的文件,请从服务器上下载标准的模版文件!");
                    }
                    //标题不存在则也不是标准文档
                    for (int i = 0; i < headerMap.size(); i++) {
                        Object o = objects.get(i);
                        if (o == null || "".equals(o) || headerMap.get(o) == null) {
                            return new RestResponse<>(RestStatusCode.BAD_REQUEST.code(), "文件不是标准的文件,请从服务器上下载标准的模版文件!");
                        }
                    }
                }
                reader.setHeaderAlias(headerMap);
            }
            int endRow = endRowIndex == null ? reader.getRowCount() : endRowIndex;
            List<Map<String, Object>> list = reader.read(headerRowIndex, startRowIndex, endRow);
            return new RestResponse<>(RestStatusCode.OK, list);
        } catch (IOException e) {
            LOGGER.error("----------读取Excel异常：", e);
            return new RestResponse<>(RestStatusCode.SERVER_UNKNOWN_ERROR);
        } finally {
            IoUtil.close(inputStream);
        }
    }

}
