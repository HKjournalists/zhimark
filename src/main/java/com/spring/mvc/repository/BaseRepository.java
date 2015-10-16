package com.spring.mvc.repository;

import com.spring.mvc.model.BaseModel;
import com.spring.mvc.model.Model;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liluoqi on 15/4/22.
 */
@Repository
public class BaseRepository<T extends Model> {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int insert(String sql, T object) {
        return sqlSessionTemplate.insert(sql, object);
    }

    public int batchInsert(String sql, List<T> object) {
        return sqlSessionTemplate.insert(sql, object);
    }

    public T querySingle(String sql, Map<Object, Object> map) {
        return sqlSessionTemplate.selectOne(sql, map);
    }

    public T querySingle(String sql) {
        return sqlSessionTemplate.selectOne(sql);
    }

    public T querySingleById(String sql, int id) {
        return sqlSessionTemplate.selectOne(sql, id);
    }

    public List<T> queryList(String sql, Map<Object, Object> map) {
        return sqlSessionTemplate.selectList(sql, map);
    }

    public List<T> queryList(String sql,List list) {
        return sqlSessionTemplate.selectList(sql, list);
    }

    public List<T> queryList(String sql) {
        return sqlSessionTemplate.selectList(sql);
    }

    public int update(String sql, Map<Object, Object> map) {
        return sqlSessionTemplate.update(sql, map);
    }

    public int update(String sql) {
        return sqlSessionTemplate.update(sql);
    }

    public long count(String sql) {
        return (Long) sqlSessionTemplate.selectOne(sql);
    }

    public long count(String sql, Map<Object, Object> map) {
        return (Long) sqlSessionTemplate.selectOne(sql, map);
    }

    public double sum(String sql) {
        Double sum = (Double) sqlSessionTemplate.selectOne(sql);
        return sum == null ? 0 : sum;
    }

    public double sum(String sql, Map<Object, Object> map) {
        Double sum = (Double) sqlSessionTemplate.selectOne(sql, map);
        return sum == null ? 0 : sum;
    }
}
