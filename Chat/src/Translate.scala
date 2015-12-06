import scala.collection.mutable.Map

/**
 * @author Danielle
 */

object Translate {
  def translate(language: String, input: String) = language match{
    case "P" => toPort(input)
    case "E" => toEng(input)
    case _ =>
  }
  

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