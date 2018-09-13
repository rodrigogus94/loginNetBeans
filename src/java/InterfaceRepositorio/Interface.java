/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceRepositorio;

import java.util.List;

/**
 *
 * @author Rodrigo-Casa
 */
public interface Interface <T, G>{
   public void inserir (T t);
   public void alterar(T t);
   public T recuperar (int id);
   public void deletar (T t);
   public List<T> recuperarTodos ();
    
}

