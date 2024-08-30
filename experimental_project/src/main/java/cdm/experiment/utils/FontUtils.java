package cdm.experiment.utils;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;

/**
 * @name: FontUtils
 * @user: luGuanYu
 * @date: 2024/8/27 下午3:49
 * @functionalExpression:
 */
public class FontUtils {

    private static final String fontPath = "C:/Windows/Fonts/MSYH.ttc,1";

    public static Font draw() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        font.setColor(BaseColor.WHITE);
        return font;
    }

    public static Font draw_black() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.NORMAL);
        font.setColor(BaseColor.BLACK);
        return font;
    }

    public static Font draw_black_bold() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 18, Font.BOLD);
        font.setColor(BaseColor.BLACK);
        return font;
    }

    public static Font draw_bold() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12, Font.BOLD);
        font.setColor(BaseColor.WHITE);
        return font;
    }

    public static Font draw_1() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(baseFont, 10, Font.NORMAL);
    }

    public static Font topHeading() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(baseFont, 18, Font.NORMAL);
    }

    public static Font hyperlinks() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont);
        font.setSize(13);
        font.setColor(new BaseColor(62, 107, 184));
        return font;
    }

}
