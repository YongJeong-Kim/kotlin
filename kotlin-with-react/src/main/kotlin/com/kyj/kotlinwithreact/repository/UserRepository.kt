package com.kyj.kotlinwithreact.repository

import com.kyj.kotlinwithreact.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<User, String>, UserRepositoryCustom {

}