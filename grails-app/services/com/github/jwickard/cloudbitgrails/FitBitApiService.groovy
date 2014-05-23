package com.github.jwickard.cloudbitgrails
import org.scribe.model.Token
import uk.co.desirableobjects.oauth.scribe.OauthService

class FitBitApiService {
    public static final FOOD_LOG_ENDPOINT = 'https://api.fitbit.com/1/user/-/foods/log.json'
    OauthService oauthService

    def void logIceCreamEntry(Token fitbitAccessToken, IceCreamEntry entry){
        def form = [
            foodName: "$entry.flavor.name Ice Cream",
            mealTypeId: '7',
            unitId: '301',
            amount: "$entry.scoops".toString(),
            date: new Date().format('yyyy-MM-dd')
        ]

        oauthService.postFitBitResource(fitbitAccessToken, FOOD_LOG_ENDPOINT, form)
    }
}
