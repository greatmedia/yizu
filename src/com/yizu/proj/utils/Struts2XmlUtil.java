package com.yizu.proj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xml.sax.SAXException;

import com.yizu.proj.annocation.ControlMethod;

public class Struts2XmlUtil implements ApplicationContextAware{
	private Logger log = Logger.getLogger(Struts2XmlUtil.class);
	private ApplicationContext applicationContext;
	private Set<String> extendses = null;
	private String struts2ConfigXmlLocation = "/struts.xml";
	private String actionFix = "action";
	private Map<String, ActionPath> data = null;
	
	private static Struts2XmlUtil instance ;
	private Struts2XmlUtil(){
	}
	
	public synchronized static Struts2XmlUtil getInstance(){
		if(instance == null){
			instance = new Struts2XmlUtil();
		}
		return instance;
	}
	
	private Map<String, ActionPath> getCopy(){
		Map<String,ActionPath> cp = new HashMap<String, ActionPath>();
		for(String k:data.keySet()){
			cp.put(k, data.get(k));
		}
		return cp;
	}
	
	public Map<String, ActionPath> analyzeStrutsConfig(){
		if(data==null){
			doAnalyzeStrutsConfig();
		}
		return getCopy();
		/**
		Map<String,ActionPath> map = (Map<String,ActionPath>)ApplicationUtil.get(Constant.APPLICATION_KEY_ACTION_TREE);
		if(map==null){
			synchronized(this){
				map = (Map<String,ActionPath>)ApplicationUtil.get(Constant.APPLICATION_KEY_ACTION_TREE);
				if(map==null){
					map = doAnalyzeStrutsConfig();
					ApplicationUtil.set(Constant.APPLICATION_KEY_ACTION_TREE, map);
				}
			}
		}
		return map;
		*/
	}
	
	public void doAnalyzeStrutsConfig(){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(struts2ConfigXmlLocation);
		List<Element> packageElements=null;
		List<Element> actionElements = new ArrayList<Element>();
		data = new HashMap<String,ActionPath>();
		String namespace = null;
		try{
			packageElements = analyzePackageFromXmlInputStream(in);
		}catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		for(Element packageElement:packageElements){
			namespace = packageElement.attributeValue("namespace");
			actionElements = analyzeActionFromPackageElement(packageElement);
			if(actionElements!=null){
				for(Element actionElement:actionElements){
					try {
						data.putAll(analyzePathFromActionElement(actionElement,namespace));
					} catch (Exception e) {
						log.error(e.getMessage(),e);
					}
				}
			}
		}
	}

	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		// TODO Auto-generated method stub
		applicationContext=ac;
	}
	
	private List<Element> analyzePackageFromXmlInputStream(InputStream configInputStream) throws DocumentException, SAXException{
		List<Element> list = new ArrayList<Element>();
		SAXReader reader = new SAXReader();
		reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		Document doc = reader.read(configInputStream);
		Element root = doc.getRootElement();
		List<Element> packages = root.elements("package");
		List<Element> includes = root.elements("include");
		list.addAll(packages);
		for(Element include:includes){
			String fileUri = include.attributeValue("file");
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileUri);
			try{
				list.addAll(analyzePackageFromXmlInputStream(in));
			}catch (Exception e) {
				log.error(e.getMessage(),e);
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}
		return list;
	}
	
	private boolean inExtendses(String str){
		return extendses.contains(str);
	}
	
	
	private List<Element> analyzeActionFromPackageElement(Element packageElement){
		if(!inExtendses(packageElement.attributeValue("extends"))){
			return null;
		}
		
		return packageElement.elements("action");
	}
	
	private Map<String,ActionPath> analyzePathFromActionElement(Element actionElement,String namespace) throws SecurityException, NoSuchMethodException{
		String name=actionElement.attributeValue("name");
		String clazz = actionElement.attributeValue("class");
		String method = actionElement.attributeValue("method");
		
		log.debug("struts2XmlUtils 开始执行：name:" +name + ",clazz:" + clazz + ",method :" + method);
		Class actionClass = null;
		Map<String,ActionPath> map = new HashMap<String,ActionPath>();
		try{
			actionClass = Class.forName(clazz);
		}catch (Exception e) {
			try{
				Object bean = applicationContext.getBean(clazz);
				if(bean!=null){
					actionClass = bean.getClass();
				}
			}catch (Exception ex) {
				log.error(ex.getMessage(),ex);
				return null;
			}
		}
		if(method==null){
			method = "execute";
		}
		List<Method> methods = new ArrayList<Method>();
		if(Pattern.matches("^\\w+$", method)){
			Method m = null;
			try{
				m=actionClass.getMethod(method);
			}catch (NoSuchMethodException e) {
				log.error("=========NoSuch method ["+actionClass.getSimpleName()+"."+method+"]=========");
				throw e;
			}
			if(m.getAnnotation(ControlMethod.class)!=null){
				map.put(namespace+"/"+name+"."+actionFix, new ActionPath(namespace+"/"+name+"."+actionFix, m.getAnnotation(ControlMethod.class)));
			}
		}else{
			Method[] ms = actionClass.getDeclaredMethods();
			for(Method m:ms){
				if(Modifier.isPublic(m.getModifiers())&&m.getAnnotation(ControlMethod.class)!=null){
					map.put(namespace+"/"+name.replace("*",m.getName())+"."+actionFix, new ActionPath(namespace+"/"+name.replace("*",m.getName())+"."+actionFix, m.getAnnotation(ControlMethod.class)));
				}
			}
		}
		return map;
	}


	public String getStruts2ConfigXmlLocation() {
		return struts2ConfigXmlLocation;
	}

	public void setStruts2ConfigXmlLocation(String struts2ConfigXmlLocation) {
		this.struts2ConfigXmlLocation = struts2ConfigXmlLocation;
	}
	
	
	public String getActionFix() {
		return actionFix;
	}

	public void setActionFix(String actionFix) {
		this.actionFix = actionFix;
	}

	public Set<String> getExtendses() {
		return extendses;
	}

	public void setExtendses(Set<String> extendses) {
		this.extendses = extendses;
	}
	
}
