import java.awt.Dimension
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket
import scala.swing.BorderPanel
import scala.swing.MainFrame
import scala.swing.ScrollPane
import scala.swing.TextArea
import scala.swing.TextField
import scala.swing.event.EditDone
import scala.swing.Button
import javax.swing.Action
import scala.swing.event.ButtonClicked
import javax.swing.text.DefaultCaret
import scala.swing.FlowPanel


 /* @author Artur
 */
object UserInterface {
  
  def main(args: Array[String]): Unit = {
    var dialogue = ""
    
    val address = "localhost"
    val port = 10001  
    val sock = new Socket(address, port)  
    val is = new BufferedReader(new InputStreamReader(sock.getInputStream()))
    val os = new PrintStream(sock.getOutputStream())
    val textArea = new TextArea
    val textArea2 = new TextArea
    val buttonSave = new Button("Salvar") {
      listenTo(this)
      reactions += {
        case ButtonClicked(b) => WriteToFile.wirteToFile(dialogue)
      }
    }
    val buttonUsers = new Button("Usuarios") {
      listenTo(this)
      reactions += {
        case ButtonClicked(a) => textArea2.append(SaveUser.show())
      }
    }
    val textField = new TextField {
      listenTo(this)
      reactions += {
        case e: EditDone =>
          if(text.nonEmpty){
            os.println(text)
            text = ""
          }
      }
    }
    
    val frame = new MainFrame{
      title = "Chat Scala"
      contents = new BorderPanel {
        layout += new FlowPanel(textArea) -> BorderPanel.Position.North
        layout += new ScrollPane(textArea2) -> BorderPanel.Position.Center
        layout += textField -> BorderPanel.Position.South
        layout += buttonSave -> BorderPanel.Position.East
        layout += buttonUsers -> BorderPanel.Position.West
      }
      size = new Dimension(400, 600)
      centerOnScreen
    }
    
    var flag = true
    actors.Actor.actor {
      while(flag){
        if(is.ready){
          val output = is.readLine
          dialogue += output + " @@@ "
          textArea.append(output + "\n")
        }
        Thread.sleep(200)
      }
    }
    frame.open
  }
}