package zerotwo

import com.kotlindiscord.kord.extensions.commands.converters.impl.string
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.commands.slash.AutoAckType
import com.kotlindiscord.kord.extensions.extensions.Extension
import dev.kord.common.annotation.KordPreview

// Invite Links mit Permissions https://discord.com/api/oauth2/authorize?client_id=816306640089120808&permissions=2147485760&scope=bot%20applications.commands

@OptIn(KordPreview::class)
class ZeroTwoMain : Extension() {
    override val name = "Watch2Gether"

    override suspend fun setup() {
        slashCommand(::SlapSlashArgs) {
            name = "w2g"
            description = "Gönn dir."

            // We want to send a public follow-up - KordEx will handle the rest
            autoAck = AutoAckType.PUBLIC

            guild(TEST_SERVER_ID)  // Otherwise it'll take an hour to update

            action {
                publicFollowUp {
                    content = "Gönnt euch: https://w2g.tv/rooms/" + w2g(arguments.video)
                }
            }
        }
    }

    inner class SlapSlashArgs : Arguments() {
        val video by string("video", "video link") { _, value ->
            if (!value.contains("youtube"))
                println("Not a Youtube link.")
        }
    }
}

