package dev.felipebill.pudo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import dev.felipebill.pudo.model.Delivery;
import dev.felipebill.pudo.model.Vehicle;
import dev.felipebill.pudo.repository.IVehicleRepository;

@Service
public class DeliveryDispatcherService {

	private final IVehicleRepository vehicleRepository;
	
	public DeliveryDispatcherService(final IVehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	public void dispatchDelivery(Delivery delivery) {
        // Obtém todos os veículos disponíveis
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
        
        // Converte o Iterable para uma lista para facilitar o acesso por índice
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicles.forEach(vehicleList::add);

        // Verifica se há veículos disponíveis
        if (vehicleList.isEmpty()) {
            throw new IllegalStateException("No vehicles available for delivery");
        }

        // Sorteia um veículo aleatoriamente
        Random random = new Random();
        int randomIndex = random.nextInt(vehicleList.size());
        Vehicle selectedVehicle = vehicleList.get(randomIndex);

        // Atribui a entrega ao veículo sorteado
        selectedVehicle.assignDelivery(delivery);

        System.out.println(delivery.getVehicle());
        
        // Salva o estado atualizado do veículo no repositório (se necessário)
        this.vehicleRepository.save(selectedVehicle);
        
        

        System.out.println("Delivery " + delivery.getId() + " assigned to vehicle " + selectedVehicle.getId());
    }
}