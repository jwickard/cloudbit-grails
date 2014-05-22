package com.github.jwickard.cloudbitgrails

import org.scribe.model.Token
import spock.lang.Specification

/**
 * <p>FitBitOAuthTokenSpec</p>
 * <p>FitBitOAuthToken test suite</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitOAuthTokenSpec extends Specification {
    def oauthToken
    def accessToken
    def profileId
    def screenName

    def setup(){
        profileId = "ee283"
        screenName = 'fitbitschmidt'
        accessToken = Mock(Token)
        accessToken.rawResponse >> 'oauth_token=f9bc&oauth_token_secret=9d5e&encoded_user_id=2Q2QP8'
        oauthToken = new FitBitOAuthToken(accessToken, profileId, screenName)
    }

    def cleanup(){}

    def "it should return initialized screen name"(){
        expect:
            oauthToken.getSocialId() == profileId
    }

    def "it should return provider name"(){
        expect:
            oauthToken.getProviderName() == oauthToken.PROVIDER_NAME
    }

    def "it should return screen name"(){
        expect:
            oauthToken.getScreenName() == screenName
    }

    def "it should store profileId as principal"(){
        expect:
            oauthToken.getPrincipal() == profileId
    }
}
