import cdm.experiment.Event.MyPageEventHelper;
import cdm.experiment.Event.PdfEvent;
import cdm.experiment.utils.FontUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @name: PDFtest
 * @user: luGuanYu
 * @date: 2024/8/27 上午11:47
 * @functionalExpression:
 */
public class PDFText {

    private static final Logger log = LoggerFactory.getLogger(PDFText.class);

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 背景图片地址
        String imagePath = "C:/ruoyi/uploadPath/ls2/";

        // 创建横向 A4 页面
        Document document = new Document(PageSize.A4.rotate());

        // 使用 ByteArrayOutputStream 作为输出流
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             FileOutputStream fileOutputStream = new FileOutputStream("D:/output.pdf")) {

            // 创建 PdfWriter 实例
            PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            // 添加页面
            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "客户洞察报告_test.png"));
            footerStyle(writer, document,null);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "客户洞察报告_test223.png"));
            document.add(new Paragraph("Page 1"));
            footerStyle(writer, document,"测试");

            // 添加第二页
            document.newPage();
            document.add(new Paragraph("Page 2"));
            footerStyle(writer, document,"测试");
            // 关闭文档
            document.close();

            // 将 ByteArrayOutputStream 内容写入本地文件
            byteArrayOutputStream.writeTo(fileOutputStream);

        } catch (DocumentException | IOException e) {
            log.error("功能异常");
        }
    }


    public static void footerStyle(PdfWriter writer, Document document,String companyName) {
        try {
            PdfContentByte cb = writer.getDirectContent();

            // 获取当前的日期和时间
            LocalDate now = LocalDate.now();
            // 格式化当前的日期和时间
            String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));

            if (StringUtils.isNotEmpty(companyName)) {
                // 创建包含两列的表格
                PdfPTable footer = new PdfPTable(2);
                footer.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 30); // 设置表格总宽度

                // 添加单元格到表格
                PdfPCell cell = new PdfPCell(new Paragraph(companyName, FontUtils.draw_1()));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                footer.addCell(cell);

                cell = new PdfPCell(new Paragraph(formattedNow, FontUtils.draw_1()));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                footer.addCell(cell);

                // 设置表格的位置
                // 使用页边距进行定位
                footer.writeSelectedRows(0, -1, document.leftMargin()+20, document.bottomMargin() + 10, cb);
            } else {
                // 创建包含两列的表格
                PdfPTable footer = new PdfPTable(1);
                footer.setWidthPercentage(80); // 设置表格宽度为页面宽度的 100%
                footer.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()); // 设置表格总宽度
                // 添加单元格到表格
                PdfPCell cell = new PdfPCell(new Paragraph(formattedNow, FontUtils.draw()));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                footer.addCell(cell);
                // 设置表格的位置
                // 使用页边距进行定位
                footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() + 30, cb);
            }
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
