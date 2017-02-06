package com.mall.web.mall.common.templatemethod;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class GroupDataCount implements TemplateMethodModelEx   {

	@Override
	public Object exec(List argList) throws TemplateModelException {
		if(argList.size()!=2)   //限定方法中必须且只能传递一个参数  
        {  
            throw new TemplateModelException("Wrong arguments!");  
        }  
		int count=Integer.parseInt(argList.get(0).toString());
		int point=Integer.parseInt(argList.get(1).toString());
		int rs=0;
		if(count%point==0){
			rs=count/point;
		}else{
			rs=count/point+1;
		}
		return rs;
	}


}
