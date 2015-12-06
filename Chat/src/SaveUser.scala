import java.util.Calendar
import scala.collection.mutable.Map

  trait userInfo{
    val name: String
    val date: java.util.Date
  }
  

  class newUser(nameUser: String) extends userInfo{
    val name = nameUser
    val date = Calendar.getInstance().getTime()
    
  }

  object SaveUser { 
    val map = scala.collection.mutable.Map[String, java.util.Date]()
    
    def recebeInfo(userName: String) = {
      val u = new newUser(userName)
      verificaInfo(map.keySet.exists(_ == userName), u)
    }
    
    def verificaInfo(x: Boolean, u: newUser) = x match{
      case true => compara (u)
      case false => salvaNovo (u)
    }
    
    def salvaNovo(u: newUser) = {
      map += (u.name -> u.date)
      //show
    }
    
    def compara(u: newUser) = {
      val teste = u.date.compareTo(map(u.name))
      if (teste==1) salvaNovo(u)
    }
    
    def show(): String = {     
      val m = map.mkString("\n")
      //println(m)
      m
    }
}

// date1.compareTo(date2)
// date1>date2 => 1
// date1<date2 => -1
// val date1 = Calendar.getInstance().getTime()