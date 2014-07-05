package br.ufg.pw.entidades.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufg.pw.entidades.Obstaculo;

@FacesConverter(value = "obstaculoConverter", forClass = Obstaculo.class)
public class ObstaculoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value != null && value.trim().length() > 0) {
			Obstaculo obs = new Obstaculo();
			obs.setNome( value );
			return obs;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value != null) {
			String nome = ( String ) ((Obstaculo) value).getNome();
			return nome;
		}
		return null;
	}

}
