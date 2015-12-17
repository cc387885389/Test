package com.yiya.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.mapper.${className}Mapper;

/**
 * 
 * <br>
 * <b>功能：</b>TNfcPhoneService<br>
 * <b>作者：</b>ymd<br>
 * <b>日期：</b> 2014年12月9日 <br>
 */
@Service("$!{lowerName}Service")
public class ${className}Service<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(${className}Service.class);
	

	

	@Autowired
    private ${className}Mapper<T> mapper;

		
	public ${className}Mapper<T> getMapper() {
		return mapper;
	}

}
