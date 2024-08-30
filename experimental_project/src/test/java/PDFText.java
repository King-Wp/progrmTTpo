import cdm.experiment.Event.PdfEvent;
import cdm.experiment.utils.FontUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Rectangle pptPageSize = new Rectangle(960, 540);
        // 创建横向 A4 页面
        Document document = new Document(pptPageSize);

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
            footerStyle(writer, document, null);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "工商信息.png","测试"));
            businessInformation(document);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "资质证书.png","测试"));
            qualifications(document);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "公司产品信息.png","测试"));
            List<String> objects = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                objects.add("公司名称");
            }

            companyProductInfo(document, new ArrayList<>(Arrays.asList("产品名称", "产品简称", "产品分类", "领域")),objects);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "客户.png","测试"));
            companyProductInfo(document, new ArrayList<>(Arrays.asList("客户", "销售金额", "报告期", "数据来源")),objects);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "公司人员结构.png","测试"));
            companyProductInfo(document, new ArrayList<>(Arrays.asList("职位", "姓名")),objects);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "采购统计.png","测试"));
            topHeading(writer, document, "中国电信集团有限公司共采购项目4项，合计采购金额25598.3万元，其中：");

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "客户近三年招标结果.png","测试"));
            biddingRanking(document);

            document.newPage();
            writer.setPageEvent(null);
            writer.setPageEvent(new PdfEvent(imagePath + "业绩库.png","测试"));
            List<String> objects2 = new ArrayList<>();
            for (int i = 0; i < 70; i++) {
                objects2.add("公司名称");
            }
            companyProductInfo(document, new ArrayList<>(Arrays.asList("客户单位全称", "合同名称", "合同编号", "金额","签订日期","所属公司","经办人")),objects2);

            // 关闭文档
            document.close();

            // 将 ByteArrayOutputStream 内容写入本地文件
            byteArrayOutputStream.writeTo(fileOutputStream);

        } catch (DocumentException | IOException e) {
            log.error("功能异常");
        }
    }

    /**
     * 工商信息
     *
     * @param document 页面对象
     */
    public static void businessInformation(Document document) {
        // 创建包含两列的表格
        PdfPTable info = new PdfPTable(6);
        info.setWidthPercentage(95);
        try {
            float[] columnWidths = {0.16f, 0.16f, 0.16f, 0.16f, 0.16f, 0.16f};
            info.setWidths(columnWidths);
            redType(info, "法定代表人或负责人");
            typeParameter(info, "周可璋", 0);
            redType(info, "经营状态");
            typeParameter(info, "存续", 0);
            redType(info, "工商注册号");
            typeParameter(info, "J00000065107", 0);

            redType(info, "统一社会信用代码");
            typeParameter(info, "9131000063126503X1", 0);
            redType(info, "成立日期");
            typeParameter(info, "2024-08-28", 0);
            redType(info, "组织机构代码");
            typeParameter(info, "3126503-X", 0);

            redType(info, "营业期限");
            typeParameter(info, "9131000063126503X1", 0);
            redType(info, "注册资本");
            typeParameter(info, "2024-08-28", 0);
            redType(info, "核准日期");
            typeParameter(info, "3126503-X", 0);

            redType(info, "企业类型");
            typeParameter(info, "有限责任公司（非自然人投资或控股的法人独资）", 0);
            redType(info, "实缴资本");
            typeParameter(info, "2024-08-28", 0);
            redType(info, "人员规模");
            typeParameter(info, "3126503-X", 0);

            redType(info, "纳税人识别号");
            typeParameter(info, "9131000063126503X1", 0);
            redType(info, "纳税人资质");
            typeParameter(info, "2024-08-28", 0);
            redType(info, "行业");
            typeParameter(info, "3126503-X", 0);

            redType(info, "参保人数");
            typeParameter(info, "190", 0);
            redType(info, "登记机关");
            typeParameter(info, "上海市市场监督管理局", 3);

            redType(info, "英文名称");
            typeParameter(info, "-", 0);
            redType(info, "登记机关");
            typeParameter(info, "China Construction Ei Engineeringtd.Division", 3);

            redType(info, "注册地址");
            typeParameter(info, "中国(上海)自由贸易试验区世纪大道1568号27层", 5);
            redType(info, "经营范围");
            typeParameter(info, "房屋建筑、公路、铁路、市政公用、港口与航道、水利水电各类别工程的咨询、设计、施工、总承包和项目管理，化工石油工程，电力" +
                    "工程，基础工程，装饰工程，工业筑炉，城市轨道交通工程，园林绿化工程，线路、管道、设备的安装，混凝土预制构件及制品，非标" +
                    "制作，建筑材料生产、销售，建筑设备销售，建筑机械租赁，房地产开发，自有房屋租赁，物业管理，从事建筑领域内的技术转让、技" +
                    "术咨询、技术服务，企业管理咨询，商务信息咨询，经营各类商品和技术的进出口，但国家限定公司经营或禁止进出口的商品及技术除" +
                    "外。【依法须经批准的项目，经相关部门批准后方可开展经营活动】", 5);

            document.add(new Paragraph("\n\n\n"));
            document.add(info);
            document.add(new Paragraph("\n"));
            Paragraph paragraph = new Paragraph();
            paragraph.add(websiteRedirect("https://www.baidu.com/", "公司网站跳转"));

            PdfPTable info2 = new PdfPTable(1);
            info2.setWidthPercentage(95);
            PdfPCell cell = new PdfPCell(paragraph);
            cell.setNoWrap(false);
            cell.setBorder(0);
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            info2.addCell(cell);

            document.add(info2);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 资质证书
     *
     * @param document 页面对象
     */
    public static void qualifications(Document document) {
        PdfPTable info = new PdfPTable(5);
        info.setWidthPercentage(95);
        try {
            redTypeCenter(info, "发证日期");
            redTypeCenter(info, "证书类型");
            redTypeCenter(info, "产品名称及单元(主)");
            redTypeCenter(info, "证书编号");
            redTypeCenter(info, "截至日期");

            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");

            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");

            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");
            typeParameterCenter(info, "2023-08-28");

            document.add(new Paragraph("\n\n\n"));
            document.add(info);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 通用
     *
     * @param document 页面对象
     */
    public static <T> void companyProductInfo(Document document, List<String> label,List<T> t) {
        PdfPTable info = new PdfPTable(label.size());
        info.setWidthPercentage(95);
        try {
            for (String s : label) {
                redTypeCenter(info, s);
            }

            for (int i = 0; i < t.size();) {
                for (int i1 = 0; i1 < label.size(); i1++) {
                    typeParameterCenter(info, (String) t.get(i));
                    i++;
                }
            }

            document.add(info);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 客户近三年招标结果
     *
     * @param document 页面对象
     */
    public static void biddingRanking(Document document) {
        try {
            PdfPTable table = new PdfPTable(3);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER); // 设置单元格水平居中
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置单元格垂直居中
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // 去除边框线
            table.setWidthPercentage(100);
            table.setWidths(new float[]{0.6f, 0.1f, 0.6f});
            table.getDefaultCell().setFixedHeight(60);
            table.addCell(new Phrase("金额排行前十", FontUtils.draw_black_bold()));
            table.addCell(new Phrase(" ", FontUtils.draw_black()));
            table.addCell(new Phrase("中标数量前十", FontUtils.draw_black_bold()));

            PdfPTable content = new PdfPTable(5);
            content.getDefaultCell().setBorder(PdfPCell.NO_BORDER); // 去除边框线
            content.setWidthPercentage(100);
            content.setWidths(new float[]{0.3f, 0.3f, 0.1f,0.3f,0.3f,});
            content.getDefaultCell().setFixedHeight(30);
            redTypeCenterBold(content,"公司名称");
            redTypeCenterBold(content,"中标金额");
            content.addCell(new Phrase(" ", FontUtils.draw_black()));
            redTypeCenterBold(content,"公司名称");
            redTypeCenterBold(content,"中标金额");

            for (int i = 0; i < 10; i++) {
                typeParameterCenter(content, "中通服咨询设计研究院有限公司");
                typeParameterCenter(content, "21635.95(万元)");
                content.addCell(new Phrase(" ", FontUtils.draw_black()));
                typeParameterCenter(content, "中通服咨询设计研究院有限公司");
                typeParameterCenter(content, "21635.95(万元)");
            }

            document.add(table);
            document.add(content);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 页脚
     *
     * @param writer      pdf对象
     * @param document    页面对象
     * @param companyName 公司名称
     */
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

    /**
     * 标题
     *
     * @param writer    pdf对象
     * @param document  页面对象
     * @param titleName 标题名称
     */
    public static void topHeading(PdfWriter writer, Document document, String titleName) {
        try {
            PdfContentByte cb = writer.getDirectContent();
            // 创建包含两列的表格
            PdfPTable title = new PdfPTable(1);
            title.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 280); // 设置表格总宽度
            // 添加单元格到表格
            PdfPCell cell = new PdfPCell(new Paragraph(titleName, FontUtils.topHeading()));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            title.addCell(cell);
            // 设置表格的位置
            // 使用页边距进行定位
            title.writeSelectedRows(0, -1, document.leftMargin() + 124, document.top() + title.getTotalHeight() - 8, cb);

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 红色背景 白色字色 单元格
     *
     * @param table 表格对象
     * @param text  文本内容
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static void redType(PdfPTable table, String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(text, FontUtils.draw()));
        cell.setFixedHeight(30f);
        cell.setNoWrap(false); // 设置自动换行
        cell.setHorizontalAlignment(Element.ALIGN_LEFT); // 设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
        BaseColor customColor = new BaseColor(197, 0, 0);
        cell.setBackgroundColor(customColor);
        table.addCell(cell);
    }

    /**
     * 单元格
     *
     * @param table 表格对象
     * @param text  文本内容
     * @param merge 合并行
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static void typeParameter(PdfPTable table, String text, int merge) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(text, FontUtils.draw_black()));
        cell.setNoWrap(false);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        if (merge > 0) {
            cell.setColspan(merge);
        }
        table.addCell(cell);
    }

    /**
     * 红色背景 白色字色 单元格 居中
     *
     * @param table 表格对象
     * @param text  文本内容
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static void redTypeCenter(PdfPTable table, String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(text, FontUtils.draw()));
        cell.setFixedHeight(20f);
        cell.setNoWrap(false); // 设置自动换行
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        BaseColor customColor = new BaseColor(197, 0, 0);
        cell.setBackgroundColor(customColor);
        table.addCell(cell);
    }

    /**
     * 红色背景 白色字色 单元格 居中 加粗
     *
     * @param table 表格对象
     * @param text  文本内容
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static void redTypeCenterBold(PdfPTable table, String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(text, FontUtils.draw_bold()));
        cell.setFixedHeight(20f);
        cell.setNoWrap(false); // 设置自动换行
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        BaseColor customColor = new BaseColor(197, 0, 0);
        cell.setBackgroundColor(customColor);
        table.addCell(cell);
    }

    /**
     * 单元格 居中
     *
     * @param table 表格对象
     * @param text  文本内容
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static void typeParameterCenter(PdfPTable table, String text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(text, FontUtils.draw_black()));
        cell.setFixedHeight(30f);
        cell.setNoWrap(false);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    /**
     * 超链接
     *
     * @param url  链接地址
     * @param text 文本描述
     * @return 超链接
     * @throws DocumentException 表格异常
     * @throws IOException       字体异常
     */
    private static Anchor websiteRedirect(String url, String text) throws DocumentException, IOException {
        // 使用 PdfAction 创建一个超链接
        Anchor anchor = new Anchor(text, FontUtils.hyperlinks());
        anchor.setReference(url);
        return anchor;
    }

}
