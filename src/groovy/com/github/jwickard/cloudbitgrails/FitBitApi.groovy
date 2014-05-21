package com.github.jwickard.cloudbitgrails

import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token

/**
 * <p>FitBitApi</p>
 * <p>Defines our API Endpoints for FitBit</p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class FitBitApi extends DefaultApi10a {

    private static final String AUTHORIZE_URL = 'https://www.fitbit.com/oauth/authorize?oauth_token='
    private static final String REQUEST_TOKEN_RESOURCE = 'https://api.fitbit.com/oauth/request_token'
    private static final String ACCESS_TOKEN_RESOURCE = 'https://api.fitbit.com/oauth/access_token'

    @Override
    String getRequestTokenEndpoint() {
        return REQUEST_TOKEN_RESOURCE
    }

    @Override
    String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_RESOURCE
    }

    @Override
    String getAuthorizationUrl(Token token) {
        return AUTHORIZE_URL+token.getToken()
    }
}
