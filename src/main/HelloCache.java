import com.intersys.jdbc.CacheDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 连接cache 数据库代码示例
 * 运行之前请先导入lib下面的jar包
 *
 * @author 福小林
 * https://github.com/ourlang
 */
public class HelloCache {
    public static void main(String[] args) {
        try {
            System.out.println("----------------------------------准备连接数据库----------------------------------");
            Class.forName(CacheConnectInfo.CONNECT_DRIVER.getConnectInfo()).newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL(CacheConnectInfo.CONNECT_URL.getConnectInfo());
            System.out.println("连接地址：" + ds.getURL());

            System.out.println("----------------------------------开始连接数据库----------------------------------");
            Connection dbconn = ds.getConnection(CacheConnectInfo.USERNAME.getConnectInfo(), CacheConnectInfo.PASSWORD.getConnectInfo());
            System.out.println("数据库连接信息：" + ds.getUser() + "-----" + ds.getPassword());
            String sql = "SELECT ID, ConfigActive, ConfigCode, ConfigDesc, ConfigEdit, ConfigExplain, ConfigType, ConfigValue FROM SQLUser.MKB_Config";
            System.out.println("SQL:" + sql);
            int scroll = ResultSet.TYPE_SCROLL_SENSITIVE;
            int update = ResultSet.CONCUR_UPDATABLE;
            System.out.println("----------------------------------查询开始----------------------------------");
            PreparedStatement ps = dbconn.prepareStatement(sql, scroll, update);
            ResultSet rs = ps.executeQuery();
            rs.first();
            System.out.println("\n Old ConfigCode = " + rs.getString("ConfigCode"));
            System.out.println("----------------------------------修改字段信息开始----------------------------------");
            rs.updateString("ConfigCode", "123456");
            rs.updateRow();
            System.out.println("----------------------------------修改字段结束----------------------------------");
            System.out.println("\n New ConfigCode = " + rs.getString("ConfigCode") + "\n");
            System.out.println("----------------------------------关闭连接----------------------------------");
            ps.close();
            rs.close();
            dbconn.close();
            System.out.println("----------------------------------查询结束----------------------------------");
        } catch (Exception ex) {
            System.out.println("----------------------------------数据库连接失败----------------------------------");
            System.out.println("TinyJDBC caught exception: " + ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
