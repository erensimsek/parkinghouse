package com.eren.parkinghouse.view.converter;

import com.eren.parkinghouse.domain.ParkingHouse;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by esimsek on 10/7/2016.
 */
//@FacesConverter(value = "parkingHouseConverter")
public class ParkingHouseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String str) {
        ParkingHouse parkingHouse = new ParkingHouse();
        if (str != null && !str.trim().isEmpty()) {
            parkingHouse.setId(Long.valueOf(str));
            return parkingHouse;
        }
        return parkingHouse;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {
        String str = "";
        //str = value.toString();
        if (value instanceof ParkingHouse) {
            ParkingHouse parkingHouse = (ParkingHouse) value;
            if (parkingHouse != null && parkingHouse.getId() != null) {
                str = parkingHouse.getId().toString();
            }
        }
        return str;
    }
}

