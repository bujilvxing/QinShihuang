package com.bjlx.QinShihuang.core.formatter.specialservice;

import com.bjlx.QinShihuang.model.account.RealNameInfo;
import com.bjlx.QinShihuang.model.specialservice.Car;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CarSerializer extends JsonSerializer<Car> {

    @Override
    public void serialize(Car car, JsonGenerator gen, SerializerProvider serializers) {
        try {
            gen.writeStartObject();
            gen.writeStringField(Car.fd_id, car.getId() == null ? "" : car.getId().toString());
            gen.writeStringField(Car.fd_carId, car.getCarId() == null ? "" : car.getCarId());
            gen.writeNumberField(Car.fd_transmission, car.getTransmission() == null ? 2 : car.getTransmission());
            if(car.getVehicleType() != null)
                gen.writeStringField(Car.fd_vehicleType, car.getVehicleType());
            if(car.getBrand() != null)
                gen.writeStringField(Car.fd_brand, car.getBrand());
            RealNameInfo carOwner = car.getCarOwner();
            if (carOwner != null) {
                gen.writeFieldName(Car.fd_carOwner);
                JsonSerializer<Object> retCarOwner = serializers.findValueSerializer(RealNameInfo.class, null);
                retCarOwner.serialize(carOwner, gen, serializers);
            }

            if(car.getDisplacement() != null)
                gen.writeNumberField(Car.fd_displacement, car.getDisplacement());

            if(car.getSeatNum() != null)
                gen.writeNumberField(Car.fd_seatNum, car.getSeatNum());

            if(car.getName() != null)
                gen.writeStringField(Car.fd_name, car.getName());

            if(car.getFuelType() != null)
                gen.writeStringField(Car.fd_fuelType, car.getFuelType());

            if(car.getGasolineType() != null)
                gen.writeStringField(Car.fd_gasolineType, car.getGasolineType());

            if(car.getActuationType() != null)
                gen.writeStringField(Car.fd_actuationType, car.getActuationType());

            if(car.getDormer() != null)
                gen.writeBooleanField(Car.fd_dormer, car.getDormer());

            if(car.getGps() != null)
                gen.writeBooleanField(Car.fd_gps, car.getGps());

            if(car.getSeatType() != null)
                gen.writeStringField(Car.fd_seatType, car.getSeatType());

            if(car.getAirbagNum() != null)
                gen.writeNumberField(Car.fd_airbagNum, car.getAirbagNum());

            if(car.getGearboxType() != null)
                gen.writeStringField(Car.fd_gearboxType, car.getGearboxType());

            if(car.getAirConditioner() != null)
                gen.writeBooleanField(Car.fd_airConditioner, car.getAirConditioner());

            gen.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}