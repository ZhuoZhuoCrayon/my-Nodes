package cn.com.mybatis.test;

import java.util.List;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import cn.com.mybatis.po.ShoppingCart;

public class CartObjectFactory extends DefaultObjectFactory{
	@Override 
    public <T> T create(Class<T> type) { 
        return super.create(type); 
    }  

    @Override
    //DefaultObjectFactory的create(Class type)方法会调用此方法
    //所以，只需要在此方法中添加逻辑即可。 
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes,List<Object> constructorArgs){ 
        T ret= super.create(type, constructorArgTypes, constructorArgs); 
        //判断加载的类的类型，然后执行init方法。
        if(ShoppingCart.class.isAssignableFrom(type)){ 
        	ShoppingCart entity=(ShoppingCart)ret; 
            entity.init(); 
        } 
        return ret; 
    }
}
