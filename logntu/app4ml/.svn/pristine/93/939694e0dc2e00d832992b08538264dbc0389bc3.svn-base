package com.mall.web.admin.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @category 按钮资源
 * 
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ResourceButton {
	// 资源ID
	String[] resourceId() default {};

	// 资源名称
	String[] resourceName() default {};

	// 是否可见组件
	String[] visiles() default {};

	// jsp页面
	String jspUrl() default "";

	// 资源描述
	String description() default "";

	// 资源备注
	String remark() default "";
}