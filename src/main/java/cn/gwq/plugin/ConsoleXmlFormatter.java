package cn.gwq.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;

public class ConsoleXmlFormatter extends AnAction {

    public ConsoleXmlFormatter() {
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor mEditor = (Editor) e.getData(PlatformDataKeys.EDITOR);
        SelectionModel model = mEditor.getSelectionModel();
        // 获取选取内容
        String selectedText = model.getSelectedText();
        if (null != selectedText && !"".equals(selectedText)) {
            String xml = AbstractConsoleCopyXmlBinder.bind(selectedText);
            // 设置粘贴板内容
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(xml), (ClipboardOwner) null);
        }
    }
}
