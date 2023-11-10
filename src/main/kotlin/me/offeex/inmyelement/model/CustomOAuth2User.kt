package me.offeex.inmyelement.model

import me.offeex.inmyelement.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

class CustomOAuth2User(
    val user: User,
    authorities: MutableCollection<out GrantedAuthority>,
    attributes: MutableMap<String, Any>,
    nameAttributeKey: String
) : DefaultOAuth2User(authorities, attributes, nameAttributeKey)