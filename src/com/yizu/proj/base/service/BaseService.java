package com.yizu.proj.base.service;

import java.io.Serializable;
import java.util.List;

import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.utils.Page;

public interface BaseService {
	public int insert(Object record) throws BusinessException,Exception;
	public int deleteByPrimaryKey(Serializable id, Class recordClass) throws BusinessException,Exception;
	public int countByExample(Object example) throws BusinessException,Exception;
	public int deleteByExample(Object example) throws BusinessException,Exception;
	public int insertSelective(Object record) throws BusinessException,Exception;
	public List selectByExample(Object example) throws BusinessException,Exception;
	public List selectByExampleWithFetchProperty(Object example,String[] propertyNames) throws BusinessException,Exception;
	/**
	 * 
	 * @param example
	 * @param page 分页信息
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void selectByExample(Object example,Page page) throws BusinessException,Exception;
	public void selectByExampleWithFetchProperty(Object example,Page page,String[] propertyNames) throws BusinessException,Exception;
	public Object selectByPrimaryKey(Serializable id, Class recordClass) throws BusinessException,Exception;
	public Object selectByPrimaryKeyWithFetchProperty(Serializable id, Class recordClass,String[] propertyNames) throws BusinessException,Exception;
	public int updateByExampleSelective(Object record, Object example) throws BusinessException,Exception;
	public int updateByExample(Object record, Object example) throws BusinessException,Exception;
	public int updateByPrimaryKeySelective(Object record) throws BusinessException,Exception;
	public int updateByPrimaryKey(Object record) throws BusinessException,Exception;
}
