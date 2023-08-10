import com.deflatedpickle.flatlaf.fonts.opendyslexic.FlatOpenDyslexicFont
import com.deflatedpickle.flatlaf.intellijthemes.FlatCatppuccinMacchiatoIJTheme
import com.deflatedpickle.paleta.PaletteWindow
import com.formdev.flatlaf.FlatLaf
import javax.swing.JFrame

fun main() {
    FlatOpenDyslexicFont.install()
    FlatLaf.setPreferredFontFamily(FlatOpenDyslexicFont.FAMILY)

    FlatCatppuccinMacchiatoIJTheme.setup()

    val frame = JFrame()
    frame.title = "Paleta Example"

    PaletteWindow(frame)

    frame.setSize(400, 500)
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}