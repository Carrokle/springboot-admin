package com.lb.util;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageUtil {
    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     * @return
     * @Description: 将base64编码字符串转换为图片
     * @Author:
     * @CreateTime:
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);  // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
     */
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = readInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 从网上获取图片信息
     *
     * @param url
     * @param imageFile
     * @throws IOException
     */
    public static void getImageFromUrl(String url, String imageFile) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5 * 1000);
        InputStream inputStream = connection.getInputStream();

        byte[] data = readInputStream(inputStream);
        File image = new File(imageFile);

        FileOutputStream outputStream = new FileOutputStream(image);

        outputStream.write(data);
        outputStream.close();
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws IOException {
    /*    String strImg = getImageStr("C:\\Users\\lleid\\Pictures\\Saved Pictures\\01.jpg");
        String path = "F:/test/test/test/1.jpeg";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        System.out.println(strImg);
        generateImage(strImg, path);*/

        getImageFromUrl("http://27.115.105.145:10016/20180306/SEAL/4f086acf-d379-4a5a-843e-b41b9e74e293.BMP", "E:\\test.bmp");

    }


    public static void transferAlpha(String old_file_path, String new_file_path) {

        File file = new File(old_file_path);
        InputStream is;
        try {
            is = new FileInputStream(file);
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            BufferedImage bi = ImageIO.read(is);
            Image image = (Image) bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);

                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }

                    bufferedImage.setRGB(j2, j1, rgb);

                }
            }

            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
            String file_name = new_file_path;
            ImageIO.write(bufferedImage, "png", new File(file_name));// 直接输出文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }



    /**
     * 设置透明且将内容替换为红色
     * @param srcPath 原图片途径
     * @param desPath 生成图片路径
     */
    public static void transferAlphaRed(String srcPath, String desPath, String formatName)  {

        File file = new File(srcPath);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            BufferedImage bi = transferAlphaRed(in);
            ImageIO.write(bi,formatName,new File(desPath));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 去除图片背景，使其透明，内容变红,生成图片格式为png
     * @param inputStream 图片输入流
     * @throws IOException
     */
    public static BufferedImage transferAlphaRed(InputStream inputStream) throws IOException {
        BufferedImage bi = ImageIO.read(inputStream);
        return transferAlphaRed(bi);
    }


    /**
     * 去除图片背景，使其透明，并且将内容转为红色，生成图片格式为png
     * @param src
     * @return
     */
    public static BufferedImage transferAlphaRed(BufferedImage src) {
        ImageIcon imageIcon = new ImageIcon(src);
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());

        // 红色
        int red = new Color(255,0,0).getRGB();
        for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                int rgb = bufferedImage.getRGB(j2, j1);

                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0xff00) >> 8;
                int B = (rgb & 0xff);
                if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                    // rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    // 设置透明
                    bufferedImage.setRGB(j2,j1,rgb & 0x00ffffff);
                }else{
                    // 改为红色
                    bufferedImage.setRGB(j2, j1, red);
                }


            }
        }
        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
        return bufferedImage;
    }

    /**
     * 将文件压缩到指定存储空间大小一下
     * @param bi
     * @param size 单位：kb,指定需要压缩的大小
     * @param scale 每次压缩的比例
     * @param quality 每次压缩的质量
     * @return BufferedImage
     */
    public static BufferedImage compressToSize(BufferedImage bi, int size, double scale, double quality) throws IOException {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        ImageIO.write(bi,"png",o);
        if(o.size() < size*1024){
            o.close();
            return bi;
        }else{
            o.close();
            BufferedImage bufferedImage = Thumbnails.of(bi)
                    .scale(scale)
                    .outputQuality(quality)
                    .outputFormat("png")
                    .asBufferedImage();
            return compressToSize(bufferedImage,size,scale,quality);
        }
    }

    /**
     * 将byte[]转为文件
     * @param bfile
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    public static void byteToFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
