package com.travelagency.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private String name;
    private String location;
    private Double rating;
    private List<Room> rooms = new ArrayList<>();
}