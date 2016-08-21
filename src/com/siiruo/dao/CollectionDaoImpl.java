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

import com.siiruo.beans.Collection;
import com.siiruo.beans.Video;
import com.siiruo.util.DateUtil;
import com.siiruo.util.HandlerUtil;
import com.siiruo.util.LoggerUtil;

public class CollectionDaoImpl implements CollectionDao {
	private SAXReader reader ;
	private OutputFormat format = OutputFormat.createPrettyPrint();
	private File file;
	private  Document docXml;
	private  Element eleRoot;
	private  Element eleCollection;
	private  Element element;
    private Logger logger=LoggerUtil.getLogger(CollectionDaoImpl.class.getName());
    /**
     * Constructor
     * @param fileName
     */
	public CollectionDaoImpl(String fileName){
		format.setEncoding("UTF-8");
		file = new File(fileName);
		reader = new SAXReader();
		if(!file.exists()){
			try {
				file.createNewFile();	
				createCollectionXML();
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
	public CollectionDaoImpl(){
		this(".\\src\\resources\\collections.xml");
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
	public void createCollectionXML() {  
		docXml= DocumentHelper.createDocument();
		eleRoot=docXml.addElement("collections");
		eleCollection=eleRoot.addElement("collection");
		eleCollection.addAttribute("name", "默认");
		saveXML();
	}

	@Override
	public void create(String name) {
		if(isContainCollection(name)) return;
		eleCollection=eleRoot.addElement("collection");
		eleCollection.addAttribute("name", name);
		saveXML();
	}

	@Override
	public void create(Collection collection) {
		if(collection==null||isContainCollection(collection.getName())) return;
		eleCollection=eleRoot.addElement("collection");
		eleCollection.addAttribute("name", collection.getName());
		List<Video> list=collection.getVideos();
		Iterator<Video> iterator=list.iterator();
		Video video=null;
		while(iterator.hasNext()){
			element=eleCollection.addElement("vname");
			video=iterator.next();
			element.setText(video.getName());
		}
		saveXML();
	}
	
	@Override
	public void add(String name, String vname) {
		if(vname==null||vname.isEmpty()) return;
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection,which's name is "+name);
		}
		if(isContainVideo(eleCollection,vname)) return;
		element=eleCollection.addElement("vname");
		element.setText(vname);
		saveXML();
	}

	@Override
	public void add(String name, String[] vnames) {
		if(vnames==null||vnames.length==0) return;
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection,which's name is "+name);
		}
		int i=0,len=vnames.length;
		String vname;
		for(;i<len;){
			vname=vnames[i++];
			if(isContainVideo(eleCollection,vname)) continue;
			element=eleCollection.addElement("vname");
			element.setText(vname);
		}
		saveXML();
		
		//add(name,Arrays.asList(vnames));//另一种方式
	}

	@Override
	public void add(String name, List<String> vnames) {
		if(vnames==null||vnames.size()==0) return;
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection,which's name is "+name);
		}
		Iterator<String> iterator=vnames.iterator();
		String vname=null;
		while(iterator.hasNext()){
			vname=iterator.next();
			if(isContainVideo(eleCollection,vname)) continue;
			element=eleCollection.addElement("vname");
			element.setText(vname);
		}
		saveXML();
	}

	@Override
	public void remove(String name) {
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection element, which's name is "+name);
			return;
		}
		this.eleRoot.remove(eleCollection);
		saveXML();
	}
	@Override
	public void remove(String name, String vname) {
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection element, which's name is "+name);
			return;
		}
		List<Element> list=eleCollection.elements();
		Iterator< Element> iterator=list.iterator();
		while(iterator.hasNext()){
			element=iterator.next();
			if(element.getText().equals(vname)){
				eleCollection.remove(element);
				break;
			}
		}
		saveXML();
	}
	@Override
	public void remove(String name, String[] vnames) {
		remove(name,Arrays.asList(vnames));
	}
	@Override
	public void remove(String name, List<String> vnames) {
		if(vnames==null||vnames.size()==0) return;
		eleCollection=(Element) this.docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		if(eleCollection==null){
			logger.error("fail to find collection element, which's name is "+name);
			return;
		}
		List<Element> list=eleCollection.elements();
		if(list==null) return;
		Iterator< Element> iterator=list.iterator();
		while(iterator.hasNext()){
			element=iterator.next();
			if(vnames.contains(element.getText()))
				eleCollection.remove(element);
		}
		saveXML();
	}

	@Override
	public void clear(String name) {
		eleCollection = (Element) this.docXml.selectSingleNode("/collections/collection[@name='" + name + "']");
		if (eleCollection == null) {
			logger.error("fail to find collection element, which's name is " + name);
			return;
		}
		clearAllElements(eleCollection);
		saveXML();
	}

	@Override
	public void update(String name, Collection collection) {
		if(collection==null) return;
		eleCollection = (Element) this.docXml.selectSingleNode("/collections/collection[@name='" + name + "']");
		if (eleCollection == null) {
			logger.error("fail to find collection element, which's name is " + name);
			return;
		}
		clearAllElements(eleCollection);
		eleCollection.attribute("name").setValue(collection.getName());
		List<Video> videos=collection.getVideos();
		if(videos==null||videos.size()==0){
			saveXML();
			return;
		}
		Iterator<Video> iterator=videos.iterator();
		Video video=null;
		while(iterator.hasNext()){
			video=iterator.next();
			element=eleCollection.addElement("vname");
			element.setText(video.getName());
		}
		saveXML();
	}

	@Override
	public void update(Collection collection) {
		if(collection==null) return;
		update(collection.getName(), collection);

	}
	@Override
	public void update(String name, String vname) {
		eleCollection = (Element) this.docXml.selectSingleNode("/collections/collection[@name='" + name + "']");
		if (eleCollection == null) {
			logger.error("fail to find collection element, which's name is " + name);
			return;
		}
		clearAllElements(eleCollection);
		if(!vname.isEmpty()){
			element=eleCollection.addElement("vname");
			element.setText(vname);
		}
		saveXML();
		
	}
	@Override
	public void update(String name, String[] vnames) {
		if(vnames==null) return;
		update(name,Arrays.asList(vnames));
	}
	@Override
	public void update(String name, List<String> vnames) {
		if(vnames==null) return;
		eleCollection = (Element) this.docXml.selectSingleNode("/collections/collection[@name='" + name + "']");
		if (eleCollection == null) {
			logger.error("fail to find collection element, which's name is " + name);
			return;
		}
		clearAllElements(eleCollection);
		Iterator<String> iterator=vnames.iterator();
		String vname=null;
		while(iterator.hasNext()){
			vname=iterator.next();
			element=eleCollection.addElement("vname");
			element.setText(vname);
		}
		saveXML();
	}
	@Override
	public Collection getCollection(String name) {
		eleCollection = (Element) this.docXml.selectSingleNode("/collections/collection[@name='" + name + "']");
		if (eleCollection == null) {
			logger.error("fail to find collection element, which's name is " + name);
			return null;
		}
		List<Element> list=eleCollection.elements();
		Iterator<Element> iterator=list.iterator();
		List<Video> videos=new ArrayList<Video>();
		while(iterator.hasNext()){
			element=iterator.next();
			videos.add(new Video(element.getText()));
		}
		Collection collection=new Collection(eleCollection.attributeValue("name"));
		collection.setVideos(videos);
		return collection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Collection> getCollections() {
		List<Element> collList=this.docXml.selectNodes("/collections/collection");
		Iterator<Element> iterator=collList.iterator();
		List<Collection> collections=new ArrayList<Collection>();
	    List<Element> eles;
		List<Video> videos;
		Collection collection;
		while(iterator.hasNext()){
			eleCollection=iterator.next();
			eles=eleCollection.elements();
			 Iterator<Element> it=eles.iterator();
			 videos=new ArrayList<Video>();
			 while(it.hasNext()){
				 element=it.next();
				 videos.add(new Video(element.getText()));
			 }
			 collection=new Collection(eleCollection.attributeValue("name"));
			 collection.setVideos(videos);
			 collections.add(collection);
		}
		return collections;
	}
	
	

	private boolean isContainCollection(String name){
		 eleCollection=(Element) docXml.selectSingleNode("/collections/collection[@name='"+name+"']");
		 return eleCollection!=null;
	}
	/**
	 * 判断收藏夹是否包含名称为vname的视频信息
	 * @param name
	 * @param vname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean isContainVideo(String name,String vname){
		List<Element> list=docXml.selectNodes("/collections/collection[@name='"+name+"']"+"/vname");
		if(list==null||list.size()==0) return false;
		Iterator<Element> iterator=list.iterator();
		while(iterator.hasNext()){
			element=iterator.next();
			if(element.getText().equals(vname)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断标签元素ele是否包含名称为vname的视频信息
	 * @param ele
	 * @param vname
	 * @return
	 */
	private boolean isContainVideo(Element ele,String vname){
		if(ele==null) return false;
		List<Element> list=ele.elements();
		Iterator<Element> iterator=list.iterator();
		while(iterator.hasNext()){
			element=iterator.next();
			if(element.getText().equals(vname)){
				return true;
			}
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
	
}
