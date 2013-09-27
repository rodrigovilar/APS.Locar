package br.ufpb.aps.locar;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class RestaurarObjeto {
	
	public static Object restaurar(String caminho) {
		 
        Object objeto = null;
       
        try {
               FileInputStream restFile = new FileInputStream(caminho);
               ObjectInputStream stream = new ObjectInputStream(restFile);

               // recupera o objeto
               objeto = stream.readObject();

               stream.close();
               } catch (Exception e) {
            	   e.printStackTrace();
            	   }
        return objeto;
        }
}