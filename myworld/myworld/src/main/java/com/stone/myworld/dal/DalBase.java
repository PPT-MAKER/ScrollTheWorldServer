package com.stone.myworld.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.util.CollectionUtils;

public class DalBase {
	private DataSource ds = null;
	private Map<String, SimpleJdbcCall> jdbcMap = new HashMap<>();
	
	private SimpleJdbcCall getJdbcCall(String sprocName, String[] resultSets, @SuppressWarnings("rawtypes") RowMapper[] resultRowMappers) {
		SimpleJdbcCall jdbcCall = jdbcMap.get(sprocName);
		if (jdbcCall == null) {
			jdbcCall = new SimpleJdbcCall(initDataSource());
			jdbcCall.setProcedureName(sprocName);
			if (resultSets != null && resultRowMappers != null) {
				for (int i = 0; i < resultSets.length; i++) {
					jdbcCall.returningResultSet(resultSets[i], resultRowMappers[i]);
				}
			}
			jdbcMap.put(sprocName, jdbcCall);
		}
		return jdbcCall;
	}
	
	public Map<String, Object> execute(String sprocName, Map<String, ?> parameters) {
		SimpleJdbcCall jdbcCall = getJdbcCall(sprocName, null, null);
		SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
		return jdbcCall.execute(parameterSource);
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> execute(String sprocName, Map<String, ?> parameters, String[] resultSets, RowMapper[] resultRowMappers) {
		SimpleJdbcCall simpleJdbcCall = getJdbcCall(sprocName, resultSets, resultRowMappers);
		SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
		return simpleJdbcCall.execute(parameterSource);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> executeReturnList(String sprocName, Map<String, ?> parameters, RowMapper<T> resultSetMapper) {
		String defaultSetName = "stone";
		return (List<T>) execute(sprocName, parameters, new String[] { defaultSetName }, new RowMapper[] { resultSetMapper })
				.get(defaultSetName);
	}
	
	public <T> T executeReturnObject(String sprocName, Map<String, ?> parameters, RowMapper<T> resultSetMapper) {
		List<T> resultList = executeReturnList(sprocName, parameters, resultSetMapper);
		if (CollectionUtils.isEmpty(resultList)) {
			return null;
		}
		return resultList.get(0);
	}
	
	private DataSource initDataSource() {
		if (ds == null) {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://212.50.225.62:3306/world?autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8");
			dataSource.setUsername("root");
			dataSource.setPassword("liu546522");
			ds = dataSource;
		}
		return ds;
	}
}
