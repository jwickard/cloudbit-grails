package com.github.jwickard.cloudbitgrails

import org.scribe.model.Token

/**
 * <p>OauthServiceDouble</p>
 * <p>
 *  <description/>
 * </p>
 * @author Joel Wickard (Object Partners Inc.)
 */
class OauthServiceDouble {
    def getFitBitResource(Token token, String url){
        return [body: '{}']
    }
}
