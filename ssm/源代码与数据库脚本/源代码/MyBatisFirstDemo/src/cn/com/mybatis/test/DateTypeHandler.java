package cn.com.mybatis.test;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class DateTypeHandler implements TypeHandler<Date>{

	//转换日期类型的辅助类
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void setParameter(PreparedStatement ps, int i, Date parameter,
			JdbcType jdbcType) throws SQLException {
		//指定传入的Java参数对应JDBC中的数据库类型
		System.out.println("其它逻辑");
        ps.setDate(i, parameter);
        System.out.println("其它逻辑");
	}
	
	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		System.out.println("其它逻辑");
		return rs.getDate(columnName);
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		System.out.println("其它逻辑");
		return rs.getDate(columnIndex);
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
		System.out.println("其它逻辑");
		return cs.getDate(columnIndex);
	}
}
