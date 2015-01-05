/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilvamoises.conversores;

import com.jsilvamoises.controller.MBTipoCodigoBarra;
import com.jsilvamoises.model.entities.TipoCodigoBarra;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Moises
 */
@FacesConverter(forClass = TipoCodigoBarra.class)
public class TipoCodigoBarraConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(!string.isEmpty()){
            return new MBTipoCodigoBarra().getTipoCodigoBarraById(Long.valueOf(string));
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o!=null){
            return  String.valueOf(((TipoCodigoBarra)o).getId());
        }else{
            return null;
        }
    }

   
    
}
