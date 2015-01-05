/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.conversores;

import com.jsilvamoises.controller.MBTipoAcesso;
import com.jsilvamoises.model.entities.TipoAcesso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = TipoAcesso.class)
public class TipoAcessoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(!string.isEmpty()){
            return new MBTipoAcesso().getTipoAcessoById(Long.valueOf(string));
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o!=null){
            return  String.valueOf(((TipoAcesso)o).getId());
        }else{
            return null;
        }
    }

   
    
}
