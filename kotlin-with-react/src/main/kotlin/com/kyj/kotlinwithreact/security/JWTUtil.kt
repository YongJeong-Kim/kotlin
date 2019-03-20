package com.kyj.kotlinwithreact.security

import com.kyj.kotlinwithreact.domain.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JWTUtil(
  @Value("\${myInfo.jjwt.secret}")
  val secret: String,
  @Value("\${myInfo.jjwt.expiration}")
  val expirationTime: String
) {
  fun getHeaderFromToken(token: String) =
    Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.toByteArray())).parseClaimsJws(token).header

  fun getAllClaimsFromToken(token: String): Claims? {
    val claims: Claims?
    try {
      claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret.toByteArray())).parseClaimsJws(token).body
    } catch (e: Exception) {
//      e.printStackTrace()
      return null
    }
    return claims
  }

  fun getUsernameFromToken(token: String): String? = getAllClaimsFromToken(token)?.subject

  fun getExpirationDateFromToken(token: String): Date? = getAllClaimsFromToken(token)?.expiration

  fun isTokenExpired(token: String): Boolean? {
    val expiration = getExpirationDateFromToken(token)
    return expiration?.before(Date())
  }

  fun generateToken(user: User): String {
    val claims = mapOf<String, Any>("role" to user.roles!!)
    return doGenerateToken(claims, user.username)
  }

  fun doGenerateToken(claims: Map<String, Any>, username: String): String {
    val expirationTimeLong = expirationTime.toInt()
    val createdDate = Calendar.getInstance()
    val expirationDate = Calendar.getInstance()
    expirationDate.add(Calendar.MILLISECOND, expirationTimeLong * 1000)
    val key = SecretKeySpec(secret.toByteArray(), 0, secret.toByteArray().size, "HmacSHA512")

    return Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setClaims(claims)
      .setSubject(username)
      .setIssuedAt(createdDate.time)
      .setExpiration(expirationDate.time)
//      .setNotBefore(expirationDate.time)
//      .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.toByteArray()))
      .signWith(key, SignatureAlgorithm.HS512)
      .compact()
  }

  fun validateToken(token: String): Boolean? = !isTokenExpired(token)!!
}