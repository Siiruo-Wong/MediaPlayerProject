package com.siiruo.ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

class LargerComboBoxUI extends BasicComboBoxUI {
    protected ComboPopup createPopup() {
        return new LargerComboPopup(comboBox);
    }
  
 class LargerComboPopup extends BasicComboPopup {
        /**
	 * 
	 */
	private static final long serialVersionUID = -3804732150248037511L;

		public LargerComboPopup(JComboBox<String> comboBox) {
            super(comboBox);
        }
  
        public void show() {
            int selectedIndex = comboBox.getSelectedIndex();
            if (selectedIndex == -1) {
                list.clearSelection();
            } else {
                list.setSelectedIndex(selectedIndex);
                list.ensureIndexIsVisible(selectedIndex);
            }
  
            Insets insets = getInsets();
            Dimension listDim = list.getPreferredSize();
            boolean hasScrollBar = scroller.getViewport().getViewSize().height != listDim.height;
            if (hasScrollBar) {
                JScrollBar scrollBar = scroller.getVerticalScrollBar();
                listDim.width += scrollBar.getPreferredSize().getWidth();
            }
  
            int width = Math.max(listDim.width, comboBox.getWidth() - (insets.right + insets.left));
            int height = getPopupHeightForRowCount(comboBox.getMaximumRowCount());
            Rectangle popupBounds = computePopupBounds(0, comboBox.getHeight(), width, height);
  
            Dimension scrollSize = popupBounds.getSize();
            scroller.setMaximumSize(scrollSize);
            scroller.setPreferredSize(scrollSize);
            scroller.setMinimumSize(scrollSize);
  
            list.revalidate();
            show(comboBox, popupBounds.x, popupBounds.y);
        }
    }
} 
