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
    }
}
