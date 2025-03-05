package com.tbank.learn.carsharing.configuration

import com.tbank.learn.carsharing.model.user.User
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import java.util.*


@Configuration
@EnableJdbcAuditing
internal class ApiAuditorAwareConfig : AuditorAware<UUID> {
		override fun getCurrentAuditor(): Optional<UUID> {
				/*val authentication: Authentication = SecurityContextHolder.getContext().getAuthentication()
				return if (authentication == null || !authentication.isAuthenticated()) {
						null
				} else (authentication.getPrincipal() as MyUserDetails).getUser()*/
				return Optional.of(User(UUID(1L,1L), "todo", "todopas", "todopas", 0).id!!)
		}
}