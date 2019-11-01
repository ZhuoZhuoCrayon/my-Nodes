package cn.com.mybatis.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.mapper.CustomerMapper;
import cn.com.mybatis.po.Batch;
import cn.com.mybatis.po.BatchCustomer;
import cn.com.mybatis.po.BatchDetail;
import cn.com.mybatis.po.BatchItem;
import cn.com.mybatis.po.Customer;
import cn.com.mybatis.po.FinacialProduct;
import cn.com.mybatis.po.GamePlayer;
import cn.com.mybatis.po.Product;
import cn.com.mybatis.po.User;
import cn.com.mybatis.po.UserInstance;
import cn.com.mybatis.po.UserQueryInfo;

public class MyBatisTest {
	public DataConnection dataConn=new DataConnection();
	
	@Test
	public void TestSelect() throws IOException{
		SqlSession sqlSession=dataConn.getSqlSession();   
        User user=sqlSession.selectOne("test.findUserById",1);  
        System.out.println("姓名:"+user.getUsername()); 
        System.out.println("性别:"+user.getGender()); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("生日:"+sdf.format(user.getBirthday())); 
        System.out.println("所在地:"+user.getProvince()+user.getCity()); 
        sqlSession.close();
	}
	
	@Test
	public void TestFuzzySearch() throws IOException{
		SqlSession sqlSession=dataConn.getSqlSession();   
        List<User> userList=sqlSession.selectList("test.findUserByUsername","丽");  
        for (int i = 0; i < userList.size(); i++) {  
            User u=userList.get(i);  
            System.out.println("姓名:"+u.getUsername()); 
            System.out.println("性别:"+u.getGender()); 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("生日:"+sdf.format(u.getBirthday())); 
            System.out.println("所在地:"+u.getProvince()+u.getCity()); 
        }  
        sqlSession.close();
	}
	
	@Test
	public void TestInsert() throws Exception{
		SqlSession sqlSession=dataConn.getSqlSession();   
		User user=new User();
		user.setUsername("孙佳佳");
		user.setGender("男");
		user.setPassword("5555");
		user.setEmail("5555@126.com");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		user.setBirthday(sdf.parse("1991-02-16"));
		user.setProvince("湖北省");
		user.setCity("武汉市");
        sqlSession.insert("test.insertUser",user); 
        sqlSession.commit();
        sqlSession.close();
	}
	
	@Test
	public void TestDelete() throws Exception{
		SqlSession sqlSession=dataConn.getSqlSession();   
        sqlSession.delete("test.deleteUser",5); 
        sqlSession.commit();
        sqlSession.close();
	}
	
	@Test
	public void Testupdate() throws Exception{
		SqlSession sqlSession=dataConn.getSqlSession();   
		User user=new User();
		user.setId(4);
		user.setUsername("孙丽");
        sqlSession.update("test.updateUserName",user); 
        sqlSession.commit();
        sqlSession.close();
	}
	
	//用户信息综合查询  
	@Test  
	public void testFindUserList() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();   
	    //创建包装对象，设置查询条件  
		UserQueryInfo userQueryInfo=new UserQueryInfo();  
	    UserInstance userInstance=new UserInstance();  
	    userInstance.setGender("男");  
	    userInstance.setUsername("张三");  
	    userQueryInfo.setUserInstance(userInstance);  
	      
	    //调用userMapper的方法  
	    List<UserInstance> userList=sqlSession.selectList("test.findUserList",userQueryInfo);  
	      
	    for (int i = 0; i < userList.size(); i++) {  
	    	UserInstance user=(UserInstance)userList.get(i);  
	        System.out.println(user.getId()+":"+user.getUsername());  
	    }
	    sqlSession.close();
	}  
	
	//用户信息综合查询  
	@Test  
	public void testUserList() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
	    Product p=sqlSession.selectOne("test.queryProductInfo2",1);
	    System.out.println(p.getPid()+"||"+p.getPname());
	    List<User> userList=p.getUsers();
	    for (int i = 0; i < userList.size(); i++) {  
	    	System.out.println("****************************");
	    	User user=(User)userList.get(i);  
	        System.out.println(user.getId()+":"+user.getUsername());  
	    }  
	    sqlSession.close();
	}  
	
	//玩家信息综合查询  
	@Test  
	public void testGamePlayerInfo() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
		GamePlayer wgp=sqlSession.selectOne("test.queryWarriorGamePlayer",1);
	    System.out.println("玩家ID："+wgp.getId()+"||玩家昵称："+wgp.getUsername()
	    		+"||玩家性别："+wgp.getuGender()+"||玩家等级"+wgp.getuLevel());
	    System.out.println("玩家职业属性：");
	    Map wMap=wgp.getProfessionalAttributes();//获取职业属性
	    System.out.println("剑气值："+wMap.get("swordValue"));
	    System.out.println("战斗力："+wMap.get("fightingPower"));
	    
		GamePlayer mgp=sqlSession.selectOne("test.queryMagicianGamePlayer",2);
	    System.out.println("玩家ID："+mgp.getId()+"||玩家昵称："+mgp.getUsername()
	    		+"||玩家性别："+mgp.getuGender()+"||玩家等级"+mgp.getuLevel());
	    System.out.println("玩家职业属性：");
	    Map mMap=mgp.getProfessionalAttributes();//获取职业属性
	    System.out.println("法术范围："+mMap.get("SpellRange"));
	    System.out.println("法术强度："+mMap.get("SpellPower"));
	    sqlSession.close();
	}  
	
	@Test  
	public void testBatchCustomer() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
	    List<BatchCustomer> bcList=sqlSession.selectList("findBatchCustomer");
	    if(bcList!=null){
	    	BatchCustomer batchCustomer = null;
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	for (int i = 0; i < bcList.size(); i++) {
	    		batchCustomer = bcList.get(i);
	    		System.out.println("卡号为"+batchCustomer.getAcno()+"的名为"
	    				+batchCustomer.getUsername()+"的客户:\n于"
	    				+sdf.format(batchCustomer.getCreatetime())+"采购了批次号为"
	    				+batchCustomer.getNumber()+"的一批理财产品");
			}
	    }
	    sqlSession.close();
	} 
	
	@Test  
	public void testBatchCustomerToMap() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
	    List<BatchItem> bcList=sqlSession.selectList("findBatchCustomerToMap");
	    if(bcList!=null){
	    	BatchItem batchItem = null;
	    	Customer customer = null;
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	for (int i = 0; i < bcList.size(); i++) {
	    		batchItem = bcList.get(i);//取出批次对象
	    		customer = batchItem.getCustomer();//取出该批次的用户信息
	    		System.out.println("卡号为"+customer.getAcno()+"的名为"
	    				+customer.getUsername()+"的客户:\n于"
	    				+sdf.format(batchItem.getCreatetime())+"采购了批次号为"
	    				+batchItem.getNumber()+"的一批理财产品");
			}
	    }
	    sqlSession.close();
	} 
	
	@Test  
	public void testfindBatchAndBatchDetail() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
		BatchItem batchItem=sqlSession.selectOne("findBatchAndBatchDetail");
	    if(batchItem!=null){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	Customer customer = batchItem.getCustomer();//取出该批次的用户信息
	    	//取出该批次订购的理财产品信息
	    	List<BatchDetail> batchDetails = batchItem.getBatchDetails();
    		System.out.println("卡号为"+customer.getAcno()+"的名为"
    				+customer.getUsername()+"的客户:\n于"
    				+sdf.format(batchItem.getCreatetime())+"采购了批次号为"
    				+batchItem.getNumber()+"的一批理财产品，详情如下：");
    		BatchDetail batchDetail = null;
    		if(batchDetails!=null){
    			for (int i = 0; i < batchDetails.size(); i++) {
    				batchDetail = batchDetails.get(i);
    				System.out.println("id为"+batchDetail.getProduct_id()
    						+"的理财产品"+batchDetail.getProduct_num()+"份");
    			}
        		
    		}
    		
	    }
	    sqlSession.close();
	} 
	
	
	@Test  
	public void testfindCustomerAndProducts() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法，获取所有用户信息(以及从属批次信息)  
		List<Customer> customerList=sqlSession.selectList("findUserAndProducts");
	    if(customerList!=null){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Customer customer = null;
	    	for (int i = 0; i < customerList.size(); i++) {
	    		customer = customerList.get(i);
	    		//1.获取用户基本信息
		    	System.out.println("卡号为"+customer.getAcno()+"的名为"
	    				+customer.getUsername()+"的客户:");
		    	//2.获取用户下的所有批次订单信息
		    	List<Batch> batchList=customer.getBatchList();
		    	Batch batch = null;
		    	for (int j = 0; j < batchList.size(); j++) {
		    		batch = batchList.get(j);
		    		System.out.println("于"
		    				+sdf.format(batch.getCreatetime())+"采购了批次号为"
		    				+batch.getNumber()+"的一批理财产品，详情如下：");
		    		//3.获取一个批次的明细
		    		List<BatchDetail> batchDetails = batch.getBatchDetials();
		    		BatchDetail batchDetail = null;
		    		FinacialProduct finacialProduct = null;
		    		for (int k = 0; k < batchDetails.size(); k++) {
	    				batchDetail = batchDetails.get(k);
	    				System.out.println("id为"+batchDetail.getProduct_id()
	    						+"的理财产品"+batchDetail.getProduct_num()+"份。");
	    				//4.获取每个批次明细中的理财产品详细信息
	    				finacialProduct = batchDetail.getFinacialProduct();
	    				System.out.println("该理财产品的详细信息为：\n"
	    						+"产品名称:"+finacialProduct.getName()
	    						+"|产品价格:"+finacialProduct.getPrice()
	    						+"|产品简介:"+finacialProduct.getDetail());
		    		}	
		    	}
		    	System.out.println("**************************************");
	    	}
	    	
	    }
	    sqlSession.close();
	} 
	
	@Test  
    public void testFindBatchCustomerLazyLoading() throws Exception{  
          
        SqlSession sqlSession=dataConn.getSqlSession();
        //调用userMapper的方法，获取所有订单信息(未加载关联的用户信息)  
      	List<BatchItem> batchItemList=sqlSession.selectList("findBatchUserLazyLoading"); 
      	BatchItem batchItem = null;
      	Customer customer = null;
        for (int i = 0; i < batchItemList.size(); i++) {  
        	batchItem = batchItemList.get(i);  
        	System.out.println("订单编号："+batchItem.getNumber());
            //执行getCustomer时才会去查询用户信息，这里实现了延迟加载  
        	customer=batchItem.getCustomer();  
            System.out.println("订购用户姓名:"+customer.getUsername());  
        }  
        sqlSession.close();
    }  
	
	@Test  
	public void testFindCustomerCache1() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
	    Customer customer1=sqlSession.selectOne("findCustomerById",1);
	    System.out.println("用户姓名："+customer1.getUsername());
	    
	    Customer customer2=sqlSession.selectOne("findCustomerById",1);
	    System.out.println("用户姓名："+customer2.getUsername());
	    sqlSession.close();
	}
	
	@Test  
	public void testFindCustomerCache2() throws Exception{  
	      
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //调用userMapper的方法  
	    Customer customer1=sqlSession.selectOne("findCustomerById",1);
	    System.out.println("用户姓名："+customer1.getUsername()+"|"
	    		+"卡号："+customer1.getAcno());
	    
	    String AcNo = "6228289999999";
	    customer1.setAcno(AcNo);
	    System.out.println("修改用户卡号为："+AcNo);
	    sqlSession.update("UpdateCustomerAcNo",customer1);
	    sqlSession.commit();
	    
	    Customer customer2=sqlSession.selectOne("findCustomerById",1);
	    System.out.println("用户姓名："+customer2.getUsername()+"|"
	    		+"卡号："+customer2.getAcno());
	    
	    sqlSession.close();
	}
	
	@Test  
	public void testFindCustomerOnMapper() throws Exception{  
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //获取Mapper代理  
	    CustomerMapper customerMapper=sqlSession.getMapper(CustomerMapper.class);
	    //执行Mapper代理对象的查询方法
	    Customer customer=customerMapper.findCustomerById(1);
	    System.out.println("用户姓名："+customer.getUsername()+"|"
	    		+"卡号："+customer.getAcno());
	    sqlSession.close();
	}
	
	@Test  
	public void testFindCustomerOnMapper2() throws Exception{  
		SqlSession sqlSession=dataConn.getSqlSession();    
	      
	    //获取Mapper代理  
	    CustomerMapper customerMapper1=sqlSession.getMapper(CustomerMapper.class);
	    //执行Mapper代理对象的查询方法
	    Customer customer1=customerMapper1.findCustomerById(1);
	    System.out.println("用户姓名："+customer1.getUsername()+"|"
	    		+"卡号："+customer1.getAcno());
	    
	    //获取Mapper代理  
	    CustomerMapper customerMapper2=sqlSession.getMapper(CustomerMapper.class);
	    String AcNo = "6228286666666";
	    customer1.setAcno(AcNo);
	    //执行Mapper代理对象的修改方法
	    customerMapper2.updateCustomerAcNo(customer1);
	    System.out.println("修改用户姓名："+customer1.getUsername()+"|"
	    		+"的卡号为："+customer1.getAcno());
	    sqlSession.commit();
	    
	    //获取Mapper代理  
	    CustomerMapper customerMapper3=sqlSession.getMapper(CustomerMapper.class);
	    //执行Mapper代理对象的查询方法
	    Customer customer3=customerMapper3.findCustomerById(1);
	    System.out.println("用户姓名："+customer3.getUsername()+"|"
	    		+"卡号："+customer3.getAcno());
	    
	    sqlSession.close();
	}
}
