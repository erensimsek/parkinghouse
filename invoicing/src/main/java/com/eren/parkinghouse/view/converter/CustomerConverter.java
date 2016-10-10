package com.eren.parkinghouse.view.converter;

import com.eren.parkinghouse.domain.Customer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by esimsek on 10/7/2016.
 */
//@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String str) {
        Customer customer = new Customer();
        if (str != null && !str.trim().isEmpty()) {
            customer.setId(Long.valueOf(str));
            return customer;
        }
        return customer;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {
        String str = "";
        //str = value.toString();
        if (value instanceof Customer) {
            Customer customer = (Customer) value;
            if (customer != null && customer.getId() != null) {
                str = customer.getId().toString();
            }
        }
        return str;
    }
}

