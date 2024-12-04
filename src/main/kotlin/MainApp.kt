import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.*
import java.time.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(WebSockets)
        routing {
            val clients = mutableListOf<DefaultWebSocketServerSession>()

            webSocket("/chat") {
                clients += this
                try {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText()
                            // Рассылка всем клиентам
                            clients.forEach { client ->
                                client.send("User: $receivedText")
                            }
                        }
                    }
                } finally {
                    clients -= this
                }
            }
        }
    }.start(wait = true)
}
