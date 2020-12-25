package com.github.syusuke.intellijrunconfig;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.ws.http.request.HttpRequestPsiUtils;
import com.intellij.ws.http.request.psi.HttpRequest;
import com.intellij.ws.http.request.psi.HttpRequestBlock;
import com.intellij.ws.http.request.psi.HttpRequestBody;
import com.intellij.ws.http.request.psi.HttpRequestTarget;

public class HttpViewID extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);

        HttpRequestBlock[] requestBlocks = HttpRequestPsiUtils.getRequestBlocks(psiFile);

        for (HttpRequestBlock requestBlock : requestBlocks) {
            HttpRequest request = requestBlock.getRequest();
            System.out.println(request.getClass());
            System.out.println(request.getHttpMethod());
            HttpRequestBody requestBody = request.getRequestBody();
            if (requestBody != null) {
                System.out.println(requestBody.getText());
            } else {
                System.out.println("BODY is Null");
            }
            HttpRequestTarget requestTarget = request.getRequestTarget();
            if (requestTarget != null) {
                System.out.println(requestTarget.getText());
            } else {
                System.out.println("target is null");
            }
            System.out.println("-------");
        }

    }
}
