package com.deflatedpickle.paleta

import org.jdesktop.swingx.JXList
import java.awt.Dimension
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JTextField
import javax.swing.SwingUtilities

class PaletteInput(width: Int) : JTextField() {
    init {
        preferredSize = Dimension(width, preferredSize.height)

        addFocusListener(object : FocusAdapter() {
            override fun focusLost(e: FocusEvent) {
                SwingUtilities.getWindowAncestor(this@PaletteInput).isVisible = false
            }
        })

        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent) {
            }
        })
    }
}