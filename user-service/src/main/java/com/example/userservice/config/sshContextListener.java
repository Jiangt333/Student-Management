package com.example.userservice.config;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class sshContextListener implements ServletContextListener {
    private sshConnection conexionssh;
    public sshContextListener() {
        super();
    }
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new sshConnection();
        } catch (Throwable e) {
            e.printStackTrace(); // 连接失败
        }
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Context destroyed ... !");
        conexionssh.closeSSH(); // 断开连接
    }
}
