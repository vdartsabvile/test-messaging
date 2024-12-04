import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class ChatClient2 : Application() {
    private lateinit var client: WebSocketClient

    override fun start(primaryStage: Stage) {
        val messages = TextArea().apply {
            isEditable = false
        }
        val input = TextField()
        val sendButton = Button("Send").apply {
            setOnAction {
                val text = input.text
                if (text.isNotBlank()) {
                    client.send(text)
                    input.clear()
                }
            }
        }

        val layout = VBox(10.0, messages, input, sendButton)
        val scene = Scene(layout, 400.0, 300.0)

        primaryStage.scene = scene
        primaryStage.title = "WebSocket Chat"
        primaryStage.show()

        connectToServer(messages)
    }

    private fun connectToServer(messages: TextArea) {
        client = object : WebSocketClient(URI("ws://26.9.5.10:8080/chat")) { // Укажите публичный IP-адрес сервера
            override fun onOpen(handshakedata: ServerHandshake?) {
                messages.appendText("Connected to server\n")
            }

            override fun onMessage(message: String?) {
                message?.let {
                    messages.appendText("$it\n")
                }
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                messages.appendText("Disconnected: $reason\n")
            }

            override fun onError(ex: Exception?) {
                messages.appendText("Error: ${ex?.message}\n")
            }
        }
        client.connect()
    }

    override fun stop() {
        client.close()
    }
}

fun main() {
    Application.launch(ChatClient::class.java)
}
