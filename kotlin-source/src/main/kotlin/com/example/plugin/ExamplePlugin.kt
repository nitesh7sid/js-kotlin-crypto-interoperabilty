package com.example.plugin

import com.example.api.ExampleApi
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.serialization.SerializationWhitelist
import net.corda.webserver.services.WebServerPluginRegistry
import java.util.function.Function

class ExamplePlugin : WebServerPluginRegistry, SerializationWhitelist {
    /**
     * A list of classes that expose web APIs.
     */
    override val webApis = listOf(Function(::ExampleApi))

    /**
     * A list of directories in the resources directory that will be served by Jetty under /web.
     */
    override val staticServeDirs = mapOf(
            // This will serve the exampleWeb directory in resources to /web/example
            "example" to javaClass.classLoader.getResource("exampleWeb").toExternalForm()
    )

    override val whitelist: List<Class<*>>
        get() = listOf(sun.security.rsa.RSAPublicKeyImpl::class.java,
                java.math.BigInteger::class.java,
                sun.security.x509.AlgorithmId::class.java)
}
