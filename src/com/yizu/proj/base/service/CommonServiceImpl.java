package com.yizu.proj.base.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Scope("prototype")
@Service("commonServiceImpl")
public class CommonServiceImpl extends AbstractService implements BaseService{
	
}
