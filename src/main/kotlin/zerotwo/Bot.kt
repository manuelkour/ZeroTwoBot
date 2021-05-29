import dev.kord.core.*
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.behavior.edit
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.event.message.ReactionAddEvent
import kotlinx.coroutines.delay

import java.nio.file.Files
import java.nio.file.Path

suspend fun main(args: Array<String>) {
    val BOT_TOKEN = Files.readString(Path.of("token.txt"))

    val kord = Kord(BOT_TOKEN)

    //setup bad apple animation
    val badAppleAnim = Files.readAllLines(Path.of ("bad-apple.txt"))

    // "Passive" Youtube Annoucement Funktion (Rebecca Elizabeth)
    kord.on<ReadyEvent> {
        while(true) {
            youtube(kord, args[1])
        // reactionRoles(kord)
        }
    }

    // Message Events bzw. aktiv ausgelöste Events von Usern
    kord.on<MessageCreateEvent> {

        if(message.author?.isBot != false)
            return@on

        //spicy memes
        if(message.content.contains("!meme")) {
            message.channel.createMessage(redditMeme());
        }

        //very bad bad apple animation
        else if(message.content.contains("!apple")) {
            val m = message.channel.createMessage("```\uD83C\uDF4E```")
            for (frame in 0..6572 step 5) {
                var msg = "frame $frame\n"
                for (line in 0..17)
                    msg = msg + badAppleAnim[frame*17+line] + "\n"
                m.edit { content = "```$msg```" }
                delay(700)
            }
        }

        //W2G Room (Base Case ist irgendein random Video von Sawano)
        else if(message.content.contains("!w2g")) {
            if (message.content.length <= 4)
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