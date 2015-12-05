

import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
 /* @author Artur
 */
object User {
  
  def main(args: Array[String]): Unit = {
    
    val address = "localhost"
    val port = 10001  
    val sock = new Socket(address, port)  
    val is = new BufferedReader(new InputStreamReader(sock.getInputStream()))
    val os = new PrintStream(sock.getOutputStream())
    var flag = true
    actors.Actor.actor {
      while(flag){
        if(is.ready){
          val output = is.readLine
          println(output)
        }
        Thread.sleep(200)
      }
    }
    while(flag){
      val input = readLine
      if(input == "quit") flag = false
      else os.println(input)
    }
    sock.close()
  }
}