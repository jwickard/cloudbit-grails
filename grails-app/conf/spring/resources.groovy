import com.github.jwickard.cloudbitgrails.FitBitSpringSecurityOAuthService

// Place your Spring DSL code here
beans = {
    fitbitSpringSecurityOAuthService(FitBitSpringSecurityOAuthService){
        oauthService = ref("oauthService")
    }

    profileQueue(org.springframework.amqp.core.Queue, 'fit-bit-profile-synch')
}
