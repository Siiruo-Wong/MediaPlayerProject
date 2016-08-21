package com.siiruo.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.siiruo.beans.History;
import com.siiruo.beans.Video;
import com.siiruo.util.LoggerUtil;

public class HistoryDaoImpl implements HistoryDao {
	private SAXReader reader ;
	private OutputFormat format = OutputFormat.createPrettyPrint();
	private File file;
	private  Document docXml;
	private  Element eleRoot;
	private  Element eleHistory;
	private  Element element;
    private Logger logger=LoggerUtil.getLogger(CollectionDaoImpl.class.getName());
    /**
     * Constructor
     * @param fileName
     */
	public HistoryDaoImpl(String fileName){
		format.setEncoding("UTF-8");
		file = new File(fileName);
		reader = new SAXReader();
		if(!file.exists()){
			try {
				file.createNewFile();	
				createHistoryXML();
			} catch (IOException e) {
				logger.error("fail to create new XmlFile");
			}
		}else{
			try {
				this.docXml =reader.read(file);
				this.eleRoot=this.docXml.getRootElement();
			} catch (DocumentException e) {
				logger.error("fail to read XML document");
			}	
		}
		
	}
	/**
	 * Default Constructor
	 */
	public HistoryDaoImpl(){
		this(".\\src\\resources\\histories.xml");
	}

/**
 * 将docXml写入到xmlWriter指定的xml文件中
 * @param xmlWriter
 * @param docXml
 */
	private void saveXML() {
		/**
		 * 将docXml中的内容写入xml文件中
		 **/
		FileWriter fileWriter=null;
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException e) {
			logger.error("fail to create FileWriter Object...");
		}
		XMLWriter xmlWriter= new XMLWriter(fileWriter,format);
		try {
			xmlWriter.write(docXml);
			xmlWriter.flush();
			xmlWriter.close();
			fileWriter=null;
		} catch (Exception e) {
			logger.error("fail to save XML document");
		}
	}
	/**
	 * 判断是否已经存在指定的历史标签
	 * @param name
	 * @return
	 */
	private boolean isContainHistory(String name){
		List<Element> list=this.eleRoot.elements();
		if(list==null||list.size()==0) return false;
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			eleHistory=it.next();
			if(eleHistory.attributeValue("name").equals(name)) return true; 
		}
		return false;
	}
	/**
	 * 判断指定的元素是否包含text=name的子元素
	 * @param ele
	 * @param name
	 * @return
	 */
	private boolean isContainElement(Element ele,String name){
		if(ele==null) return false;
		List<Element> list=ele.elements();
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			element=it.next();
			if(element.getText().equals(name)) return true; 
		}
		return false;
	}
	/**
	 * 清空标签元素ele
	 * @param ele
	 */
	private void clearAllElements(Element ele){
		if (ele == null) return;
		List<Element> list = ele.elements();
		Iterator<Element> iterator = list.iterator();
		while (iterator.hasNext()) {
			ele.remove(iterator.next());
		}
	}
	@Override
	public void createHistoryXML() {
		docXml= DocumentHelper.createDocument();
		eleRoot=docXml.addElement("histories");
		element=eleRoot.addElement("history");
		element.addAttribute("name", "今天");
		element=eleRoot.addElement("history");
		element.addAttribute("name", "昨天");
		element=eleRoot.addElement("history");
		element.addAttribute("name", "更久");
		saveXML();
	}

	@Override
	public void create(String name) {
		if(isContainHistory(name)) return;
		eleHistory=eleRoot.addElement("history");
		eleHistory.addAttribute("name",name);
		saveXML();
	}

	@Override
	public void create(History history) {
		if(history==null||isContainHistory(history.getName())) return;
		eleHistory=eleRoot.addElement("history");
		eleHistory.addAttribute("name",history.getName());
		List<Video> videos=history.getVideos();
		Iterator<Video> it=videos.iterator();
		while(it.hasNext()){
			element=eleHistory.addElement("vname");
			element.setText(it.next().getName());
		}
		saveXML();
	}

	@Override
	public void add(String name, String vname) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null) return;
		if(isContainElement(eleHistory, vname)) return;
		element=eleHistory.addElement("vname");
		element.setText(vname);
		saveXML();
	}

	@Override
	public void add(String name, String[] vnames) {
		if(vnames==null||vnames.length==0) return;
		add(name,Arrays.asList(vnames));

	}

	@Override
	public void add(String name, List<String> vnames) {
		if(vnames==null||vnames.size()==0) return;
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null) {
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		Iterator<String> it=vnames.iterator();
		String vname;
		while(it.hasNext()){
			vname=it.next();
			if(isContainElement(eleHistory, vname)) continue;
			element=eleHistory.addElement("vname");
			element.setText(vname);
		}
		saveXML();
	}

	@Override
	public void remove(String name) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null) {
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		this.eleRoot.remove(eleHistory);
		saveXML();
	}

	@Override
	public void remove(String name, String vname) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null) {
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		List<Element> list=eleHistory.elements();
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			element=it.next();
			if(element.getText().equals(vname)) {
				eleHistory.remove(element);
				break;
			}
		}
		saveXML();
	}

	@Override
	public void remove(String name, String[] vnames) {
		if(vnames==null||vnames.length==0) return;
		remove(name,Arrays.asList(vnames));
	}

	@Override
	public void remove(String name, List<String> vnames) {
		if(vnames==null||vnames.size()==0) return;
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null){
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		List<Element> list=eleHistory.elements();
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			element=it.next();
			if(vnames.contains(element.getText())) eleHistory.remove(element);
		}
		saveXML();
	}

	@Override
	public void clear(String name) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		clearAllElements(eleHistory);
		saveXML();
	}

	@Override
	public void update(String name, History history) {
		if(history==null) return;
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null){
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		clearAllElements(eleHistory);
		eleHistory.attribute("name").setValue(history.getName());
		List<Video> list=history.getVideos();
		if(list==null||list.size()==0){
			saveXML();
			return;
		}
		Iterator<Video> it=list.iterator();
		while(it.hasNext()){
			element=eleHistory.addElement("vname");
			element.setText(it.next().getName());
		}
		saveXML();
	}

	@Override
	public void update(History history) {
		if(history!=null) update(history.getName(),history);
	}

	@Override
	public void update(String name, String vname) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null){
			logger.error("fail to find element,which's name is "+name);
			return;
		}
		clearAllElements(eleHistory);
		if(!vname.isEmpty()){
		element=eleHistory.addElement("vname");
		element.setText(vname);
		}
		saveXML();
	}

	@Override
	public void update(String name, String[] vnames) {
		if(vnames==null||vnames.length==0) return;
		update(name,Arrays.asList(vnames));
	}

	@Override
	public void update(String name, List<String> vnames) {	
		if (vnames == null)
			return;
		eleHistory = (Element) this.docXml.selectSingleNode("/histories/history[@name='" + name + "']");
		if (eleHistory == null) {
			logger.error("fail to find element,which's name is " + name);
			return;
		}
		clearAllElements(eleHistory);
		Iterator<String> it = vnames.iterator();
		while (it.hasNext()) {
			element = eleHistory.addElement("vname");
			element.setText(it.next());
		}
		saveXML();
	}

	@SuppressWarnings("unchecked")
	@Override
	public History getHistory(String name) {
		eleHistory=(Element) this.docXml.selectSingleNode("/histories/history[@name='"+name+"']");
		if(eleHistory==null){
			logger.error("fail to find element,which's name is "+name);
			return null;
		}
		List<Video> videos=new ArrayList<Video>();
		List<Element> list=eleHistory.elements();
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			element=it.next();
			videos.add(new Video(element.getText()));
		}
		History history=new History(eleHistory.attributeValue("name"));
		history.setVideos(videos);
		System.out.println(history.getVideos().size());
		return history;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<History> getHistories() {
		List<Element> hiList = this.docXml.selectNodes("/histories/history");
		Iterator<Element> hiIt = hiList.iterator();
		List<Element> eleList;
		Iterator<Element> eleIt;
		List<Video> videos;
		List<History> histories = new ArrayList<History>();
		History history;
		while (hiIt.hasNext()) {
			eleHistory = hiIt.next();
			eleList = eleHistory.elements();
			eleIt = eleList.iterator();
			videos = new ArrayList<Video>();
			while (eleIt.hasNext()) {
				element = eleIt.next();
				videos.add(new Video(element.getText()));
			}
			history = new History(eleHistory.attributeValue("name"));
			history.setVideos(videos);
			histories.add(history);
		}
		return histories;
	}

}
