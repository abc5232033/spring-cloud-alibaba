package com.xinm.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.sun.jdi.InternalException;
import com.xinm.sentinel.openfeign.StockOpenFeignService;
import com.xinm.sentinel.pojo.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Sentinel接口流控测试
 *
 * @Author: xinm
 * @Date: 2022/08/29 9:31
 * @Email: abc5232033@163.com
 */
@Slf4j
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    /**
     * nacos持久化配置的规则优先级高，有配置下面不会生效
     */
    /**
     * 1.普通流控测试 ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
     */
    /**
     * 资源名
     */
    public static final String RESOURCE_NAME = "flow-control";

    // 进行flow-control流控
    @RequestMapping(value = "/flow-control")
    public String flowControlTest() {
        Entry entry = null;
        try {
            // flow-control针对资源进行限制
            entry = SphU.entry(RESOURCE_NAME);
            // 被保护的业务逻辑
            String str = "flow-control接口正常";
            log.info("===={}====", str);
            return str;
        } catch (BlockException e) {
            e.printStackTrace();
            // 资源访问阻止，被限流或被降级
            // 进行相应的处理操作
            String str = "flow-control接口被流控了";
            log.info(str);
            return str;
        } catch (Exception e) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    /**
     * 定义规则
     * <p>
     * spring的初始化方法
     */
    @PostConstruct
    private static void initFlowRules() {
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();
        // 普通流控
        FlowRule rule = new FlowRule();
        // 设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护资源的阈值
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);

        // user 流控
        FlowRule rule2 = new FlowRule();
        // 设置受保护的资源
        rule2.setResource(USER_RESOURCE_NAME);
        // 设置流控规则 QPS
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护资源的阈值
        // Set limit QPS to 20.
        rule2.setCount(1);
        rules.add(rule2);

        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 2.流控降级测试 ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
     */
    /**
     * 资源名
     */
    public static final String USER_RESOURCE_NAME = "user";

    /**
     * 定义规则
     * <p>
     * spring的初始化方法
     * 写到了上面1的initFlowRules方法中
     */
    // @PostConstruct
    private static void initUserFlowRules() {
        // 流控规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule2 = new FlowRule();
        // 设置受保护的资源
        rule2.setResource(USER_RESOURCE_NAME);
        // 设置流控规则 QPS
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护资源的阈值
        // Set limit QPS to 20.
        rule2.setCount(1);
        rules.add(rule2);

        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 使用@SentinelResource进行Sentinel流控
     *
     * @param id
     * @return
     * @SentinelResource注解改善接口钟资源定义和被流控降级后的处理方法 使用方法：1、添加依赖
     * 2、配置bean-SentinelResourceAspect
     * value:定义流控资源
     * blockHandler：设置流控降级后的处理方法（默认该方法必须声明在同一个类）
     * 如果不想在同一个类中，可以使用 blockHandlerClass 指定，但是方法必须是static
     * fallback：当接口出现异常，就可以交给fallback指定的方法进行处理
     * 如果不想在同一个类中，可以使用 fallbackClass 指定，但是方法必须是static
     * <p>
     * 注意：如果blockHandler和fallback方法同时指定了，则blockHandler优先级更高
     */
    @RequestMapping(value = "/user")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "blockHandlerForUserTest", fallback = "fallbackForUserTest")
    public User userTest(String id) {
        int a = 1 / RandomUtils.nextInt(3);
        return new User("张三");
    }

    /**
     * userTest流控降级后的处理方法
     * 注意：
     * 1、一定要是public
     * 2、返回值一定要和源方法（userTest）保证一致，包含源方法的参数
     * 3、可以在参数最后添加BlockException，可以区分是什么规则的处理方法
     *
     * @param id
     * @param ex
     * @return
     */
    public User blockHandlerForUserTest(String id, BlockException ex) {
        ex.printStackTrace();
        return new User("流控！");
    }

    /**
     * userTest异常后的处理方法
     * 注意：
     * 1、一定要是public
     * 2、返回值一定要和源方法（userTest）保证一致，包含源方法的参数
     * 3、可以在参数最后添加Throwable，可以区分是什么异常
     *
     * @param id
     * @param e
     * @return
     */
    public User fallbackForUserTest(String id, Throwable e) {
        e.printStackTrace();
        return new User("异常处理！");
    }

    /**
     * 3.服务降级测试 ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
     */
    /**
     * 降级资源名
     */
    public static final String DEGRADE_RESOURCE_NAME = "degrade";

    /**
     * 定义服务降级规则
     * <p>
     * spring的初始化方法
     */
    @PostConstruct
    private static void initDegradeRules() {
        // 降级规则
        List<DegradeRule> DegradeRules = new ArrayList<>();
        // 降级
        DegradeRule degradeRule = new DegradeRule();
        // 设置受保护的资源
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        // 设置规则测率： 异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 设置异常数
        degradeRule.setCount(2);
        // 10秒内发生的异常
        degradeRule.setTimeWindow(10);
        // 最小请求数
        degradeRule.setMinRequestAmount(2);

        DegradeRules.add(degradeRule);
        // 加载配置好的规则
        DegradeRuleManager.loadRules(DegradeRules);
    }

    @SneakyThrows
    @RequestMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandlerForDegrade")
    public User degrade(String id) throws InternalException {
        // 异常数/比例
        if (RandomUtils.nextBoolean()) {
            throw new RuntimeException("异常");
        }

        // 慢调用比例
        TimeUnit.SECONDS.sleep(1);
        return new User("正常");
    }

    /**
     * degrade服务降级的处理方法
     *
     * @param id
     * @param ex
     * @return
     */
    public User blockHandlerForDegrade(String id, BlockException ex) {
        ex.printStackTrace();
        return new User("降级处理！");
    }

    /**
     * 4.新增订单,测试feign流控 ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
     */
    @Autowired
    private StockOpenFeignService stockOpenFeignService;

    /**
     * 新增订单
     *
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder() {
        log.info("订单新增成功");
        stockOpenFeignService.subStock();

        return "订单服务-订单新增成功";
    }
}
