package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaikeba.entity.User;

public class UserTest {

	@Test
	public void testGetUserById() throws Exception{
		//1. 读取配置文件sqlmapconfig.xml
		InputStream inputStream = Resources.getResourceAsStream("sqlmapconfig.xml");
		//2. sqlsessionFactory的创建
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		//3. 创建sqlsession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//4. 基于sqlsession做增删改查
		//第一个参数:statement的id= namespace.id
		//第二个参数:你要绑定的参数的值
		User user = sqlSession.selectOne("hello.getUserById", 1);
		System.out.println(user);
		//5. 资源回收
		sqlSession.close();
	}
	
	
	/**
	 * 
	 */
	//1. 读取配置文件sqlmapconfig.xml
	InputStream inputStream = null;
	//2. sqlsessionFactory的创建
	SqlSessionFactory sqlSessionFactory = null;
	//3. 创建sqlsession
	SqlSession sqlSession = null;
	
	@Before	//在每个单元测试方法之前, 都会运行的方法
	public void init() throws Exception{
		//1. 读取配置文件sqlmapconfig.xml
		inputStream = Resources.getResourceAsStream("sqlmapconfig.xml");
		//2. sqlsessionFactory的创建
		sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		//3. 创建sqlsession
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After //在每个单元测试方法之后, 都会运行的方法
	public void after(){
		//5. 资源回收
		sqlSession.close();
	}
			
	@Test
	public void testGetUserByName() throws Exception{
		//4. 基于sqlsession做增删改查
		//第一个参数:statement的id= namespace.id
		//第二个参数:你要绑定的参数的值
		//selectOne方法必须结果<=1条, selectList是没有限制
		List<User> userlist = sqlSession.selectList("hello.getUserByName", "%a%");
		System.out.println(userlist);
	}
	@Test
	public void testGetUserByName2() throws Exception{
		//4. 基于sqlsession做增删改查
		//第一个参数:statement的id= namespace.id
		//第二个参数:你要绑定的参数的值
		//selectOne方法必须结果<=1条, selectList是没有限制
		List<User> userlist = sqlSession.selectList("hello.getUserByName2", "aaa");
		System.out.println(userlist);
	}
	@Test
	public void testSaveUser() throws Exception{
		User user = new User();
		user.setName("ddd");
		user.setAge(34);
		sqlSession.insert("hello.saveUser", user);
		//所有的增删改操作都是需要手动提交事务
		sqlSession.commit();//提交事务
	}
	
	@Test
	public void testSaveUser1() throws Exception{
		User user = new User();
		user.setName("d");
		user.setAge(345);
		sqlSession.insert("hello.saveUser1", user);
		//所有的增删改操作都是需要手动提交事务
		sqlSession.commit();//提交事务
		System.out.println(user.getId());
	}
	@Test
	public void testSaveUser2() throws Exception{
		User user = new User();
		user.setName("zhangsan");
		user.setAge(34);
		sqlSession.insert("hello.saveUser2", user);
		//所有的增删改操作都是需要手动提交事务
		sqlSession.commit();//提交事务
		System.out.println(user.getId());
	}

	
	@Test
	public void deleteUserById(){
		
		sqlSession.delete("hello.deleteUserById",1);
		sqlSession.commit();
	}
	
	@Test
	public void update(){
		//先查询原先的对象数据
		//根据id=15去查询
		User user = sqlSession.selectOne("hello.getUserById", 7);
		//更新id=15的数据中的name属性
				
		user.setName("cba");
		
		
		sqlSession.update("hello.updateUser", user);
		//要提交
		sqlSession.commit();
		
	}
	
	
}
