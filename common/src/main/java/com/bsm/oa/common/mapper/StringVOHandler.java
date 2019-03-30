package com.bsm.oa.common.mapper;

import com.bsm.oa.common.model.ValueObject;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

@Slf4j
@RequiredArgsConstructor
public class StringVOHandler<V extends ValueObject<String>> extends BaseTypeHandler<V> {

  private final Class<V> type;

  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, V v,
                                  JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, v.getValue());
  }

  @Override
  public V getNullableResult(ResultSet resultSet, String label) throws SQLException {
    return createValueObject(resultSet.getString(label));
  }

  @Override
  public V getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return createValueObject(resultSet.getString(i));
  }

  @Override
  public V getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return createValueObject(callableStatement.getString(i));
  }

  private V createValueObject(String string) throws SQLException {
    try {
      return type.getConstructor(String.class).newInstance(string);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      log.error(e.getMessage());
      throw new SQLException(e);
    }
  }
}
