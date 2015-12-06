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

import java.util.Calendar
import scala.collection.mutable.Map

  trait userInfo{
    val name: String
    val date: java.util.Date
  }
  
// newUser extende a trait userInfo
  class newUser(nameUser: String) extends userInfo{
    val name = nameUser
    val date = Calendar.getInstance().getTime() // data atual
  }

  // para salvar novos usuários ou novos acessos de usuários já salvos no Map
  object SaveUser { 
    val map = scala.collection.mutable.Map[String, java.util.Date]()
    
    // recebe o nome do usuário a partir do Server
    // e cria um novo objeto da classe newUser:
    def recebeInfo(userName: String) = {
      val u = new newUser(userName)
      // verifica se já existe um usuário com esse nome no Map:
      verificaInfo(map.keySet.exists(_ == userName), u) 
    }
    
    def verificaInfo(x: Boolean, u: newUser) = x match{
      case true => compara (u)     // se já existir, compara as datas de cada
      case false => salvaNovo (u)  // se não existir, salva o novo usuário
    }
    
    def salvaNovo(u: newUser) = {
      map += (u.name -> u.date)
      show
    }
    
    def compara(u: newUser) = {
      // compareTo retorna 1 se a data do novo usuário é maior que a data já existente
      // retorna -1 se a data do novo usuário é menor que a data já existente
      val teste = u.date.compareTo(map(u.name))  
      if (teste==1) salvaNovo(u)  // data maior, ou seja, mais recente, salva novo usuário
    }
    
    // exibe no console o Map atualizado com os usuários online
    // e sua última data de acesso
    def show() = {     
      val m = map.mkString("\n")  // salva em uma String o valor do Map
      println("****Usuários online****")
      println(m)
    }
    
}