package com.github.jwickard.cloudbitgrails

import grails.plugin.springsecurity.oauth.OAuthLoginException
import org.scribe.model.Token
import spock.lang.Specification
/**
 * <p>FitBitSpringSecurityOAuthServiceSpec</p>
 * <p>Test Suite For our Spring Security OAuth Service!</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitSpringSecurityOAuthServiceSpec extends Specification {
    def service
    def token
    def oauthResourceService
    def encodedId = '28782'
    def displayName = 'Schmidt'

    def setup(){
        token = Mock(Token)
        token.rawResponse >> 'oauth_token=f9bc&oauth_token_secret=9d5e&encoded_user_id=2Q2QP8'
        oauthResourceService = Mock(OauthServiceDouble)
        oauthResourceService.getFitBitResource(_, _) >> [body: "{'user': {'encodedId': '$encodedId', 'displayName':'$displayName'}}"]

        service = new FitBitSpringSecurityOAuthService()
        service.oauthService = oauthResourceService
    }

    def cleanup(){}

    def "it should extract encodedId as profileId"(){
        when:
            def oauthToken = service.createAuthToken(token)

        then:
            oauthToken.profileId == encodedId
    }

    def "it should extract displayName as screenName"(){
        when:
            def oauthToken = service.createAuthToken(token)

        then:
            oauthToken.screenName == displayName
    }

    def "it should throw OAuthLoginException if no user id present"(){
        when:
            service.createAuthToken(token)

        then:
            oauthResourceService.getFitBitResource(_, _) >> [body: "{'user': {}}"]
            thrown OAuthLoginException
    }

    def "it should throw OAuthLoginException if response cannot be parsed as JSON"(){
        when:
            service.createAuthToken(token)

        then:
            oauthResourceService.getFitBitResource(_, _) >> [body: "&*#@^*&@#%*@&#%"]
            thrown OAuthLoginException
    }
}
