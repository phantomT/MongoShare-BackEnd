package com.ustc.utils;

import com.ustc.chain.core.Handler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 田宝宁
 */
@Component
public class SpringContentUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContentUtils.applicationContext == null) {
            SpringContentUtils.applicationContext = applicationContext;
        }
    }


    /**
     * 根据bean的名称获取bean
     * @param name bean的名称
     * @return 对应的bean
     */
    public Object getBean(String name) {
        try {
            return applicationContext.getBean(name);
        } catch (Exception ex) {
            return null;
        }
    }

    public Handler getHandler(Class<?> clazz) {
        return (Handler) applicationContext.getBean(clazz);
    }
}
