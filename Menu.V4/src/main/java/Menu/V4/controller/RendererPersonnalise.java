package Menu.V4.controller;

import Menu.V4.vue.ParametrageVisuel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hannart Thierry-Julien
 */
public class RendererPersonnalise extends DefaultTableCellRenderer {

    ParametrageVisuel objParametrageVisuel = new ParametrageVisuel();
    
    /**
     * 
     * @param table
     * @param obj
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellule = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        if (table.getValueAt(row, 4).equals(false)) {
            cellule.setBackground(Color.decode("#FF7171"));
        } else if (row % 2 == 0) {
            cellule.setBackground(Color.decode("#FCF8E8"));
        } else {
            cellule.setBackground(objParametrageVisuel.getCouleur4());
        }
        return cellule;
    }
}
