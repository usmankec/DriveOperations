package com.elastica.listeners;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.lang.reflect.Method;

/**
 * Priority listener
 * @author erajan
 *
 */
public class PriorityListener implements IMethodInterceptor {

	/**
	 * Priority interceptor
	 */
	public List<IMethodInstance> intercept(List<IMethodInstance>
	methods,ITestContext context){
		Comparator<IMethodInstance> comparator = new
				Comparator<IMethodInstance>() {

			@SuppressWarnings("deprecation")
			private int getPriority(IMethodInstance mi) {
				int result = 0;
				Method method = mi.getMethod().getMethod();
				Priority a1 = method.getAnnotation(Priority.class);

				if (a1 != null) {
					result = a1.value();
				} else {
					Class<?> cls = method.getDeclaringClass();
					Priority classPriority = cls.getAnnotation(Priority.class);
					if (classPriority != null) {
						result = classPriority.value();
					}
				}
				return result;
			}

			public int compare(IMethodInstance m1, IMethodInstance m2) {
				return getPriority(m1) - getPriority(m2);
			}

		};
		IMethodInstance[] array = methods.toArray(new IMethodInstance
				[methods.size()]);
		Arrays.sort(array, comparator);

		return Arrays.asList(array);
	}

}
