import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: 11653
 * @createTime: 2024/03/11 15:05
 * @package: PACKAGE_NAME
 * @description:
 */
public class TianYanDemo {
    public static void main(String[] args) throws InterruptedException {
//        List<String> userAgents = Arrays.asList(
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0",
//                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36",
//                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36",
//                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36",
//                "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1)",
//                "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko",
//                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
//                "Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko",
//                "Mozilla/5.0 (Windows NT 6.2; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0E; rv:11.0) like Gecko",
//                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0; .NET CLR 3.5.30729)",
//                "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)"
//        );
//        for (String userAgent : userAgents) {
//            String companyDetailSpider = getCompanyDetailSpider("436777350",userAgent);
//            if (StringUtils.isNotEmpty(companyDetailSpider)){
//                System.out.println(companyDetailSpider);
//            }
//        }
//        test("2");

//        String str = "872980.0621760.0449800.0";
//
//        List<Integer> list = new ArrayList<>();
//
//        // 遍历字符串
//        for (int i = 0; i < str.length(); i++) {
//            // 检查当前字符是否为点号
//            if (str.charAt(i) == '.') {
//                System.out.println("点号的位置：" + i);
//                list.add(i);
//            }
//        }
//
//        int t = 0;
//        List<String> list1 = new ArrayList<>();
//        for (Integer i : list) {
//            String substring = str.substring(t, i + 2);
//            list1.add(substring);
//            t = i + 2;
//        }

        String str = "872980.0";

        String pattern1 = ".*[.,].*[.,].*";
        // 编译正则表达式
        Pattern regex1 = Pattern.compile(pattern1);

        Matcher matcher1 = regex1.matcher(str);

        boolean b = matcher1.find();

//        List<String> list = new ArrayList<>();
//
//        int startIndex = 0;
//        int dotIndex;
//        while ((dotIndex = str.indexOf('.', startIndex)) != -1) {
//            list.add(str.substring(startIndex, dotIndex + 2));
//            startIndex = dotIndex + 2;
//        }
//
//        // 添加最后一个点号后的子串
//        if (startIndex < str.length()) {
//            list.add(str.substring(startIndex));
//        }
//
//        String result = String.join(",", list);
        int i = 0;
    }

    private static String getCompanyDetailSpider(String companyId, String op) {
        String docPubUrl = "https://www.tianyancha.com/company/" + companyId;
        Document doc = null;
        String companyProfile = "";
        // 利用jsoup连接目标url网页获取整个html对象

        Map<String, String> map = new HashMap<>();
        map.put("User-Agent", op);

        try {
            doc = Jsoup.connect(docPubUrl).headers(map).get();
            //防止频繁访问（模拟网络延迟）
//                            Thread.sleep(500);
            if (doc != null) {

                //获取主页面的HTML对象
                Elements span = doc.select("div[class^=index_detail-linewrap__AKtCa index_-intro__ma3Qd]").select("span");
                if (span.size() > 0) {
                    companyProfile = doc.select("div[class^=index_detail-linewrap__AKtCa index_-intro__ma3Qd]").select("span").get(1).text();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyProfile;
    }

    public static void test(String op) {
        switch (op) {
            case "1":
            case "2":
                System.out.println("================================2");
                break;
            default:
                System.out.println("default");
        }
    }


}
