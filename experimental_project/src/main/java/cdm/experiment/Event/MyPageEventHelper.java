package cdm.experiment.Event;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class MyPageEventHelper extends PdfPageEventHelper {
    private final Image backgroundImage;
    private boolean newPageStarted = true;

    public MyPageEventHelper(String imagePath) throws IOException, DocumentException {
        backgroundImage = Image.getInstance(imagePath);
        backgroundImage.setAbsolutePosition(0, 0);
        backgroundImage.scaleAbsolute(PageSize.A4.rotate());
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte canvas = writer.getDirectContentUnder();
        try {
            canvas.addImage(backgroundImage);
        } catch (DocumentException e) {
            e.printStackTrace();
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