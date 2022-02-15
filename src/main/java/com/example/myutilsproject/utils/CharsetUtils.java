package com.example.myutilsproject.utils;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import info.monitorenter.cpdetector.io.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CharsetUtils {

    /**
     * 获取文件的编码格式
     * @param file 文件
     * @return 编码格式名称
     */
    public static String getCharsetName(File file) {
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        try {
            charset = detector.detectCodepage(file.toURI().toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String charsetName = null;
        if (charset != null) {
            charsetName = charset.name();
        } else {
            charsetName = "UTF-8";
        }
        return charsetName;
    }

    /**
     * 获取
     * @param inputStream
     * @return
     */
    public static String getCharsetName(InputStream inputStream) {
        CharsetDetector charsetDetector = new CharsetDetector();
        try {
            charsetDetector.setText(new BufferedInputStream(inputStream));
            charsetDetector.enableInputFilter(true);
            CharsetMatch cm = charsetDetector.detect();
            return cm.getName();
        } catch (IOException e) {
            return null;
        }
    }
}
