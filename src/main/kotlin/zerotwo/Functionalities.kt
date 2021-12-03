package zerotwo
import khttp.get as httpGet
import khttp.post as httpPost

fun w2g(video: String) : String {
    val response = httpPost(
        url = "https://w2g.tv/rooms/create.json",
        json = mapOf("w2g_api_key" to "i37lhu79syq2diq7pr335fwiv8uvdbb4zs3e6gj3uhawqokt1d1pm1n6fh5a2sg2",
            "share" to video, "bg_color" to "#000000", "bg_opacity" to "50"))

    return response.text.split(",")[1]
        .substring(12).replace("\"","")
}

fun redditMeme() : String {
    val response = httpGet(url = "https://reddit-meme-api.herokuapp.com/")
    return if (response.statusCode == 200)
        "**"+response.jsonObject.getString("title")+ "**\n" + response.jsonObject.getString("url")
    else
        "Joa top, das funktioniert ja nich so wie es eigentlich sollte..."
}