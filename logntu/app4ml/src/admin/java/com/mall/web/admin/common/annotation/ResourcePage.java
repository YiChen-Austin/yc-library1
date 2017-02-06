package com.mall.web.admin.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @category 记录资源页面
 * 
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ResourcePage {
	// 菜单父菜单ID
	String menuPid() default "";

	// 菜单链接
	String menuUrl() default "";

	// jsp页面
	String jspUrl() default "";

	// 资源描述
	String description() default "";

	// 资源备注
	String remark() default "";
}
