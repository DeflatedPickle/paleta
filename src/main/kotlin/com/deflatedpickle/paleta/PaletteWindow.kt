package com.deflatedpickle.paleta

import com.deflatedpickle.paleta.api.Anchor
import java.awt.BorderLayout
import java.awt.Color
import java.awt.KeyboardFocusManager
import java.awt.Point
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.KeyStroke
import javax.swing.SwingUtilities

class PaletteWindow @JvmOverloads constructor(
    private val parent: JFrame,
    private var anchor: Anchor = Anchor.NORTH,
    paletteWidth: Int = 160,
    openTrigger: KeyStroke = KeyStroke.getKeyStroke(
        KeyEvent.VK_P,
        KeyEvent.CTRL_DOWN_MASK or KeyEvent.SHIFT_DOWN_MASK
    ),
    closeTrigger: KeyStroke = KeyStroke.getKeyStroke(
        KeyEvent.VK_ESCAPE, 0
    )
) : JDialog(parent) {
    private val textInput = PaletteInput(paletteWidth)

    init {
        this.layout = BorderLayout()
        this.isUndecorated = true
        this.background = Color(0, 0, 0, 0)

        add(textInput, BorderLayout.CENTER)

        pack()

        object : WindowAdapter() {
            override fun windowIconified(e: WindowEvent) {
                this@PaletteWindow.isVisible = false
            }
        }.also {
            this.parent.addWindowListener(it)
            this.parent.addWindowStateListener(it)
            this.parent.addWindowFocusListener(it)
        }

        this.parent.addComponentListener(object : ComponentAdapter() {
            override fun componentMoved(e: ComponentEvent) {
                this@PaletteWindow.refresh()
            }

            override fun componentResized(e: ComponentEvent) {
                this@PaletteWindow.refresh()
            }
        })

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher {
            when (KeyStroke.getKeyStrokeForEvent(it)) {
                openTrigger -> {
                    this@PaletteWindow.refresh()
                    this@PaletteWindow.isVisible = !this@PaletteWindow.isVisible
                    return@addKeyEventDispatcher true
                }
                closeTrigger -> {
                    this@PaletteWindow.refresh()
                    this@PaletteWindow.isVisible = false
                    return@addKeyEventDispatcher true
                }
            }

            false
        }
    }

    fun refresh() {
        this.repaint()
        this.revalidate()

        this.location = Point(
            parent.x + parent.width / 2 - this.width / 2,
            when (anchor) {
                Anchor.NORTH -> parent.y + parent.insets.top
                Anchor.CENTRE -> parent.y + parent.height / 2 - this.height / 2
            }
        )
    }

    override fun isVisible(): Boolean {
        SwingUtilities.invokeLater {
            textInput.text = ""
        }
        return super.isVisible()
    }
}