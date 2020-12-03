package com.hf.exceldemo.service.impl;

import com.hf.exceldemo.dao.UserMapper;
import com.hf.exceldemo.entity.User;
import com.hf.exceldemo.service.ExportService;
import com.hf.exceldemo.utils.DownLoadUtil;
import com.hf.exceldemo.utils.ExcelExportUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    Logger logger = LoggerFactory.getLogger(ExportService.class);
    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response) {
        logger.info("---------------------------------开始导出excel-----------------------------");
        try {
            List<User> usersList = userMapper.listUser();
                DownLoadUtil downLoadUtil = new DownLoadUtil();
                return downLoadUtil.downLoadExcel(export((usersList)), "导出用户表.xls");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("------------------导出excel异常，原因是"+e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param list
     * @return
     */
    public InputStream export(List<User> list) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("-----------------------开始进入导出方法——————————————");
        }
        ByteArrayOutputStream output = null;
        InputStream input=null;

        //保留1000条数据在内存中
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet();

        //设置报表头样式
        CellStyle header = ExcelExportUtil.headSytle(wb);     //设置报表头样式
        CellStyle content = ExcelExportUtil.contentStyle(wb); //报表体样式

        //表头字段名
        String [] strs={"姓名", "性别", "年龄"};

        // 字段名所在表格的宽度
        int[] ints = new int[] { 5000, 5000, 5000, 5000, 5000, 5000 };

        //设置表头样式
         ExcelExportUtil.initTitleEX((SXSSFSheet) sheet,header,strs,ints);

        logger.info(">>>>>>>>>>>>>>>>>>>>表头样式设置完成>>>>>>>>>>");

        if(list !=null && list.size()>0 ){
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                SXSSFRow row = (SXSSFRow) sheet.createRow(i + 1);
                int j = 0;

                SXSSFCell cell = (SXSSFCell) row.createCell(j++);
                cell.setCellValue(user.getName()); // 姓名
                cell.setCellStyle(content);

                cell = (SXSSFCell) row.createCell(j++);
                cell.setCellValue(user.getSex()); // 性别
                cell.setCellStyle(content);

                cell = (SXSSFCell) row.createCell(j++);
                cell.setCellValue(user.getAge()); // 年龄
                cell.setCellStyle(content);
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>结束遍历数据组装单元格内容>>>>>>>>>>>>>>>>>>>>");
        }
        try {
            output = new ByteArrayOutputStream();
            wb.write(output);
            input = new ByteArrayInputStream(output.toByteArray());
            //输出区也还是有数据的，就像水缸里有水，只是在缓冲区遗留了一部分，这时如果我们先调用flush()方法，就会强制把数据输出，缓存区就清空了
//            缓存区就清空了，最后再关闭读写流调用close()就完成了。
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (output != null) {
                        output.close();
                        if (input != null){
                            input.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return input;


    }
}