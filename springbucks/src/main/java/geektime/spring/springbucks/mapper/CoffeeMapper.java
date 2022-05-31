package geektime.spring.springbucks.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeExample;

@Mapper
public interface CoffeeMapper {

    long countByExample(CoffeeExample example);


    int deleteByExample(CoffeeExample example);


    @Delete({
        "delete from T_COFFEE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);


    @Insert({
        "insert into T_COFFEE (CREATE_TIME, UPDATE_TIME, ",
        "NAME, PRICE)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{name,jdbcType=VARCHAR}, #{price,jdbcType=BIGINT,typeHandler=geektime.spring.springbucks.handler.MoneyTypeHandler})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Coffee record);


    int insertSelective(Coffee record);


    List<Coffee> selectByExampleWithRowbounds(CoffeeExample example, RowBounds rowBounds);


    List<Coffee> selectByExample(CoffeeExample example);


    @Select({
        "select",
        "ID, CREATE_TIME, UPDATE_TIME, NAME, PRICE",
        "from T_COFFEE",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("geektime.spring.springbucks.mapper.CoffeeMapper.BaseResultMap")
    Coffee selectByPrimaryKey(Long id);


    int updateByExampleSelective(@Param("record") Coffee record, @Param("example") CoffeeExample example);


    int updateByExample(@Param("record") Coffee record, @Param("example") CoffeeExample example);


    int updateByPrimaryKeySelective(Coffee record);


    @Update({
        "update T_COFFEE",
        "set CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "NAME = #{name,jdbcType=VARCHAR},",
          "PRICE = #{price,jdbcType=BIGINT,typeHandler=geektime.spring.springbucks.handler.MoneyTypeHandler}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Coffee record);
}