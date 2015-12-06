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

import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter
import java.util.Calendar
import java.io.PrintWriter

/*
 * Classe com os métodos para salvar a conversa num arquivo de texto .txt,
 * colocando a data e hora no nome do arquivo para maior organização.
 */
object WriteToFile {
  
  /*
   * O método writeToFile grava arquivos com o padrão de nomes estabelecido, e substitui os símbolos
   * "@@@" por quebras de linha 
   */
  def writeToFile(text: String) {
    val file = new File("Chat Scala "+ getCurrentDateAndTime() + ".txt")
    val writer = new BufferedWriter(new FileWriter(file))
    val words = text.split(" ")
    for (word <- words) {
       word match {
         case "@@@" => writer.newLine();
         case _ => writer.write(word + " ")
      }
    }
    writer.close()
  }

  /*
   * O método getCurrentDateAndTime obtém a data e hora do momento que é chamado e converte o resultado
   * para uma String, substituindo os símbolos ":" por "-" para a string ser válida para nomes de arquivo.
   */
  def getCurrentDateAndTime(): String = {
    val out = Calendar.getInstance.getTime
    out.toString().replace(":", "-")
  }
}

