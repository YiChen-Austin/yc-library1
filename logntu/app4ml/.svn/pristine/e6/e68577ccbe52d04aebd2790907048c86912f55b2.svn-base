package com.mall.web.admin.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @category 资源所辖action/controller链接
 * 
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ResourceUrl {
	// 所对应资源
	String resourceKey() default "";

	// url描述
	String description() default "";

	// url备注
	String remark() default "";
}