import com.ajacker.dao.IBlogDao;
import com.ajacker.domain.Blog;
import com.ajacker.util.IdUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    private static Logger logger = Logger.getLogger(MyBatisTest.class);
    private InputStream in;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        logger.info("读取配置文件");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        logger.info("创建SqlSessionFactory工厂");
        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        logger.info("工厂生产SqlSession对象");
    }

    @After
    public void destroy() throws IOException {
        //提交事务sqlSession.commit();
        logger.info("SqlSession提交事务");
        //6.释放资源
        sqlSession.close();
        in.close();
        logger.info("释放资源");
    }


    @Test
    public void testInsertBlog() throws Exception {
        IBlogDao blogDao = sqlSession.getMapper(IBlogDao.class);
        Blog blog = new Blog();
        blog.setId(IdUtils.getId());
        blog.setCreateTime(new Date());
        blog.setTitle("啦啦啦博客");
        blog.setAuthor("ajacker");
        blog.setViews(9999);

        blogDao.insertBlog(blog);

        blog.setId(IdUtils.getId());
        blog.setCreateTime(new Date());
        blog.setTitle("Spring笔记");

        blogDao.insertBlog(blog);

        blog.setId(IdUtils.getId());
        blog.setCreateTime(new Date());
        blog.setTitle("mybatis笔记");

        blogDao.insertBlog(blog);
    }

    @Test
    public void testQueryBlog() throws Exception {
        IBlogDao blogDao = sqlSession.getMapper(IBlogDao.class);
        List<Blog> blogs = blogDao.queryBlogChoose("啦啦啦博客", "ajacker", 9999);
        blogs.forEach(System.out::println);
    }

    @Test
    public void testUpdateBlog() throws Exception {
        IBlogDao blogDao = sqlSession.getMapper(IBlogDao.class);
        blogDao.updateBlog("哒哒哒博客", null, "55a0e0cf2b95431db576ac77dd00cc93");
    }

    @Test
    public void testQueryBlogForeach() throws Exception {
        IBlogDao blogDao = sqlSession.getMapper(IBlogDao.class);
        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<Blog> blogs = blogDao.queryBlogForeach(ids);
        blogs.forEach(System.out::println);
    }
}
