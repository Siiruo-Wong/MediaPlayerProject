package com.siiruo.layout;

import java.awt.GridBagConstraints;
import java.awt.Insets;
/**
 * GridbagLayout布局工具类
 * @author SIIRUO
 * @version 1.0
 */
public class GBC extends GridBagConstraints {
	/**
	 * 
	 */
	private static final long serialVersionUID = -743329526970784010L;

	/**
	 * 初始化左上角位置  
	 * @param gridx
	 * @param gridy
	 */
	   public GBC(int gridx, int gridy)  
	   {  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	   }  
	  
	   /**
	    * 初始化左上角位置和所占行数和列数  
	    * @param gridx
	    * @param gridy
	    * @param gridwidth
	    * @param gridheight
	    */
	   public GBC(int gridx, int gridy, int gridwidth, int gridheight)  
	   {  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	      this.gridwidth = gridwidth;  
	      this.gridheight = gridheight;  
	   }  
	  
	  /**
	   *  对齐方式  
	   * @param anchor
	   * @return
	   */
	   public GBC setAnchor(int anchor)  
	   {  
	      this.anchor = anchor;  
	      return this;  
	   }  
	  
	   /**
	    * 是否拉伸及拉伸方向  
	    * @param fill
	    * @return
	    */
	   public GBC setFill(int fill)  
	   {  
	      this.fill = fill;  
	      return this;  
	   }  
	  
	   /**
	    * x和y方向上的增量  
	    * @param weightx
	    * @param weighty
	    * @return
	    */
	   public GBC setWeight(double weightx, double weighty)  
	   {  
	      this.weightx = weightx;  
	      this.weighty = weighty;  
	      return this;  
	   }  
	  
	   /**
	    * 外部填充  
	    * @param distance
	    * @return
	    */
	   public GBC setInsets(int distance)  
	   {  
	      this.insets = new Insets(distance, distance, distance, distance);  
	      return this;  
	   }  
	  
	   /**
	    * 外填充  
	    * @param top
	    * @param left
	    * @param bottom
	    * @param right
	    * @return
	    */
	   public GBC setInsets(int top, int left, int bottom, int right)  
	   {  
	      this.insets = new Insets(top, left, bottom, right);  
	      return this;  
	   }  
	  
	  /**
	   * 外填充  
	   * @param ipadx
	   * @param ipady
	   * @return
	   */
	   public GBC setIpad(int ipadx, int ipady)  
	   {  
	      this.ipadx = ipadx;  
	      this.ipady = ipady;  
	      return this;  
	   }  
}
