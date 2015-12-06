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


object WriteToFile {
  def wirteToFile(text: String) {
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

  
  def getCurrentDateAndTime(): String = {
    val out = Calendar.getInstance.getTime
    out.toString().replace(":", "-")
  }
}

