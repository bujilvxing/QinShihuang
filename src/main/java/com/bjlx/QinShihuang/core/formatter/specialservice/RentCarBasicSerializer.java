package com.bjlx.QinShihuang.core.formatter.specialservice;

import java.io.IOException;

import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class RentCarBasicSerializer extends JsonSerializer<RentCar> {

    @Override
    public void serialize(RentCar rentCar, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(RentCar.fd_id, rentCar.getId() == null ? "" : rentCar.getId().toString());
            gen.writeNumberField(RentCar.fd_price, rentCar.getPrice() == null ? 0 : rentCar.getPrice());
            
            Contact contact = rentCar.getContact();
            if (contact != null) {
                gen.writeFieldName(RentCar.fd_contact);
                JsonSerializer<Object> retContact = serializers.findValueSerializer(Contact.class, null);
                retContact.serialize(contact, gen, serializers);
            }

            gen.writeNumberField(RentCar.fd_minRentDay, rentCar.getMinRentDay() == null ? 1 : rentCar.getMinRentDay());

            gen.writeBooleanField(RentCar.fd_autoInsurance, rentCar.getAutoInsurance() == null ? true: rentCar.getAutoInsurance());
            gen.writeNumberField(RentCar.fd_autoInsurancePrice, rentCar.getAutoInsurancePrice() == null ? 0 : rentCar.getAutoInsurancePrice());
            gen.writeBooleanField(RentCar.fd_pickup, rentCar.getPickup() == null ? false : rentCar.getPickup());
            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}