package ru.cristalix.uiengine.example

import dev.xdark.clientapi.ClientApi
import dev.xdark.clientapi.entry.ModMain
import dev.xdark.clientapi.event.input.KeyPress
import ru.cristalix.uiengine.UIEngine
import ru.cristalix.uiengine.element.Item
import ru.cristalix.uiengine.element.Rectangle
import ru.cristalix.uiengine.element.Text
import ru.cristalix.uiengine.element.animate
import ru.cristalix.uiengine.utility.Color
import ru.cristalix.uiengine.utility.V3

class ExampleMod : ModMain {

    override fun load(clientApi: ClientApi) {
        UIEngine.initialize(clientApi)
        var rect = Rectangle(
            size = V3(240.0, 240.0),
            color = Color(alpha = 1.0, red = 0, green = 0, blue = 0),
            children = listOf(
                Text(
                    content = "каргонд блин где 20 миллионов игроков",
                    offset = V3(y = -3.0),
                    align = V3(0.5, 1.0),
                    origin = V3(0.5, 1.0)
                ),
                Item(
                    stack = UIEngine.clientApi.itemRegistry().getItem(198).newStack(1, 0),
                    align = V3(0.5, 0.5),
                    origin = V3(0.5, 0.5),
                    scale = V3(9.0, 9.0, 1.0)
                )
            )
        )

        UIEngine.overlayContext.addChild(rect)

        UIEngine.registerHandler(KeyPress::class.java, {
            rect.animate(0.5) {
                offset.x = 100.0 + Math.random() * 100.0
                offset.y = 100.0 + Math.random() * 100.0
            }
        })

    }

    override fun unload() {
        UIEngine.uninitialize()
    }
}
