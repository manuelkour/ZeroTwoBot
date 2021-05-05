

import dev.kord.core.*
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.event.message.ReactionAddEvent
import kotlinx.coroutines.delay

suspend fun main(args: Array<String>) {
    val BOT_TOKEN = args[0]
    val kord = Kord(BOT_TOKEN)

    // "Passive" Youtube Annoucement Funktion (Rebecca Elizabeth)
    kord.on<ReadyEvent> {
        while(true) {
            youtube(kord, args[1])
        // reactionRoles(kord)
        }
    }

    // Message Events bzw. aktiv ausgelöste Events von Usern
    kord.on<MessageCreateEvent> {

        if(message.author?.isBot != false) return@on

        //W2G Room (Base Case ist irgendein random Video von Sawano)
        if(message.content.contains("!w2g")) {
            when (message.content.length <= 4) {
                true -> message.channel.createMessage("https://w2g.tv/rooms/" + w2g("https://www.youtube.com/watch?v=4ja403hodxM"))
                false -> message.channel.createMessage("https://w2g.tv/rooms/" + w2g(message.content.substring(5)))
            }
        }
    }

    kord.on<ReactionAddEvent> {

    }



    kord.login()

}