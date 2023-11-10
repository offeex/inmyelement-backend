package me.offeex.inmyelement.service

import me.offeex.inmyelement.entity.User
import me.offeex.inmyelement.repo.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepository
) : DefaultOAuth2UserService() {

    override fun loadUser(request: OAuth2UserRequest): OAuth2User =
        super.loadUser(request).apply {
            if (!userRepo.existsById(deriveId())) userRepo.save(this)
        }

    fun get(principal: OAuth2User): User = userRepo
        .findById(principal.deriveId())
        .orElseGet { userRepo.save(principal) }

    private fun UserRepository.save(user: OAuth2User) = save(User(user.deriveId()))
    private fun OAuth2User.deriveId() = attributes["id"]!!.toString().toLong()
}