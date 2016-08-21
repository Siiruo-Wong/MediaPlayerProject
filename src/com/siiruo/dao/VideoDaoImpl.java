package com.siiruo.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.siiruo.beans.Collection;
import com.siiruo.beans.Video;
import com.siiruo.util.DateUtil;
import com.siiruo.util.HandlerUtil;
import com.siiruo.util.LoggerUtil;

public class VideoDaoImpl implements VideoDao {
	private SAXReader reader ;
	private OutputFormat format = OutputFormat.createPrettyPrint();
	private File file;
	private  Document docXml;
	private  Element eleRoot;
	private  Element eleVideo;
	private  Element element;
    private Logger logger=LoggerUtil.getLogger(VideoDaoImpl.class.getName());
    /**
     * Constructor
     * @param fileName
     */
	public VideoDaoImpl(String fileName){
		format.setEncoding("UTF-8");
		file = new File(fileName);
		reader = new SAXReader();
		if(!file.exists()){
			try {
				file.createNewFile();	
				createVideoXML();
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
	public VideoDaoImpl(){
		this(".\\src\\resources\\videos.xml");
	}
	/**
	 * 判断是否已经存在指定的视频标签
	 * @param name
	 * @return
	 */
	private boolean isContainVideo(String name){
		List<Element> list=this.eleRoot.elements();
		if(list==null||list.size()==0) return false;
		Iterator<Element> it=list.iterator();
		while(it.hasNext()){
			eleVideo=it.next();
			if(eleVideo.attributeValue("name").equals(name)) return true; 
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
	public void createVideoXML() {  
		docXml= DocumentHelper.createDocument();
		eleRoot=docXml.addElement("videos");
		saveXML();
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
	@Override
	public void create(Video video) {
		if(video==null||isContainVideo(video.getName())) return;
		System.out.println("***************执行");
		eleVideo=eleRoot.addElement("video");
		eleVideo.addAttribute("name",video.getName());
		eleVideo.addAttribute("online",video.isOnline()?"true":"false");
		
		eleVideo.addAttribute("visible",video.isVisible()?"true":"false");
		
		element=eleVideo.addElement("path");
		element.setText(video.getPath());
		element=eleVideo.addElement("time");
		element.setText(HandlerUtil.getStringTime(video.getTime()));
		element=eleVideo.addElement("date");
		element.setText(video.getDate());
		element=eleVideo.addElement("size");
		element.setText(HandlerUtil.getStringSize(video.getSize()));
		element=eleVideo.addElement("collection");
		element.setText(video.getCollection()==null?"默认":video.getCollection().getName());
		saveXML();
	}

	@Override
	public void remove(String name) {
		 eleVideo=(Element) this.docXml.selectSingleNode("/videos/video[@name='"+name+"']");
		if(eleVideo==null){
			logger.error("fail to find element ,which it's name is "+name);
			return ;
		}
		eleRoot.remove(eleVideo);
		saveXML();
	}

	@Override
	public void remove(String[] names) {
		if(names==null||names.length==0) return;
		remove(Arrays.asList(names));
	}
	@Override
	public void remove(List<String> names) {
		List<Element> list=this.docXml.selectNodes("/videos/video");
		if(list==null||list.size()==0) {
			logger.error("fail to find video");
			return;
		}
	    Iterator<Element> iter = list.iterator();
	      while(iter.hasNext()){
	    	  eleVideo= (Element) iter.next();
	          if(names.contains(eleVideo.attributeValue("name"))){
	        	  this.eleRoot.remove(eleVideo);
	          }     
	      }
			saveXML();
	}
	@Override
	public void update(String name, Video video) {
		if(video==null) return;
		eleVideo=(Element) this.docXml.selectSingleNode("/videos/video[@name='"+name+"']");
		if(eleVideo==null){
			logger.error("fail to find element ,which it's name is "+name);
			return ;
		}
		eleVideo.attribute("name").setValue(video.getName());
		eleVideo.attribute("online").setValue(video.isOnline()?"true":"false");
		
		eleVideo.attribute("visible").setValue(video.isVisible()?"true":"false");
		
		eleVideo.element("path").setText(video.getPath());
		eleVideo.element("time").setText(HandlerUtil.getStringTime(video.getTime()));
		eleVideo.element("date").setText(video.getDate());
		eleVideo.element("size").setText(HandlerUtil.getStringSize(video.getSize()));
		eleVideo.element("collection").setText(video.getCollection()==null?"":video.getCollection().getName());		
		saveXML();
	}

	@Override
	public void update(Video video) {
		if(video!=null) update(video.getName(),video);

	}

	@Override
	public Video getVideo(String name) {
		 eleVideo=(Element) docXml.selectSingleNode("/videos/video[@name='"+name+"']");
			if(eleVideo==null){
				logger.error("fail to find element ,which it's name is "+name);
				return null;
			}
		Video video=new Video();
		video.setName(eleVideo.attributeValue("name"));
		video.setOnline(eleVideo.attributeValue("online").equals("true"));
		
		video.setVisible(eleVideo.attributeValue("visible").equals("true"));
		
		video.setPath(eleVideo.element("path").getText());
		video.setDate(eleVideo.element("date").getText());
		video.setTime(HandlerUtil.getLongTime(eleVideo.element("time").getText()));
		video.setSize(HandlerUtil.getDoubleSize(eleVideo.element("size").getText()));
		video.setCollection(new Collection(eleVideo.element("collection").getText()));
		return video;
	}

	@Override
	public List<Video> getVideos(boolean online) {
		 List<Element> list=this.docXml.selectNodes("/videos/video[@online='"+online+"']");
		if(list==null||list.size()==0){
				logger.error("fail to find elements ,which's online is "+online);
				return null;
			}
		Iterator<Element> it=list.iterator();
		List<Video> videos=new ArrayList<Video>();
		Video video=null;
		while(it.hasNext()){
			eleVideo=it.next();
			video=new Video();
			video.setName(eleVideo.attributeValue("name"));
			//video.setOnline(eleVideo.attributeValue("online").equals("true")?true:false);
			video.setOnline(online);
			
			video.setVisible(eleVideo.attributeValue("visible").equals("true"));
			
			video.setPath(eleVideo.element("path").getText());
			video.setDate(eleVideo.element("date").getText());
			video.setTime(HandlerUtil.getLongTime(eleVideo.element("time").getText()));
			video.setSize(HandlerUtil.getDoubleSize(eleVideo.element("size").getText()));
			video.setCollection(new Collection(eleVideo.element("collection").getText()));
			videos.add(video);
		}
		return videos;
	}

	@Override
	public List<Video> getVideos() {
		List<Element> list = this.docXml.selectNodes("/videos/video");
		if (list == null || list.size() == 0) {
			logger.error("fail to find elements...");
			return null;
		}
		Iterator<Element> it = list.iterator();
		List<Video> videos = new ArrayList<Video>();
		Video video = null;
		while (it.hasNext()) {
			eleVideo = it.next();
			video = new Video();
			video.setName(eleVideo.attributeValue("name"));
			video.setOnline(eleVideo.attributeValue("online").equals("true"));
			
			video.setVisible(eleVideo.attributeValue("visible").equals("true"));
			
			video.setPath(eleVideo.element("path").getText());
			video.setDate(eleVideo.element("date").getText());
			video.setTime(HandlerUtil.getLongTime(eleVideo.element("time").getText()));
			video.setSize(HandlerUtil.getDoubleSize(eleVideo.element("size").getText()));
			video.setCollection(new Collection(eleVideo.element("collection").getText()));
			videos.add(video);
		}
		return videos;
	}
	

}
