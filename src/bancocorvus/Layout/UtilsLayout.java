/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Layout;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Jdany
 */
public class UtilsLayout {
    public static Object LeerInputSpinner(JSpinner spinner) {
        Object value = spinner.getValue();
        spinner.setValue(0);
        return value;
    }
    
    public static String LeerInputText(JTextField textField) {
        String value = textField.getText();
        textField.setText("");
        return value;
    }
    
    public static boolean LeerCheckbox(JCheckBox checkbox) {
        boolean value = checkbox.isSelected();
        checkbox.setSelected(false);
        return value;
    }
    
    private static void Popup(String title, String msg, int type) {
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    
    public static void PopupInfo(String title, String msg) {
        Popup(title, msg, INFORMATION_MESSAGE);
    }
    
    public static void PopupError(String title, String msg) {
        Popup(title, msg, ERROR_MESSAGE);
    }
}
