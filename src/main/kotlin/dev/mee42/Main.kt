package dev.mee42

import discord4j.core.DiscordClient
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.reactor.mono
import reactor.util.Loggers

private val logger = Loggers.getLogger("Main")
@FlowPreview
fun main(argv: Array<String>) {
    logger.info("starting up")
    val token = argv[0]
    val database = argv[1]
    Database.file(database)
    val client = DiscordClient.create(token)
    client.withGateway { mono {
        processCommands(it)
    } }.block()
}

