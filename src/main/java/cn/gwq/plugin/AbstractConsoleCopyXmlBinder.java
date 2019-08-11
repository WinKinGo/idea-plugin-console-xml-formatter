package cn.gwq.plugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gwq
 * @version 1.0
 * @date 2019-08-10
 */
public abstract class AbstractConsoleCopyXmlBinder {

    public AbstractConsoleCopyXmlBinder() {
    }

    static String bind(String selectedText) {
        return getFormatterXml(selectedText);
    }

    /**
     *
     * @param selectText 选择的内容
     * @return 格式化后内容
     */
    private static String getFormatterXml(String selectText) {
        return lineBreakBeforeElementStart(selectText);
    }


    /**
     * 在标签 </elementName> 前换行
     * @param xmlStr 待处理内容
     * @return 处理后内容
     */
    private static String lineBreakBeforeElementStart(String xmlStr) {
        if (xmlStr == null || "".equals(xmlStr)) {
            return xmlStr;
        }
        // match <elementName>
        String regEx = "<\\w*>";
        Matcher matcher = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE).matcher(xmlStr);
        StringBuilder sb = new StringBuilder(xmlStr), sbResult = new StringBuilder();
        int beginIndex = 0, endIndex = 0;
        while (matcher.find()) {
            endIndex = matcher.start();
            sbResult.append("\r\n").append(sb.substring(beginIndex, endIndex));
            beginIndex = endIndex;
        }
        sbResult.append(sb.substring(endIndex));
        return sbResult.toString();
    }
}
