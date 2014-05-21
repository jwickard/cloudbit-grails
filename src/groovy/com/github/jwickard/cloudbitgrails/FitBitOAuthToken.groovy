package com.github.jwickard.cloudbitgrails

import grails.plugin.springsecurity.oauth.OAuthToken
import org.scribe.model.Token

/**
 * <p>FitBitOAuthToken</p>
 * <p>Oauth Token encapsulation for FitBit</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitOAuthToken extends OAuthToken {

    public static final String PROVIDER_NAME = "fitbit"

    String profileId
    String screenName

    FitBitOAuthToken(Token accessToken, String profileId, String screenName) {
        super(accessToken)
        this.profileId = profileId
        this.principal = profileId
        this.screenName = screenName
    }

    @Override
    String getProviderName() {
        return PROVIDER_NAME
    }

    @Override
    String getSocialId() {
        return profileId
    }

    @Override
    String getScreenName() {
        return screenName
    }
}
