package cdm.experiment.Event;

import cdm.experiment.utils.FontUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @name: pdfEvent
 * @user: luGuanYu
 * @date: 2024/8/27 下午2:56
 * @functionalExpression:
 */
public class PdfEvent extends PdfPageEventHelper {

    private final Image backgroundImage;
    private boolean newPageStarted = true;
    private String baseFont = "";

    public PdfEvent(String imagePath, String titleName) {
        try {
            this.backgroundImage = Image.getInstance(imagePath);
            this.baseFont = titleName;
        } catch (BadElementException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PdfEvent(String imagePath) {
        try {
            this.backgroundImage = Image.getInstance(imagePath);
        } catch (BadElementException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte canvas = writer.getDirectContentUnder();
        // 设置背景图片的位置和尺寸
        backgroundImage.setAbsolutePosition(0, 0);
        backgroundImage.scaleAbsolute(new Rectangle(960, 540));
        // 在底层画布上添加背景图片
        try {
            canvas.addImage(backgroundImage);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        footerStyle(writer, document, baseFont);

        newPageStarted = true;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        if (newPageStarted) {
            try {
                document.add(new Paragraph("\n\n")); // 添加两行空白，预留背景图片标题空间
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            newPageStarted = false;
        }
    }

    public static void footerStyle(PdfWriter writer, Document document, String companyName) {
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
                footer.writeSelectedRows(0, -1, document.leftMargin() + 20, document.bottomMargin() + 10, cb);
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
                footer.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin() + 22, cb);
            }
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
