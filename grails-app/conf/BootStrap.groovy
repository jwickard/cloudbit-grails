import com.github.jwickard.cloudbitgrails.Flavor
import com.github.jwickard.cloudbitgrails.Role

class BootStrap {

    def init = { servletContext ->
//        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
//        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
//
//        def chocolate = Flavor.findByName('Chocolate') ?: new Flavor(name: 'Chocolate').save(failOnError: true)
//        def vanilla = Flavor.findByName('Vanilla') ?: new Flavor(name: 'Vanilla').save(failOnError: true)
//        def strawberry = Flavor.findByName('Strawberry') ?: new Flavor(name: 'Strawberry').save(failOnError: true)
    }
    def destroy = {
    }
}
