package com.example.eureka.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.Properties;


public class sshConnection {
    // ssh连接的用户名
    private final static String SSH_USER = "user";
    // ssh连接的密码
    private final static String SSH_PASSWORD = "userr1.";
    // ssh远程连接的ip地址
    private final static String SSH_REMOTE_SERVER = "172.16.108.173";
    // ssh连接的端口号
    private final static int SSH_REMOTE_PORT = 22;
    // 本地mysql发起连接的IP地址
    private final static String MYSQL_REMOTE_SERVER = "192.168.1.2";
    // 本地数据库连接时用的端口号
    private final static int LOCAl_PORT = 3306;
    // 远程数据库端口用的端口号
    private final static int REMOTE_PORT = 3306;

    private Session sesion; //ssh 会话

    public void closeSSH ()
    {
        sesion.disconnect();
    }

    public sshConnection () throws Throwable
    {

        JSch jsch = new JSch();

        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);

        sesion.setPassword(SSH_PASSWORD);

        // 设置连接过程不校验known_hosts文件中的信息
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        sesion.setConfig(config);

        sesion.connect(); // ssh 建立连接！

        // 根据安全策略，必须通过转发端口进行连接
        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT);

    }


}
