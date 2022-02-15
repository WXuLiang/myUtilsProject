package com.example.myutilsproject.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    /**
     * 复制输入流
     * 当需要判断一个流的编码格式时，需要复制一个流出来用户判断格式，另一个用于转为数据。
     * @param input 输入流
     * @return 两个输入流
     */
    public static List<InputStream> cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
            return new ArrayList<InputStream>() {{
                add(stream1);
                add(stream2);
            }};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
