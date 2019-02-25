package com.kyj.kotlinjpa.domains.user

import com.kyj.kotlinjpa.domains.board.entity.Board
import com.kyj.kotlinjpa.domains.board.repository.BoardRepository
import com.kyj.kotlinjpa.domains.role.Role
import com.kyj.kotlinjpa.domains.role.RoleRepository
import com.kyj.kotlinjpa.enum.RoleEnum
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.annotation.PostConstruct

@RestController
class TestRestController(
  val userRepository: UserRepository,
  val roleRepository: RoleRepository,
  val boardRepository: BoardRepository
) {
  @GetMapping("/")
  fun home() = "home"

  @GetMapping("/home")
  fun hh() = "url home"

  @PreAuthorize("hasAuthority('"+ RoleEnum.RoleName.ROLE_ADMIN + "')")
  @GetMapping("/home/qq")
  fun qq() = "url home qqq"

  @GetMapping("/all")
  fun all() = ResponseEntity(userRepository.findAll(), HttpStatus.NOT_FOUND)

  @PostMapping("/create/user")
  fun createUser(@RequestBody user: User) = ResponseEntity(userRepository.save(user), HttpStatus.CREATED)

  @PutMapping("/update/{id}")
  fun updateUser(@PathVariable id: Long, email: String): User {
    val u = userRepository.findById(id)
    u.ifPresent {
      it.email = email
    }
    return userRepository.save(u.get())
  }

  @PostConstruct
  fun init() {
    if (userRepository.findAll().isEmpty()) {
      val passwordEncoder = BCryptPasswordEncoder()
      val password = passwordEncoder.encode("1234")
      val role = Role(name = "ROLE_ADMIN")
      val role2 = Role(name = "ROLE_USER")
      val roles = mutableListOf(role, role2)

      val user = User(
        name = "aaa name",
        email = "aaa@email.com",
        username = "aaa",
        isAccountNonExpired = true,
        isAccountNonLocked = true,
        isCredentialNonExpired = true,
        isEnabled = true,
        password = password,
        roles = roles
      )
      roleRepository.save(role)
      roleRepository.save(role2)
      userRepository.save(user)

      val boards = (1..15).toList().map {
        Board(subject = "subject $it", text = "text $it")
      }.toList()
      boardRepository.saveAll(boards)

    }
  }
}