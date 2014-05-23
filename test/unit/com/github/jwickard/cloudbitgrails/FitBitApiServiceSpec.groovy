package com.github.jwickard.cloudbitgrails
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.scribe.model.Token
import spock.lang.Specification
import uk.co.desirableobjects.oauth.scribe.OauthService
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FitBitApiService)
@Mock([IceCreamEntry,Flavor])
class FitBitApiServiceSpec extends Specification {

    def fitBitApiService
    def flavor
    def iceCreamEntry
    def oauthService
    def token

    def setup() {
        oauthService = Mock(OauthService)
        token = Mock(Token)

        fitBitApiService = new FitBitApiService()
        fitBitApiService.oauthService = oauthService

        flavor = new Flavor()
        flavor.name = 'Chocolate'

        iceCreamEntry = new IceCreamEntry()
        iceCreamEntry.flavor = flavor
    }

    def cleanup() {
    }

    void "it should call to Food Log Endpoint"() {
        when:
            fitBitApiService.logIceCreamEntry(token, iceCreamEntry)

        then:
            1 * oauthService.methodMissing(*_) >> { args ->
                def requestUrl = args[1][1]
                assert requestUrl == fitBitApiService.FOOD_LOG_ENDPOINT
            }
    }

    void "it should POST request"() {
        when:
            fitBitApiService.logIceCreamEntry(token, iceCreamEntry)

        then:
            1 * oauthService.methodMissing(*_) >> { args ->
                def methodName = args[0]
                assert methodName == 'postFitBitResource'
            }
    }

    void "it should POST parameters"() {
        when:
            fitBitApiService.logIceCreamEntry(token, iceCreamEntry)

        then:
            1 * oauthService.methodMissing(*_) >> { args ->
                def requestParameters = args[1][2]
                assert requestParameters.foodName == "$flavor.name Ice Cream"
                assert requestParameters.mealTypeId == '7'
                assert requestParameters.unitId == '301'
                assert requestParameters.amount == iceCreamEntry.scoops.toString()
                assert requestParameters.date == new Date().format('yyyy-MM-dd')
            }
    }
}
