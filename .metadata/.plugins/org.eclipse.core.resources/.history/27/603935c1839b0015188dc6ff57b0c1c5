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
  
  def toPort(input: String) = {
    val words = input.split(" ")
    val dic = Map("hi"->"oi","how"->"como","are"->"esta","you"->"vc")
    var traducao = ""
    for(i <- 0 to words.length-1){
      dic contains words(i) match{
        case true => traducao = traducao.concat(dic(words(i)) + " ")
        case false => traducao = traducao.concat("["+words(i)+"]")
      }
    }
    traducao
    println(traducao)
  }
  
  def toEng(input: String) = {
    
  }
}