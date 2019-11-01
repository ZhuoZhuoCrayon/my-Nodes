package cn.com.generator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorSqlMap {
	public void generator() throws Exception{    
		//warnings为用于放置生成过程中警告信息的集合对象
        List<String> warnings = new ArrayList<String>();  
        //指定DefaultShellCallback是否覆盖重名文件
        boolean overwrite = true;  
        //加载配置文件  
        File configFile = new File("generatorConfig.xml");   
        //配置解析类
        ConfigurationParser cp = new ConfigurationParser(warnings);  
        //配置解析类解析配置文件并生成Configuration配置对象
        Configuration config = cp.parseConfiguration(configFile);  
        //DefaultShellCallback负责如何处理重复文件
        ShellCallback callback = new DefaultShellCallback(overwrite);  
        //逆向工程对象
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,  
                callback, warnings);  
        //执行逆向文件生成操作
        myBatisGenerator.generate(null);  
    }   
    public static void main(String[] args) throws Exception {  
        GeneratorSqlMap generatorSqlmap = new GeneratorSqlMap();  
        generatorSqlmap.generator();    
    }  
}
