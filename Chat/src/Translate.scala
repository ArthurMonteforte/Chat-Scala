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

import scala.collection.mutable.Map

// recebe o idioma selecionado e a mensagem digitada pelo usuário
// se usuário escolheu P as mensagens que ele vai receber serão em português
// se usuário escolheu E as mensagens que ele vai receber serão em inglês
object Translate {
  def translate(language: String, input: String) = language match{
    case "P" => toPort(input)
    case "E" => toEng(input)
    case _ =>
  }
  
// traduz as mensagens recebidas em inglês para português e retorna a tradução
  def toPort(input: String): String = {
    val words = input.split(" ")
    val dic = Map("hi"->"oi","how"->"como","are"->"esta","you"->"vc","i'm"->"eu estou","fine"->"bem","and"->"e","too"->"tambem")
    var traducao = ""
    for(i <- 0 to words.length-1){
      dic contains words(i) match{
        case true => traducao = traducao.concat(dic(words(i)) + " ")
        case false => traducao = traducao.concat("["+words(i)+"]")
      }
    }
    traducao
  }

  // traduz as mensagens recebidas em português para inglês e retorna a tradução
  def toEng(input: String): String = {
     val words = input.split(" ")
    val dic = Map("oi"->"hi","como"->"how","esta"->"are","vc"->"you","eu estou"->"i'm","bem"->"fine","e"->"and","tambem"->"too")
    var traducao = ""
    for(i <- 0 to words.length-1){
      dic contains words(i) match{
        case true => traducao = traducao.concat(dic(words(i)) + " ")
        case false => traducao = traducao.concat("["+words(i)+"]")
      }
    }
    traducao
  }
}