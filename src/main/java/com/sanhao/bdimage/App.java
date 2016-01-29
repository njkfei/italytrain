package com.sanhao.bdimage;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.sanhao.bdimage.config.HttpClientConfig;
import com.sanhao.bdimage.config.ItalyTrainConfig;
import com.sanhao.bdimage.config.MybatisDataConfig;
import com.sanhao.bdimage.service.ItalyTrainService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String args[]){
        AbstractApplicationContext  context = new AnnotationConfigApplicationContext(MybatisDataConfig.class,HttpClientConfig.class,ItalyTrainConfig.class);
/*        BdImageUtil bdImageUtil = (BdImageUtil) context.getBean("bdImageUtil");
        
        bdImageUtil.getResult(null);*/
        ItalyTrainService service = (ItalyTrainService) context.getBean("italyTrainService");
        
        service.getResult(null);
        
    //    service.getResultRedirect(null);
    }
}
