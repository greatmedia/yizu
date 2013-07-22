package com.yizu.proj.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLogAnnotation {
	/**
	 * 被访问的操作的文字描述
	 * @return
	 */
	String description();//方法描述
	/**
	 * 需要记录的重要参数
	 * @return
	 */
	String args();//参数，当前request中传递的参数
	/**
	 * 日志分类
	 * @return
	 */
	OperationType operationType(); 
	
	enum OperationType{
		
		/**
		 * 新增记录
		 */
		CREATE(1),
		/**
		 * 修改记录
		 */
		UPDATE(2),
		/**
		 * 删除记录
		 */
		DELETE(3),
		/**
		 * 查询浏览
		 */
		SEARCH_AND_VIEW(4),
		/**
		 * 导出数据
		 */
		EXPORT(5),
		/**
		 * 其他
		 */
		OTHER(6);
		private int value;
		private OperationType(int v){
			value = v;
		}
		public int getValue(){return value;}
		public OperationType get(int i){
			switch(i){
			case 1:return OperationType.CREATE;
			case 2:return OperationType.UPDATE;
			case 3:return OperationType.DELETE;
			case 4:return OperationType.SEARCH_AND_VIEW;
			case 5:return OperationType.EXPORT;
			default:return OperationType.OTHER;
			}
		}
	}
}
