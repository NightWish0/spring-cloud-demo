package com.miscroservice.control;

import com.miscroservice.client.UserFeignClient;
import com.miscroservice.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    private static final Logger LOGGER=LoggerFactory.getLogger(MovieController.class);

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("user.userServiceUrl")
    private String userServiceUrl;

//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable Long id){
//        return this.restTemplate.getForObject(this.userServiceUrl+id,User.class);
//    }

//    @HystrixCommand(fallbackMethod="findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return this.userFeignClient.findById(id);
    }

//    public User findByIdFallback(Long id){
//        User user=new User();
//        user.setId(-1L);
//        user.setName("默认用户");
//        return user;
//    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance=this.loadBalancerClient.choose("miscroservice-provider-user");
        // 打印当前选择的哪个节点
        MovieController.LOGGER.info("{}:{}:{}",serviceInstance.getServiceId(),
                serviceInstance.getHost(),serviceInstance.getPort());
    }

}
