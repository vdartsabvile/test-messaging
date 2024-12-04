package io.vdartsabvile

import atlantafx.base.theme.CupertinoDark
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.geometry.Pos
import javafx.scene.layout.*
import javafx.stage.Stage
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.stage.StageStyle

class ConnectionWindow {
    private var xOffset = 0.0
    private var yOffset = 0.0

    fun openConnectionForm(primaryStage: Stage) {
        val titleBar = HBox()
        titleBar.style = "-fx-background-color: #333; -fx-padding: 5;"
        titleBar.alignment = Pos.CENTER_RIGHT
        val icon = javafx.scene.image.Image("icon.png") // Укажите путь к вашей иконке
        val iconView = javafx.scene.image.ImageView(icon)
        iconView.fitWidth = 34.0 // Установите ширину иконки
        iconView.fitHeight = 34.0 // Установите высоту иконки
        iconView.isPreserveRatio = true // Сохраняем пропорции
        HBox.setMargin(iconView, javafx.geometry.Insets(0.0, 0.0, 0.0, 8.0)) // 5 пикселей отступа слева

        val label2 = Label("AVSpeak")
        label2.style = "-fx-font-family: 'Secession [by me]' ;-fx-font-size: 34px; -fx-font-weight: regular;" // Устанавливаем размер шрифта и жирный стиль


        // Кнопка закрытия
        val closeButton = Button("✖")
        closeButton.style = "-fx-text-fill: white; -fx-font-size: 16px; -fx-min-width: 40px; -fx-min-height: 30px;"
        closeButton.setOnAction { primaryStage.close() }

        // Кнопка сворачивания
        val minimizeButton = Button("−")
        minimizeButton.style = "-fx-text-fill: white; -fx-font-size: 16px; -fx-min-width: 40px; -fx-min-height: 30px;"
        minimizeButton.setOnAction { primaryStage.isIconified = true}

        // Добавление кнопок на панель заголовка
        // Устанавливаем выравнивание кнопок вправо
        HBox.setHgrow(closeButton, Priority.ALWAYS)
        HBox.setHgrow(minimizeButton, Priority.ALWAYS)
        titleBar.children.addAll(iconView, label2)

        // Заполнение пространства слева
        val spacer = Region()
        HBox.setHgrow(spacer, Priority.ALWAYS)

        // Добавляем пробел, чтобы кнопки были справа
        titleBar.children.addAll(spacer, minimizeButton, closeButton)

        // Выравнивание
        titleBar.alignment = Pos.CENTER_LEFT

        // Перетаскивание окна
        titleBar.setOnMousePressed { event: javafx.scene.input.MouseEvent ->
            xOffset = event.sceneX
            yOffset = event.sceneY
        }
        titleBar.setOnMouseDragged { event: javafx.scene.input.MouseEvent ->
            primaryStage.x = event.screenX - xOffset
            primaryStage.y = event.screenY - yOffset
        }

        // Кнопка назад
        val backButton = Button("Назад")
        backButton.setOnAction {
            // Возвращаемся на главный экран
            //val mainApp = MainApp()
            //mainApp.start(primaryStage)
        }



        // Создание формы для подключения
        val ipLabel = Label("Введите IP сервера:")
        val ipField = TextField()
        ipField.promptText = "IP-адрес"

        val portLabel = Label("Введите порт сервера:")
        val portField = TextField()
        portField.promptText = "Порт"

        val connectButton = Button("Подключиться")
        connectButton.setOnAction {
            // Здесь можно добавить логику подключения по IP и порту
            println("Подключение к серверу ${ipField.text}:${portField.text}")
        }


        // Создаем VBox для формы
        val formBox = VBox(10.0)
        formBox.children.addAll(ipLabel, ipField, portLabel, portField, connectButton, backButton)
        formBox.alignment = Pos.CENTER

        // Обновляем содержимое окна
        val root = BorderPane()
        root.center = formBox
        root.top = titleBar

        val scene = Scene(root, 1600.0, 900.0)
        scene.stylesheets.add(CupertinoDark().userAgentStylesheet)

        primaryStage.scene = scene
        primaryStage.show()


    }
}
