package com.app.planm;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestRunner implements ApplicationRunner {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final DataSource dataSource;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Connection connection = dataSource.getConnection();

		logger.info("DCP : ", dataSource.getClass());
		logger.info("URL : ", connection.getMetaData().getURL());
		logger.info("DCP : ", connection.getMetaData().getUserName());
	}
	
}
