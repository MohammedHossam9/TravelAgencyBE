package com.travelagency.hotel.config;

import com.travelagency.hotel.models.Hotel;
import com.travelagency.hotel.models.Room;
import com.travelagency.hotel.models.RoomType;
import com.travelagency.hotel.storage.HotelStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** Initializes database with sample hotel data **/
@Component
public class DataInitializer implements CommandLineRunner {

    private final HotelStorage hotelStorage;

    @Autowired
    public DataInitializer(HotelStorage hotelStorage) {
        this.hotelStorage = hotelStorage;
    }

    @Override
    public void run(String... args) {
        if (hotelStorage.findAll().isEmpty()) {
            List<Hotel> hotels = new ArrayList<>();

            // Cairo
            Hotel fourSeasons = new Hotel();
            fourSeasons.setName("Four Seasons Nile Plaza");
            fourSeasons.setLocation("Cairo");
            fourSeasons.setRating(5.0);
            
            List<Room> fourSeasonsRooms = new ArrayList<>();
            fourSeasonsRooms.add(createRoom(RoomType.SINGLE, 200.0));
            fourSeasonsRooms.add(createRoom(RoomType.DOUBLE, 300.0));
            fourSeasonsRooms.add(createRoom(RoomType.FAMILY, 500.0));
            fourSeasons.setRooms(fourSeasonsRooms);
            hotels.add(fourSeasons);

            // Luxor
            Hotel hiltonLuxor = new Hotel();
            hiltonLuxor.setName("Hilton Luxor Resort");
            hiltonLuxor.setLocation("Luxor");
            hiltonLuxor.setRating(4.5);
            
            List<Room> hiltonRooms = new ArrayList<>();
            hiltonRooms.add(createRoom(RoomType.SINGLE, 150.0));
            hiltonRooms.add(createRoom(RoomType.DOUBLE, 250.0));
            hiltonRooms.add(createRoom(RoomType.FAMILY, 400.0));
            hiltonLuxor.setRooms(hiltonRooms);
            hotels.add(hiltonLuxor);

            // Sharm El Sheikh
            Hotel ritzcarlton = new Hotel();
            ritzcarlton.setName("The Ritz-Carlton Sharm");
            ritzcarlton.setLocation("Sharm El Sheikh");
            ritzcarlton.setRating(4.9);
            
            List<Room> ritzRooms = new ArrayList<>();
            ritzRooms.add(createRoom(RoomType.SINGLE, 230.0));
            ritzRooms.add(createRoom(RoomType.DOUBLE, 330.0));
            ritzRooms.add(createRoom(RoomType.FAMILY, 550.0));
            ritzcarlton.setRooms(ritzRooms);
            hotels.add(ritzcarlton);

            // Alexandria
            Hotel fourSeasonsAlex = new Hotel();
            fourSeasonsAlex.setName("Four Seasons Alexandria");
            fourSeasonsAlex.setLocation("Alexandria");
            fourSeasonsAlex.setRating(4.8);
            
            List<Room> alexRooms = new ArrayList<>();
            alexRooms.add(createRoom(RoomType.SINGLE, 190.0));
            alexRooms.add(createRoom(RoomType.DOUBLE, 290.0));
            alexRooms.add(createRoom(RoomType.FAMILY, 480.0));
            fourSeasonsAlex.setRooms(alexRooms);
            hotels.add(fourSeasonsAlex);

            // Hurghada
            Hotel marriott = new Hotel();
            marriott.setName("Marriott Hurghada");
            marriott.setLocation("Hurghada");
            marriott.setRating(4.6);
            
            List<Room> marriottRooms = new ArrayList<>();
            marriottRooms.add(createRoom(RoomType.SINGLE, 170.0));
            marriottRooms.add(createRoom(RoomType.DOUBLE, 270.0));
            marriottRooms.add(createRoom(RoomType.FAMILY, 420.0));
            marriott.setRooms(marriottRooms);
            hotels.add(marriott);

            hotelStorage.saveAll(hotels);
        }
    }

    private Room createRoom(RoomType type, Double price) {
        return new Room(type, price, true, null);
    }
}