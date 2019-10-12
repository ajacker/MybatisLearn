import com.ajacker.dao.IUserDao;
import com.ajacker.domain.QueryVo;
import com.ajacker.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    private static Logger logger = Logger.getLogger(MyBatisTest.class);
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

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
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
        logger.info("SqlSession创建Dao接口的代理对象");
    }

    @After
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        logger.info("SqlSession提交事务");
        //6.释放资源
        sqlSession.close();
        in.close();
        logger.info("释放资源");
    }


    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        users.forEach(System.out::println);
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("王二狗");
        user.setAddress("太原");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println("保存之前:" + user);
        //userDao保存方法
        userDao.saveUser(user);
        System.out.println("保存之后:" + user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = userDao.findById(11);
        user.setUsername("test update");
        user.setAddress("德阳市");
        user.setSex("女");
        user.setBirthday(new Date());
        //userDao保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        //userDao删除方法
        userDao.deleteUser(4);
    }

    /**
     * 测试查询一个
     */
    @Test
    public void testFindOne() {
        //查询一个
        User user = userDao.findById(1);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() {
        //模糊查询
        List<User> users = userDao.findByName("%王%");
        users.forEach(System.out::println);
    }

    /**
     * 测试查询用户数
     */
    @Test
    public void testTotal() {
        //查询用户数
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试实体类包装模糊查询
     */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        //实体类包装模糊查询
        List<User> users = userDao.findUserByVo(vo);
        users.forEach(System.out::println);
    }

    @Test
    public void testSaveUseMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "王飒");
        map.put("userAds", "江洋大道");
        map.put("userBirth", new Date());
        //userDao保存方法
        userDao.saveUserUseMap(map);
    }

    @Test
    public void testFindAllByLimit() {
        List<User> userList = userDao.findAllByLimit(0, 4);
        userList.forEach(System.out::println);
    }

    @Test
    public void testFindAllByRowBounds() {
        RowBounds rowBounds = new RowBounds(0, 4);
        List<User> userList = sqlSession.selectList("com.ajacker.dao.IUserDao.findAll", null, rowBounds);
        userList.forEach(System.out::println);
    }
}
