/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
public class cartDTO implements Serializable{
        private Map<String, BookDTO> items;

    public cartDTO(Map<String, BookDTO> items) {
        this.items = items;
    }

    public cartDTO() {
    }

    public Map<String, BookDTO> getItems() {
        return items;
    }

    public void setItems(Map<String, BookDTO> items) {
        this.items = items;
    }

    public void add(BookDTO dto){
        if(this.items == null){
            items = new HashMap<>();
        }
        if(this.items.containsKey(dto.getBookID())){
            int quantity = items.get(dto.getBookID()).getQuantity();
            dto.setQuantity(quantity + 1 );
        }
        items.put(dto.getBookID(), dto);
    }
    public void delete(String id){
        if(this.items == null){
            return ;
        }
        if(this.items.containsKey(id)){
            this.items.remove(id);
        }
    }
    public void update(String id, BookDTO dto){
        if(items != null){
            if(items.containsKey(id)){
                this.items.replace(id, dto);
            }
        }
    }
}
