package com.yiyjm.nest;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * NestApplication
 *
 * @author Jonny.Chang
 * @date 2020/05/01
 */
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class NestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NestApplication.class);
	}


	/**
	 * application.yml 配置了 ssl 后，默认使用 https，加入以下配置，同时支持 http
	 *
	 * @return {@link ServletWebServerFactory}
	 */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		// 主程序端口号
		connector.setPort(8810);
		// 添加http
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}

	/**
	 * 该配置激活 http的ws 和 https的wss，支持 websockt 聊天
	 *
	 * @return {@link ServerEndpointExporter}
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}

