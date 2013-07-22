package com.yizu.proj.base.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.utils.Page;
import com.yizu.proj.utils.ReflectUtil;

public abstract class AbstractService implements ApplicationContextAware {
	private ApplicationContext appContext;

	public int insert(Object record) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		String cname = record.getClass().getSimpleName();
		String mapperString = cname.substring(0, 1).toLowerCase()
				+ cname.substring(1) + "Mapper";
		Object mapper = appContext.getBean(mapperString);
		// Method method = mapper.getClass().getMethod("insert",
		// getTruthClass(record));
		Method method = getMethodContainsSuperClass(mapper.getClass(),
				"insert", getTruthClass(record));
		Object result = method.invoke(mapper, record);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param id
	 *            主键
	 * @param recordClass
	 *            POJO对象
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public int deleteByPrimaryKey(Serializable id, Class recordClass)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String cname = recordClass.getSimpleName();
		Object mapper = getMapper(cname);
		Method method = mapper.getClass().getMethod("deleteByPrimaryKey",
				id.getClass());
		Object result = method.invoke(mapper, id);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param example
	 *            pojo对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int countByExample(Object example) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String pojoName = example.getClass().getSimpleName().replaceAll(
				"Example$", "");
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("countByExample",
				example.getClass());
		Object result = method.invoke(mapper, example);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param example
	 *            pojo对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int deleteByExample(Object example) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String pojoName = example.getClass().getSimpleName().replaceAll(
				"Example$", "");
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("deleteByExample",
				example.getClass());
		Object result = method.invoke(mapper, example);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param record
	 *            POJO对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	public int insertSelective(Object record) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		String pojoName = getTruthClass(record).getSimpleName();
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("insertSelective",
				getTruthClass(record));
		Object result = method.invoke(mapper, record);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param example
	 *            pojo对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public List selectByExample(Object example) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String pojoName = example.getClass().getSimpleName().replaceAll(
				"Example$", "");
		Object mapper = getMapper(pojoName);
		// Method method =
		// mapper.getClass().getMethod("selectByExample",example.getClass());
		Method method = getMethodContainsSuperClass(mapper.getClass(),
				"selectByExample", example.getClass());
		Object result = method.invoke(mapper, example);
		if (result != null) {
			return (List) result;
		}
		return null;
	}

	/**
	 * 查询，并抓取相关属性
	 * 
	 * @param example
	 * @param propertyNames
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public List selectByExampleWithFetchProperty(Object example,
			String[] propertyNames) throws BusinessException, Exception {
		List rtn = selectByExample(example);
		fetchPropertyOfList(rtn, propertyNames);
		return rtn;
	}

	/**
	 * 
	 * @param example
	 *            pojo对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void selectByExample(Object example, Page page)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String pojoName = example.getClass().getSimpleName().replaceAll(
				"Example$", "");
		Object mapper = getMapper(pojoName);
		// 从page对象中抽取分页信息，设置到 example中
		int limitStart = (page.getCurPage() - 1) * page.getRowsPerPage();
		if (page.getCurPage() > 0 && page.getRowsPerPage() > 0) {
			List<Class> ptypes = new ArrayList<Class>();
			ptypes.add(Integer.TYPE);
			ReflectUtil.invoke(example, "setLimitStart", ptypes, limitStart);
			ReflectUtil.invoke(example, "setRowsPerPage", ptypes, page
					.getRowsPerPage());
		}
		Method method = mapper.getClass().getMethod("selectByExample",
				example.getClass());
		Object result = method.invoke(mapper, example);
		if (result != null) {
			page.setData((List) result);
		}
		method = mapper.getClass().getMethod("countByExample",
				example.getClass());
		result = method.invoke(mapper, example);
		if (result != null) {
			page.setRowCount(Integer.parseInt(result.toString()));
		}
	}

	/**
	 * 分页查询，并抓取相关属性
	 * 
	 * @param example
	 * @param page
	 * @param propertyNames
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void selectByExampleWithFetchProperty(Object example, Page page,
			String[] propertyNames) throws BusinessException, Exception {
		this.selectByExample(example, page);
		fetchPropertyOfList(page.getData(), propertyNames);
	}

	/**
	 * 返回POJO对象
	 * 
	 * @param id
	 *            主键
	 * @param recordClass
	 *            POJO对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public Object selectByPrimaryKey(Serializable id, Class recordClass)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String pojoName = recordClass.getSimpleName();
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("selectByPrimaryKey",
				id.getClass());
		Object result = method.invoke(mapper, id);
		if (result != null) {
			return result;
		}
		return null;
	}

	/**
	 * 根据主键查询，并抓取相关属性
	 * 
	 * @param id
	 * @param recordClass
	 * @param propertyNames
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public Object selectByPrimaryKeyWithFetchProperty(Serializable id,
			Class recordClass, String[] propertyNames)
			throws BusinessException, Exception {
		Object obj = selectByPrimaryKey(id, recordClass);
		fetchPropertys(obj, propertyNames);
		return obj;
	}

	/**
	 * 
	 * @param record
	 *            POJO对象
	 * @param example
	 *            POJO 对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	public int updateByExampleSelective(Object record, Object example)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {
		String pojoName = getTruthClass(record).getSimpleName();
		System.out.println("pojoName :  " + pojoName);
		Object mapper = getMapper(pojoName);
		System.out.println("mapper :  " + mapper);
		Method method = mapper.getClass().getMethod("updateByExampleSelective",
				record.getClass(), example.getClass());
		Object result = method.invoke(mapper, record, example);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param record
	 *            POJO
	 * @param example
	 *            POJO对应的Example对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	public int updateByExample(Object record, Object example)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {
		String pojoName = getTruthClass(record).getSimpleName();
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("updateByExample",
				getTruthClass(record), example.getClass());
		Object result = method.invoke(mapper, record, example);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param record
	 *            POJO
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	public int updateByPrimaryKeySelective(Object record)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {
		String pojoName = getTruthClass(record).getSimpleName();
		System.out.println("pojoName :  " + pojoName);
		Object mapper = getMapper(pojoName);
		System.out.println("mapper :  " + mapper);
		Method method = mapper.getClass().getMethod(
				"updateByPrimaryKeySelective", getTruthClass(record));
		Object result = method.invoke(mapper, record);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * 
	 * @param record
	 *            POJO
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	public int updateByPrimaryKey(Object record) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		String pojoName = getTruthClass(record).getSimpleName();
		Object mapper = getMapper(pojoName);
		Method method = mapper.getClass().getMethod("updateByPrimaryKey",
				getTruthClass(record));
		Object result = method.invoke(mapper, record);
		if (result != null) {
			return Integer.parseInt(result.toString());
		}
		return 0;
	}

	/**
	 * public static void main(String[] args) { Tmenu m = new Tmenu();
	 * System.out.println(m.getClass().getSimpleName()); }
	 */

	/**
	 * 通过POJO名称获取mapper对象
	 * 
	 * @param pojoName
	 * @return
	 */
	protected Object getMapper(String pojoName) {
		pojoName = pojoName.indexOf("$") >= 0 ? pojoName.substring(0, pojoName
				.indexOf("$")) : pojoName;// 代理对象名例如：Trole$$.....
		String mapperString = pojoName.substring(0, 1).toLowerCase()
				+ pojoName.substring(1) + "Mapper";
		Object mapper = appContext.getBean(mapperString);
		return mapper;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws ClassNotFoundException
	 */
	protected Class getTruthClass(Object obj) throws ClassNotFoundException {
		String cname = obj.getClass().getName();
		cname = cname.indexOf("$") >= 0 ? cname
				.substring(0, cname.indexOf("$")) : cname;
		return Class.forName(cname);
	}

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		appContext = ctx;
	}

	/**
	 * 调用指定属性触发加载延时加载的对象
	 * 
	 * @param obj
	 * @param propertyName
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	private void fetchProperty(Object obj, String propertyName)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		if (obj == null) {
			return;
		}
		ReflectUtil.getProperty(obj, propertyName);
	}

	private void fetchPropertys(Object obj, String[] propertyNames)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		for (String propertyName : propertyNames) {
			fetchProperty(obj, propertyName);
		}
	}

	private void fetchPropertyOfList(List list, String[] propertyNames)
			throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		for (String propertyName : propertyNames) {
			for (Object obj : list) {
				ReflectUtil.getProperty(obj, propertyName);
			}
		}
	}

	private Method getMethodContainsSuperClass(Class targetClazz,
			String methodName, Class... parameterClazzes)
			throws SecurityException, NoSuchMethodException {
		Method method = null;
		method = targetClazz.getMethod(methodName, parameterClazzes);
		if (method == null) {
			Class[] interfaces = targetClazz.getInterfaces();
			for (Class c : interfaces) {
				method = getMethodContainsSuperClass(c, methodName,
						parameterClazzes);
				if (method != null) {
					break;
				}
			}
			if (method == null && targetClazz.getSuperclass() != null) {
				method = getMethodContainsSuperClass(targetClazz
						.getSuperclass(), methodName, parameterClazzes);
			}
		}
		return method;
	}

	private Field getFieldContainsSuperClass(Class targetClazz, String fieldName)
			throws SecurityException, NoSuchFieldException {
		Field field = null;
		field = targetClazz.getDeclaredField(fieldName);
		if (field == null) {
			Class[] interfaces = targetClazz.getInterfaces();
			for (Class c : interfaces) {
				field = getFieldContainsSuperClass(c, fieldName);
				if (field != null) {
					break;
				}
			}
			if (field == null && targetClazz.getSuperclass() != null) {
				field = getFieldContainsSuperClass(targetClazz.getSuperclass(),
						fieldName);
			}
		}
		return field;
	}
}
