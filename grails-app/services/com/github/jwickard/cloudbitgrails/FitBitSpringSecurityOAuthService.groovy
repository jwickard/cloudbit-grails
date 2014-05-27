package com.github.jwickard.cloudbitgrails

import grails.converters.JSON
import grails.plugin.springsecurity.oauth.OAuthLoginException
import org.scribe.model.Token

/**
 * <p>FitBitSpringSecurityOAuthService</p>
 * <p>Auth Service that handles Fitbit authentication</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitSpringSecurityOAuthService {
    def oauthService

    /* proxies response data to oauth token */
    def createAuthToken(Token accessToken) {
        def response = oauthService.getFitBitResource(accessToken, 'https://api.fitbit.com/1/user/-/profile.json')
        def user
        try {

            //we are going to push profile data to our integration core
            rabbitSend 'fit-bit-profile-synch', response.body

            user = JSON.parse(response.body).user
        } catch (Exception e) {
            log.error "Error parsing response from Twitter. Response:\n${response.body}"
            throw new OAuthLoginException("Error parsing response from Twitter", e)
        }
        if (! user?.encodedId) {
            log.error "No user id from Fitbit. Response:\n${response.body}"
            throw new OAuthLoginException("No user id from Fitbit")
        }
        String profileId = "${user.encodedId}"
        String screenName = "${user.displayName}"
        return new FitBitOAuthToken(accessToken, profileId, screenName)
    }
}
