package com.example.demo;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class FeatureTest
{
	@Test
	public void testEmiControllerwithService() throws ClassNotFoundException, IOException {
		if(!checkAnnotationPropertyForFields("com.controller.EmiController", "Autowired", "emiService"))
			Assert.fail("Autowired annotation not applied for emiService member in EmiController class");
	}	
	
	@Test
	public void tesEmiControllerForException() throws ClassNotFoundException, IOException {
		if(!checkAnnotationPropertyForMethods("com.controller.EmiController", "ExceptionHandler", "exceptionHandler"))
			Assert.fail("ExceptionHandler Annotation not found for exceptionHandler method in EmiController class");
	}
	@Test
	public void testForEmiControllerAnnotation() throws ClassNotFoundException, IOException {
		if(!checkAnnotationPropertyForclass("com.controller.EmiController", "Controller", "EmiController"))
			Assert.fail("Controller Annotation not found for EmiController class");
	}
	@Test
	public void testForEmiServiceAnnotation() throws ClassNotFoundException, IOException {
		if(!checkAnnotationPropertyForclass("com.service.EmiService", "Service", "EmiService"))
			Assert.fail("Service Annotation not found for EmiService class");
	}
	
	public static boolean checkAnnotationPropertyForclass(String className, String annotationProperty,
			String methodName) throws IOException, ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(className);
		Annotation[] annotations = null;
		annotations = clazz.getAnnotations();		
		boolean flag = false;
		for (Annotation a : annotations) {
			System.out.println(annotationProperty+" "+a);
			if (a.toString().contains(annotationProperty)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	public static boolean checkAnnotationPropertyForMethods(String className, String annotationProperty,
			String methodName) throws IOException, ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(className);
		Annotation[] annotations = null;
		boolean flag = false;
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getName().equals(methodName)) {
				annotations = method.getAnnotations();
				for (Annotation a : annotations) {
					if (a.toString().contains(annotationProperty)) {
						flag = true;
						break;
					}
				}
			}

		}
		return flag;
	}
	public static boolean checkAnnotationPropertyForFields(String className, String annotationProperty,
			String methodName) throws IOException, ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(className);
		Annotation[] annotations = null;
		boolean flag = false;
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals(methodName)) {
				annotations = field.getAnnotations();

				for (Annotation a : annotations) {

					if (a.toString().contains(annotationProperty)) {
						flag = true;
						break;
					}
				}
			}

		}
		return flag;
	}
}