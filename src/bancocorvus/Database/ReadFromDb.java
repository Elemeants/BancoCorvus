/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocorvus.Database;

import java.sql.ResultSet;

 
public interface ReadFromDb<T> {
    public T ReadFromDb(ResultSet set); 
}
