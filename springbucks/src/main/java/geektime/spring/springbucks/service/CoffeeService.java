package geektime.spring.springbucks.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import geektime.spring.springbucks.mapper.CoffeeMapper;
import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeExample;

@Service
public class CoffeeService {
	
	 @Autowired 
	 private CoffeeMapper coffeeMapper;
	 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	 public void addCoffee(Coffee cfe) {
		 coffeeMapper.insert(cfe);
	 }
	 
	 @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	 public void deleteCoffee(CoffeeExample example) {
		 coffeeMapper.deleteByExample(example);
	 }
	 
	 public List<Coffee> findAllCoffee(CoffeeExample example){
		 return coffeeMapper.selectByExample(example);
	 }
	 
	 @CachePut(value = "coffee_cache",key = "'page_coffee'")
	 public List<Coffee> findAllWithRowBounds(CoffeeExample example,RowBounds rowBounds){
		 return coffeeMapper.selectByExampleWithRowbounds(example, rowBounds);
	 }
	 
	 
	 @Cacheable(value = "coffee_cache",key = "'page_coffee'")
	 public List<Coffee> findFromCache(){
		 return null;
	 }
	 
}
