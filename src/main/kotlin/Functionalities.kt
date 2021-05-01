import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.entity.channel.MessageChannel
import khttp.get as httpGet
import khttp.post as httpPost

var current_video: String = "https://www.youtube.com/watch?v=wSsEvCxiijs"

fun w2g(video: String) : String {
    val response = httpPost(
        url = "https://w2g.tv/rooms/create.json",
        json = mapOf("w2g_api_key" to "i37lhu79syq2diq7pr335fwiv8uvdbb4zs3e6gj3uhawqokt1d1pm1n6fh5a2sg2",
            "share" to video, "bg_color" to "#000000", "bg_opacity" to "50"))

    return response.text.split(",")[1]
                .substring(12).replace("\"","")
}

suspend fun youtube(bot: Kord, key: String) {
    // Thread.sleep(900000)
    try {
        val response = httpGet("https://www.googleapis.com/youtube/v3/search?key=$key&channelId=UCun2qqzZOOVcuW9hZhaDxmQ&part=snippet,id&order=date&maxResults=1")
        val video = response.text.split(",")[3]
        for (s in response.text.split(","))
            println(s)
        println(response.text.split(",").size)

        val channel : MessageChannel
        channel = bot.getChannel(Snowflake(166884795555643392))?.asChannel() as MessageChannel
        if(!video.equals(current_video)) {
            current_video = video
            channel.createMessage("@everyone Neues Video ist draußen! https://www.youtube.com/watch?v=" + current_video)
        }
    } catch (e: Exception) {
        println("Beim API Call ist etwas falsch gelaufen \n$e")
    }

}