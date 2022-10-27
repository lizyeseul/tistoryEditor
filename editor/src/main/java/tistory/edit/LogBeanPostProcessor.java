package tistory.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LogBeanPostProcessor implements BeanPostProcessor {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
        log.info(String.format("Bean instantiated with name %s and class %s", beanName, bean.getClass().getSimpleName()));
        return bean;
    }
}