/*
 * Universidade Federal do ABC
 * 
 * Discentes:
 * Arthur Monteforte
 * Danielle Lazzer Lucarini
 * Laís Marinho de Souza
 * 
 * Disciplina: Paradigmas de Programação
 * 
 * 3º Quadrimestre 2015
 * 
 */

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

/*
 * Classe da interface gráfica do usuário. Ela possui a interface do usuário e métodos para se conectar
 * com o servidor, detectar ações (edição de campos de textos e clique de botões) para realizar as ações
 * correspondentes, e também checa indefinidamente se há novas mensagens enviadas pelo servidor para exibir
 * no campo de texto correspondente.
 */
object UserInterface {
  
  def main(args: Array[String]): Unit = {
    var dialogue = ""
    
    val address = "localhost"
    val port = 10001  
    val socket = new Socket(address, port)  
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    val out = new PrintStream(socket.getOutputStream())
    val receivedMessagesArea = new TextArea
    
    // Botão para salvar a conversa num arquivo de texto .txt.
    val buttonSave = new Button("Salvar") {
      listenTo(this)
      reactions += {
        case ButtonClicked(b) => WriteToFile.writeToFile(dialogue)
      }
    }
    
    // Campo de texto que detecta se a edição terminou, e caso o texto editado não seja vazio, envia pro servidor
    val textField = new TextField {
      listenTo(this)
      reactions += {
        case e: EditDone =>
          if(text.nonEmpty){
            out.println(text)
            text = ""
          }
      }
    }
    
    // Organização da interface gráfica do usuário
    val frame = new MainFrame{
      title = "Scala Chat UFABC"
      contents = new BorderPanel {
        layout += new ScrollPane(receivedMessagesArea) -> BorderPanel.Position.Center
        layout += textField -> BorderPanel.Position.South
        layout += buttonSave -> BorderPanel.Position.East
      }
      size = new Dimension(400, 600)
      centerOnScreen
    }
    
    /*
     * Cria um ator para executar indefinidamente, recebendo as mensagens do servidor e adicionando-as
     * no campo de texto correspondente e na variável 'dialogue', que guarda toda a conversa desde que o
     * usuário em questão se conectou.
     */
    actors.Actor.actor {
      while(true){
        if(in.ready){
          val output = in.readLine
          dialogue += output + " @@@ "
          receivedMessagesArea.append(output + "\n")
        }
        Thread.sleep(200)
      }
    }
    frame.open
  }
}