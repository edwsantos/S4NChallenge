package com.s4n.delivery;

import com.s4n.delivery.core.repository.DeliveryRouteRepository;
import com.s4n.delivery.core.service.DeliveryService;
import com.s4n.delivery.core.service.DeliveryServiceImpl;
import com.s4n.delivery.infrastructure.repository.DeliveryRouteRepositoryImpl;
import com.s4n.delivery.infrastructure.repository.FindRoutesInFileHelper;
import com.s4n.delivery.infrastructure.repository.SaveDroneCoordinatesHelper;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hola!");
        System.out.println("Este es tu programa de entregas de domicilios favorito!");
        System.out.println("Voy a iniciar las entregas basado en las rutas que definiste en los archivos...");

        final DeliveryRouteRepository deliveryRouteRepository = new DeliveryRouteRepositoryImpl(
                new FindRoutesInFileHelper(), new SaveDroneCoordinatesHelper());

        final DeliveryService deliveryService = new DeliveryServiceImpl(deliveryRouteRepository);
        deliveryService.deliver();

    }
}
