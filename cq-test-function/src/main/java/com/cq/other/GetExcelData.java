package com.cq.other;

import org.apache.commons.compress.utils.IOUtils;
import com.cq.common.utils.ExcelUtils;
import com.cq.common.web.RestResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Jinghao
 * @date 2023/2/1018:01
 */
public class GetExcelData {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\Cjh\\Desktop\\0207.xlsx");
        MultipartFile file = getMultipartFile(f);
        RestResponse<List<Map<String, Object>>> listRestResponse = ExcelUtils.readExcel(file, getList(), 0, 2, null);
        System.out.println(listRestResponse);
    }

    public static Map<String,String> getList(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("一级菜单","oneFloor");
        map.put("二级菜单","teoFloor");

        map.put("三级菜单","threeFloor");

        map.put("售前分配岗位","afterSite");

        map.put("测试环境AUDI_URL","testEnvro");

        map.put("生产环境AUDI_URL","prudEnvro");
        return map;

    }


    public static MultipartFile getMultipartFile(File file) {
        FileItem item = new DiskFileItemFactory().createItem("file"
                , MediaType.MULTIPART_FORM_DATA_VALUE
                , true
                , file.getName());
        try (InputStream input = new FileInputStream(file);
             OutputStream os = item.getOutputStream()) {
            // 流转移
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        return new CommonsMultipartFile(item);
    }
}

