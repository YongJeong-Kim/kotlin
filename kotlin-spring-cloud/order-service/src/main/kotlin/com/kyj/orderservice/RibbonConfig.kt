package com.kyj.orderservice

import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.WeightedResponseTimeRule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RibbonConfig {
  @Bean
  fun ribbonRule(): IRule? = WeightedResponseTimeRule()
}