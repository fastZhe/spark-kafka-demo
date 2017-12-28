package com.hz.streaming.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class GenerateInstance implements Serializable{

    private static Logger logger= LoggerFactory.getLogger(GenerateInstance.class);

    /**
     * 生成对应类的实例
     * @param classname
     * @return
     */
    public static Object getInstance(String classname){
        Object obj=null;
        try {
            Class<?> rs = GenerateInstance.class.getClassLoader().loadClass(classname);
            obj=rs.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("没有相关class: "+classname);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;

    }
}
