package com.siiruo.testJTree;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

class STreeNode extends DefaultMutableTreeNode 
{   
  /**
	 * 
	 */
	private static final long serialVersionUID = 9185081436556869005L;
	private Icon icon;  
	private String txt;     
  
  //只包含文本的节点构造
  public STreeNode(String txt)
  {
   super();
   this.txt=txt;
  }  
  
  //包含文本和图片的节点构造
  public STreeNode(Icon icon,String txt)
  {
 super();
    this.icon = icon;  
    this.txt = txt;
  }
 public void setIcon(Icon icon) 
 {   
    this.icon = icon;   
 }   
  
 public Icon getIcon() 
 {   
    return icon;   
  } 
 
 public void setText(String txt)
 {
  this.txt=txt;
 }
 
 public String getText()
 {
  return txt;
 }     
}
