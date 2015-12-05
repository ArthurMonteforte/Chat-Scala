import java.io.BufferedInputStream
import java.io.PrintStream
import java.net.Socket
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.BufferedOutputStream
import java.net.ServerSocket
import collection.mutable

/**
 * @author Artur
 */
object Chat {
  case class User(sock:Socket, is: BufferedReader, ps: PrintStream, name: String)
  
  
  def main(args: Array[String]): Unit = {
    val users = new mutable.ArrayBuffer[User]() with mutable.SynchronizedBuffer[User] {}
    val ss = new ServerSocket(10001)
    actors.Actor.actor {
      while(true){
        val sock = ss.accept()
        val is = new BufferedReader(new InputStreamReader(sock.getInputStream()))
        val os = new PrintStream((sock.getOutputStream()))
        actors.Actor.actor {
          os.println("What is your name?")
          users += User(sock, is, os, is.readLine())
        }
      }
    }    
    while(true){
      for(user <- users){
        if(user.is.ready){
          val input = user.is.readLine
          for(user2 <- users){
            user2.ps.println(user.name + " : " + input)
          }
        }
      }
    }
  }
}
  
