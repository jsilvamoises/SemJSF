/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.conversores;

import com.jsilvamoises.controller.MBTipoLogradouro;
import com.jsilvamoises.model.entities.TipoLogradouro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = TipoLogradouro.class)
public class TipoLogradouroConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(!string.isEmpty()){
            return new MBTipoLogradouro().getTipoLogradouroById(Long.valueOf(string));
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o!=null){
            return String.valueOf(((TipoLogradouro)o).getId());
            
        }else{
            return null;
        }
    }

   
    
}
