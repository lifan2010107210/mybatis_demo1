package test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaikeba.dao.BookMapper;
import com.kaikeba.entity.Book;

public class BookMapperTest {

	
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
	public void testGetUserById(){
		//1. 获取mapper接口的代理对象
		BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
		Book param = new Book();
		param.setBname("   ");
//		param.setAuthor("%贾%");
		param.setPress("%清华%");
		
		List<Book> booklist = bookMapper.findBookByParam(param);
		//2. 通过代理对象去调用其中的方法
//		User user = userMapper.getUserById(12);
		System.out.println(booklist);
	}
}
