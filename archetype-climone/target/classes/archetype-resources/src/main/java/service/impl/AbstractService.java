package ${package}.service.impl;

import org.springframework.beans.factory.BeanNameAware;

public class AbstractService implements BeanNameAware {

	private String beanName;

	public String getBeanName() {
		return beanName;
	}
	
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}


}
