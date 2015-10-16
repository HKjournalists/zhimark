package com.spring.mvc.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 二维码生成工具类
 * Created by liluoqi on 15/8/29.
 */
public class QRCodeGenerator {

    //私有不可更改的变量：生成二维码图片的颜色
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static Logger logger = Logger.getLogger(QRCodeGenerator.class);

    /**
     * 静态方法
     * BufferedImage是Image的一个子类，BufferedImage生成的图片在内存里有一个图像缓冲区，利用这个缓冲区我们可以很方便的操作这个图片，
     * 通常用来做图片修改操作如大小变换、图片变灰、设置图片透明或不透明等。
     *
     * @param matrix 编码形式
     * @return
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        //图片的宽度和高度
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //BufferedImage.TYPE_INT_RGB将图片变为什么颜色
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 静态方法 用于生成图片
     *
     * @param matrix 编码形式
     * @param format 图片类型
     * @param file   文件（图片路径，图片名称）
     * @throws IOException
     */
    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /**
     * 输出
     * 流文件可以网络传输
     *
     * @param matrix
     * @param format
     * @param stream
     * @throws IOException
     */
    private static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 生成文件夹
     * @param fileName 文件夹路径
     * @return 返回生成为文件夹文件
     */
    private static File createDirectory(String fileName) {
        File imageDir = new File(fileName);
        if (!imageDir.exists() && !imageDir.isDirectory() && imageDir.mkdirs()) {
            return imageDir;
        }
        return null;
    }

    /**
     * 生成二维码图片
     * @param content 二维码包含的内容
     * @param path 二维码存放的路径
     * @param height 二维码的高度
     * @param width 二维码的宽度
     */
    public static void generateQRCodeImage(String content, String path, Integer height, Integer width) {
        try {
            logger.info(String.format("您要生成二维码的信息是:%s,二维码的输出目录是:%s", content, path));
            MultiFormatWriter multiFormatWrite = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 按照指定的宽度，高度和附加参数对字符串进行编码
            //生成二维码
            BitMatrix bitMatrix = multiFormatWrite.encode(content, BarcodeFormat.QR_CODE, width == null ? WIDTH : width,
                    height == null ? HEIGHT : height, hints);
            File file = new File(path, String.format("%s.jpg", RandomUtils.getSevenRandomNumbers()));
            // 写入文件
            writeToFile(bitMatrix, "jpg", file);
            logger.info("二维码图片生成成功!");
        } catch (Exception e) {
            logger.error(String.format("生成二维码异常:%s", e.getMessage()), e);
        }
    }

    public static void generateQRCodeImage(String content, String path) {
        try {
            logger.info(String.format("您要生成二维码的信息是:%s,二维码的输出目录是:%s", content, path));
            MultiFormatWriter multiFormatWrite = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 按照指定的宽度，高度和附加参数对字符串进行编码
            //生成二维码
            BitMatrix bitMatrix = multiFormatWrite.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            File file = new File(path, String.format("%s.jpg", RandomUtils.getSevenRandomNumbers()));
            // 写入文件
            writeToFile(bitMatrix, "jpg", file);
            logger.info("二维码图片生成成功!");
        } catch (Exception e) {
            logger.error(String.format("生成二维码异常:%s", e.getMessage()), e);
        }
    }

    public static void generateQRCodeImage(String content) {
        try {
            logger.info(String.format("您要生成二维码的信息是:%s,二维码的输出目录是当前目录", content));
            MultiFormatWriter multiFormatWrite = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 按照指定的宽度，高度和附加参数对字符串进行编码
            //生成二维码
            File imageDir = createDirectory("QAImages");
            BitMatrix bitMatrix = multiFormatWrite.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            File file = new File(imageDir, String.format("%s.jpg", RandomUtils.getSevenRandomNumbers()));
            // 写入文件
            writeToFile(bitMatrix, "jpg", file);
            logger.info("二维码图片生成成功!");
        } catch (Exception e) {
            logger.error(String.format("生成二维码异常:%s", e.getMessage()), e);
        }
    }

    public static void main(String[] args) {
        String content = "http://www.baidu.com";
        String path = "/Users/liluoqi/Documents/QRCodeImages";
        generateQRCodeImage(content, path);
    }
}
