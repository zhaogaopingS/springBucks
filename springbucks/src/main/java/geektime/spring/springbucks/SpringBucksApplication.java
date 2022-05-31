package geektime.spring.springbucks;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeExample;
import geektime.spring.springbucks.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching

@MapperScan("geektime.spring.springbucks.mapper")
public class SpringBucksApplication implements ApplicationRunner {
	@Autowired
	private CoffeeService coffeeService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		addNewCoffees();
		operatorCoffee();
		batchQueryById();
		searchCoffeePages();
	}
	
	//添加咖啡
	private void addNewCoffees() {
		//应用启动后，默认插入espresso
		List<Coffee> allCoffee = coffeeService.findAllCoffee(null);
		log.info("库存现有咖啡{}",allCoffee);
		
		//增加其它种类咖啡
		Coffee cfe = new Coffee();
		coffeeService.addCoffee(cfe.withName("natie")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 11.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.addCoffee(cfe.withName("zhenzhu")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 13.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.addCoffee(cfe.withName("meishi")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 15.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.addCoffee(cfe.withName("kekou")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 18.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		//输出咖啡库存
		log.info("库存所有咖啡{}",coffeeService.findAllCoffee(null));
	}
	
	//新增一款咖啡，然后删除它
	private void operatorCoffee() {
		Coffee cfe = new Coffee();
		CoffeeExample example = new CoffeeExample();
		coffeeService.addCoffee(cfe.withName("拿铁咖啡")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 16.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		example.createCriteria().andNameEqualTo("拿铁咖啡");
		List<Coffee> allCoffee = coffeeService.findAllCoffee(example);
		log.info("入库成功了{}",allCoffee.get(0).toString());
		//太贵了，卖的不好删除它
		coffeeService.deleteCoffee(example);
		allCoffee = coffeeService.findAllCoffee(example);
		log.info("没有库存了{}",allCoffee.size());
	}

	//根据ID批量查询
	private void batchQueryById() {
		log.info("开始执行批量查询");
		CoffeeExample example = new CoffeeExample();
		example.createCriteria().andIdIn(Arrays.asList(new Long[] {1L,2L,3L,4L,5L}));
		List<Coffee> list = coffeeService.findAllCoffee(example);
		list.forEach(c -> log.info("咖啡来了：{}",c.toString()));
	}
	
	//分页查询coffee
	private void searchCoffeePages() {
		log.info("开始执行分页查询。");
		coffeeService.findAllWithRowBounds(null,new RowBounds(0, 5))
		.forEach(c -> log.info("第一页：{}", c));
		List<Coffee> cacheList = coffeeService.findFromCache();
		cacheList.forEach(c -> log.info("缓存所有数据：{}",c.toString()));
		try {
			Thread.sleep(30000);
			log.info("超过缓存时间：{}",coffeeService.findFromCache());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

