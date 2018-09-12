package com.ynbwjf.myoauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Program: myoauth
 * @description: 初始化验证码，自动生成验证码
 * @Author: zrj
 * @Date: 2018-09-06 16:51
 */
public class VerificationCodeGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeGenerator.class);
    // 验证码图片的宽度。
    private static int width = 60;
    // 验证码图片的高度。
    private static int height = 20;
    // 验证码字符个数
    private static int codeCount = 4;
    private static int x = 0;
    // 字体高度
    private static int fontHeight;
    private static int codeY;
    static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',  'x', 'c', 'v',
            'b', 'n', 'm', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static void initxuan(){
        // 宽度
        String strWidth ="140";
        // 高度
        String strHeight ="28";
        // 字符个数
        String strCodeCount = "4";
        // 将配置的信息转换成数值
        try {
            if (strWidth != null && strWidth.length() != 0) {
                width = Integer.parseInt(strWidth);
            }
            if (strHeight != null && strHeight.length() != 0) {
                height = Integer.parseInt(strHeight);
            }
            if (strCodeCount != null && strCodeCount.length() != 0) {
                codeCount = Integer.parseInt(strCodeCount);
            }
        }
        catch (Exception e)
        {
            LOG.error("出错了....");
            LOG.error(e.toString());
        }
        x = width / (codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;
    }

    /**
     * 自动生成验证码
     * @param request
     * @param response
     */
    public static void creatVerificationCode(HttpServletRequest request, HttpServletResponse response){
        try {
            initxuan();
            BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            Graphics2D g = buffImg.createGraphics();
            // 创建一个随机数生成器类
            Random random = new Random();
            // 将图像填充为白色
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            // 创建字体，字体的大小应该根据图片的高度来定。
            Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
            // 设置字体。
            g.setFont(font);
            g.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < 150; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
            StringBuffer randomCode = new StringBuffer();
            int red = 0, green = 0, blue = 0;
            // 随机产生codeCount数字的验证码。
            for (int i = 0; i < codeCount; i++) {
                // 得到随机产生的验证码数字。
                String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
                // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);
                // 用随机产生的颜色将验证码绘制到图像中。
                g.setColor(new Color(red, green, blue));
                g.drawString(strRand, (i + 1) * x, codeY);
                // 将产生的四个随机数组合在一起。
                randomCode.append(strRand);
            }
            // 将四位数字的验证码保存到Session中。
            HttpSession session = request.getSession();
            session.setAttribute("verifyCode", randomCode.toString());
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            // 将图像输出到Servlet输出流中。
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("出错了....");
            LOG.error(e.toString());
        }

    }
}
