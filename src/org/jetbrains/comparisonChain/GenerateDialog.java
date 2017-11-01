package org.jetbrains.comparisonChain;

import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author fanhl
 */
public class GenerateDialog extends DialogWrapper {
    private final CollectionListModel<PsiField> myFields;
    private final LabeledComponent<JPanel> myComponent;

    public GenerateDialog(PsiClass psiClass) {
        super(psiClass.getProject());
        init();
        setTitle("Select Fields for ComparisonChain");

        myFields = new CollectionListModel<>(psiClass.getAllFields());
        JList fieldList = new JList(myFields);
        fieldList.setCellRenderer(new DefaultPsiElementCellRenderer());
        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(fieldList);
        decorator.disableAddAction();
        JPanel panel = decorator.createPanel();
        myComponent = LabeledComponent.create(panel, "Fields to include in compareTo():");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return myComponent;
    }
}