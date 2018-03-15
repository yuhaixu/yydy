package com.freesheep.common.extend;

import com.freesheep.common.constants.SqlId;
import com.freesheep.common.exception.DaoException;
import com.freesheep.common.util.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


public abstract class AbstractBaseDao<T> {

    @Resource
    private SqlSession sqlSessionTemplate;
    private static final String SQL_NAME_SEPARATOR = ".";

    /**
     * @fields sqlNamespace SqlMapping命名空间
     */
    private String sqlNamespace = getDefaultSqlNamespace();

    /**
     * 获取泛型类型的实体对象类全名
     */
    private String getDefaultSqlNamespace() {
        //Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
        return getClass().getCanonicalName().replace("DAO", "BO") + "Mapper";
    }

    /**
     * 获取SqlMapping命名空间
     */
    public String getSqlNamespace() {
        return sqlNamespace;
    }

    /**
     * 设置SqlMapping命名空间。 以改变默认的SqlMapping命名空间，
     * 不能滥用此方法随意改变SqlMapping命名空间。
     */
    public void setSqlNamespace(String sqlNamespace) {
        this.sqlNamespace = sqlNamespace;
    }

    /**
     * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
     */
    protected String getSqlName(String sqlName) {
        return sqlNamespace + SQL_NAME_SEPARATOR + sqlName;
    }


    public <V extends T> V selectOne(String sqlName, T query) {
        Assert.notNull(query);
        try {
            Map<String, Object> params = BeanUtils.toMap(query);
            return sqlSessionTemplate.selectOne(getSqlName(sqlName), params);
        } catch (Exception e) {
            throw new DaoException(String.format("查询一条记录出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    public <V extends T> V selectByMap(String sqlName, Map<String, Object> params) {
        try {
            return sqlSessionTemplate.selectOne(getSqlName(sqlName), params);
        } catch (Exception e) {
            throw new DaoException(String.format("查询一条记录出错！语句：%s", getSqlName(sqlName)), e);
        }
    }


    public <V extends T> V selectById(Object id) {
        Assert.notNull(id);
        try {
            return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_BY_ID), id);
        } catch (Exception e) {
            throw new DaoException(String.format("根据ID查询对象出错！语句：%s", getSqlName(SqlId.SQL_SELECT_BY_ID)), e);
        }
    }


    public <V extends T> List<V> selectList(String sqlName, T query) {
        try {
            Map<String, Object> params = BeanUtils.toMap(query);
            return sqlSessionTemplate.selectList(getSqlName(sqlName), params);
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    public <V extends T> List<V> selectList(String sqlName, Map<String, Object> params) {
        try {
            return sqlSessionTemplate.selectList(getSqlName(sqlName), params);
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    public <V extends T> List<V> selectAll() {
        try {
            return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_ALL));
        } catch (Exception e) {
            throw new DaoException(String.format("查询所有对象列表出错！语句：%s", getSqlName(SqlId.SQL_SELECT_ALL)), e);
        }
    }

    public <K, V extends T> Map<K, V> selectMap(String sqlName, T query) {
        return selectMap(sqlName, query, "id");
    }

    public <K, V extends T> Map<K, V> selectMap(String sqlName, T query, String mapKey) {
        Assert.notNull(mapKey, "[mapKey] - must not be null!");
        try {
            Map<String, Object> params = BeanUtils.toMap(query);
            return sqlSessionTemplate.selectMap(getSqlName(sqlName), params, mapKey);
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象Map时出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    public <K, V extends T> Map<K, V> selectMap(String sqlName, Map<String, Object> params) {
        return selectMap(sqlName, params, "id");
    }

    public <K, V extends T> Map<K, V> selectMap(String sqlName, Map<String, Object> params, String mapKey) {
        Assert.notNull(mapKey, "[mapKey] - must not be null!");
        try {
            return sqlSessionTemplate.selectMap(getSqlName(sqlName), params, mapKey);
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象Map时出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    /**
     * 设置分页
     *
     * @param pageable 分页信息
     * @return SQL分页参数对象
     */
    private RowBounds getRowBounds(Pageable pageable) {
        RowBounds bounds = RowBounds.DEFAULT;
        if (null != pageable) {
            bounds = new RowBounds(pageable.getOffset(), pageable.getPageSize());
        }
        return bounds;
    }

    /**
     * 获取分页查询参数
     *
     * @param query    查询对象
     * @param pageable 分页对象
     * @return Map 查询参数
     */
    protected Map<String, Object> getParams(Object query, Pageable pageable) {
        Map<String, Object> params = BeanUtils.toMap(query, getRowBounds(pageable));
        if (pageable != null && pageable.getSort() != null) {
            String sorting = pageable.getSort().toString();
            String[] sortInfo = sorting.split(":");
            params.put("sorting_property", sortInfo[0]);
            params.put("sorting_direction", sortInfo[1]);
            params.put("sorting", sorting.replace(":", ""));
        }
        return params;
    }

    public <V extends T> List<V> selectList(String sqlName, T query, Pageable pageable) {
        try {
            return sqlSessionTemplate.selectList(getSqlName(sqlName), getParams(query, pageable));
        } catch (Exception e) {
            throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(sqlName)), e);
        }
    }


    public <V extends T> Page<V> selectPageList(String sqlName, T query, Pageable pageable) {
        try {
            if (pageable.getPageSize() <= 1000 && pageable.getPageSize() > 0 && pageable.getPageNumber() >= 0) {
                List<V> contentList = sqlSessionTemplate.selectList(getSqlName(sqlName),
                        getParams(query, pageable));
                return new PageImpl<V>(contentList, pageable, this.selectCount(sqlName + "_count", query));
            } else {
                throw new DaoException(String.format("分页大小不得超过100，参数不能小于0", getSqlName(sqlName)));
            }
        } catch (Exception e) {
            throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(sqlName)), e);
        }

    }

    public <V extends T> Page<V> selectPageList(String sqlName, Map<String, Object> query, Pageable pageable) {
        try {
            if (pageable.getPageSize() <= 1000 && pageable.getPageSize() > 0 && pageable.getPageNumber() >= 0) {
                List<V> contentList = sqlSessionTemplate.selectList(getSqlName(sqlName),
                        getParams(query, pageable));

                long total = this.selectMapCount(sqlName + "_count", query);

                return new PageImpl<V>(contentList, pageable, total);
            } else {
                throw new DaoException(String.format("分页大小不得超过100，参数不能小于0", getSqlName(sqlName)));
            }
        } catch (Exception e) {
            throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(sqlName)), e);
        }

    }


    public <K, V extends T> Map<K, V> selectMap(String sqlName, T query, Pageable pageable) {
        return selectMap(sqlName, query, "id", pageable);
    }

    private <K, V extends T> Map<K, V> selectMap(String sqlName, T query, String mapKey, Pageable pageable) {
        try {
            return sqlSessionTemplate.selectMap(getSqlName(sqlName), getParams(query, pageable), mapKey);
        } catch (Exception e) {
            throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(sqlName)), e);
        }
    }

    public Long selectCount() {
        try {
            return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_ALL_COUNT));
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_ALL_COUNT)), e);
        }
    }

    protected long selectCount(String sqlName, T query) {
        try {
            Map<String, Object> params = BeanUtils.toMap(query);
            Long result = sqlSessionTemplate.selectOne(getSqlName(sqlName), params);
            if (result == null) {
                return 0;
            }
            return result;
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    protected long selectMapCount(String sqlName, Map<String, Object> query) {
        try {
            //Map<String, Object> params = BeanUtils.toMap(query);
            Long result = sqlSessionTemplate.selectOne(getSqlName(sqlName), query);
            if (result == null) {
                return 0;
            }
            return result;
        } catch (Exception e) {
            throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(sqlName)), e);
        }
    }

    public int insert(T entity) {
        Assert.notNull(entity);
        try {
            return sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT), entity);
        } catch (Exception e) {
            throw new DaoException(String.format("添加对象出错！语句：%s", getSqlName(SqlId.SQL_INSERT)), e);
        }
    }

    Logger log = Logger.getLogger(AbstractBaseDao.class);

    public int insertSelective(T entity) {
        Assert.notNull(entity);
        try {
            return sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT_SELECTIVE), entity);
        } catch (Exception e) {
            log.error(e);
            log.info(e);
            log.info(e.getMessage());
            log.error(e.getMessage());
            throw new DaoException(String.format("添加对象出错！语句：%s", getSqlName(SqlId.SQL_INSERT)), e);
        }
    }

    public int delete(String sqlName, T query) {
        Assert.notNull(query);
        try {
            Map<String, Object> params = BeanUtils.toMap(query);
            return sqlSessionTemplate.delete(getSqlName(sqlName), params);
        } catch (Exception e) {
            throw new DaoException(String.format("删除对象出错！语句：%s", getSqlName(sqlName)), e);
        }
    }


    public int deleteById(Object id) {
        Assert.notNull(id);
        try {
            return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE_BY_ID), id);
        } catch (Exception e) {
            throw new DaoException(String.format("根据ID删除对象出错！语句：%s", getSqlName(SqlId.SQL_DELETE_BY_ID)), e);
        }
    }

    public int deleteAll() {
        try {
            return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE_ALL));
        } catch (Exception e) {
            throw new DaoException(String.format("删除所有对象出错！语句：%s", getSqlName(SqlId.SQL_DELETE_ALL)), e);
        }
    }

    public int updateById(T entity) {
        Assert.notNull(entity);
        try {
            return sqlSessionTemplate.update(getSqlName(SqlId.SQL_UPDATE_BY_ID), entity);
        } catch (Exception e) {
            throw new DaoException(String.format("根据ID更新对象出错！语句：%s", getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
        }
    }

    public int updateById(T entity, String sqlNamespace) {
        Assert.notNull(entity);
        try {
            return sqlSessionTemplate.update(getSqlName(sqlNamespace), entity);
        } catch (Exception e) {
            throw new DaoException(String.format("根据ID更新对象出错！语句：%s", getSqlName(SqlId.SQL_UPDATE_BY_ID)), e);
        }
    }


    public int updateByIdSelective(T entity) {
        Assert.notNull(entity);
        try {
            return sqlSessionTemplate.update(getSqlName(SqlId.SQL_UPDATE_BY_ID_SELECTIVE), entity);
        } catch (Exception e) {
            throw new DaoException(String.format("根据ID更新对象某些属性出错！语句：%s", getSqlName(SqlId.SQL_UPDATE_BY_ID_SELECTIVE)),
                    e);
        }
    }

    public void deleteByIdInBatch(List idList) {
        if (idList == null || idList.isEmpty())
            return;
        for (Object id : idList) {
            this.deleteById(id);
        }
    }

    public void updateInBatch(List<T> entityList) {
        if (entityList == null || entityList.isEmpty())
            return;
        for (T entity : entityList) {
            this.updateByIdSelective(entity);
        }
    }

    public void insertInBatch(List<T> entityList) {
        if (entityList == null || entityList.isEmpty())
            return;
        for (T entity : entityList) {
            this.insert(entity);
        }
    }

    public SqlSession getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

}
