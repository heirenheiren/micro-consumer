package com.micro.consumer.cache.local;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimerDuty {
    
    //@Autowired
    //FacilityService facilityService;
    
    /**  
     * 定时任务，每2分钟执行一次操作，从透传云获取数据
     */
    @Scheduled(cron="2 * * * * ?")
    public void run(){
        //facilityService.setTypeToLocalCache();
        log.info("TimerDuty run is starting");
    }
    
    
    /**  
     * 定时任务，每2分钟执行一次操作，从透传云获取数据
     */
    @Scheduled(cron="2 * * * * ?")
    public void runs(){
        //facilityService.setHisToLocakCache();
        log.info("TimerDuty runs is starting");
    }
}
