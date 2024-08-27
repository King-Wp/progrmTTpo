package cdm.experiment.Event;

import cdm.experiment.utils.FontUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.SneakyThrows;

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
        backgroundImage.scaleAbsolute(PageSize.A4.rotate());
        // 在底层画布上添加背景图片
        try {
            canvas.addImage(backgroundImage);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
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
}
