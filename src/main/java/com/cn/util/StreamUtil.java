package com.cn.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

/**
 * Created by wuchunhua on 2016/1/21.
 */
public class StreamUtil {


    public static String file2String(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            } else {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            //将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        //返回转换结果
        if (writer != null)
            return writer.toString();
        else return null;
    }

    public static byte[] uncompress(byte[] compressBytes) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressBytes);
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);

            byte[] buf = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buf)) > 0) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byte[] uncompressBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            gzipInputStream.close();
            byteArrayInputStream.close();
            return uncompressBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String inputStream2String(InputStream is) {
        try {
            if (null == is) {
                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i = -1;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
            String ret = baos.toString();
            baos.flush();
            baos.close();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readInputStream2Bytes(InputStream inputStream) {
        try {
            if (null == inputStream) {
                return null;
            }
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] ret = outputStream.toByteArray();

            outputStream.flush();
            outputStream.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream bytes2InputStream(byte[] buf) {
        try {
            if (null == buf) {
                return null;
            }
            InputStream sbs = new ByteArrayInputStream(buf);
            return sbs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static InputStream[] copyInputStream(InputStream origin, int outLen) {
        try {
            InputStream[] outISs = new InputStream[outLen];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = origin.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            for (int i = 0; i < outLen; i++) {
                outISs[i] = new ByteArrayInputStream(baos.toByteArray());
            }

            baos.close();
            return outISs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
