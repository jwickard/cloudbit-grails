package com.github.jwickard.cloudbitgrails

import org.scribe.model.Token
import spock.lang.Specification
/**
 * <p>FitBitApiSpec</p>
 * <p>Spec for API</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitApiSpec extends Specification {
    def api

    def setup(){
        api = new FitBitApi()
    }
    def cleanup(){}

    def "it should define request token endpoint"(){
        expect:
            api.getRequestTokenEndpoint() == 'https://api.fitbit.com/oauth/request_token'
    }

    def "it should define access token endpoint"(){
        expect:
            api.getAccessTokenEndpoint() == 'https://api.fitbit.com/oauth/access_token'

    }

    def "it should define authorize url and embed token"(){
        def tokenVal = '123'
        def token = Mock(Token)

        setup:
            token.getToken() >> tokenVal


        expect:
            api.getAuthorizationUrl(token) == "https://www.fitbit.com/oauth/authorize?oauth_token=$tokenVal"
    }
}
