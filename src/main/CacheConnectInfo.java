
/**
 * 连接数据需要的常量
 * 这里只是测试临时使用，项目中应该配置在配置文件中
 * 如果需要rpc连接，请在github下面留言
 *
 * @author 福小林
 * https://github.com/ourlang
 */
public enum CacheConnectInfo {
    /**
     * 连接cache数据库的驱动字符串
     */
    CONNECT_DRIVER("com.intersys.jdbc.CacheDriver"),
    /**
     * 连接cache数据库的连接地址和数据库
     */
    CONNECT_URL("jdbc:Cache://127.0.0.1:51773/USER"),
    /**
     * 连接cache数据库的账号
     */
    USERNAME("_SYSTEM"),
    /**
     * 连接cache数据库的密码
     */
    PASSWORD("system");

    private String connectInfo;

    CacheConnectInfo(String connectInfo) {
        this.connectInfo = connectInfo;
    }

    public String getConnectInfo() {
        return connectInfo;
    }
}



