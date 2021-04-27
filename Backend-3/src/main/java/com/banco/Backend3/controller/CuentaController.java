package com.banco.Backend3.controller;

import com.banco.Backend3.ResourceNotFoundException;
import com.banco.Backend3.model.Compra;
import com.banco.Backend3.model.Cuenta;
import com.banco.Backend3.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    CuentaRepository cuentaRepository;



    @PostMapping("/compra")
    @CrossOrigin()
    public ResponseEntity<Boolean> checkCompra(@RequestBody Compra compra) throws ResourceNotFoundException {
        Cuenta cuenta =  cuentaRepository.findById(compra.getNoTarjeta())
                .orElseThrow(() -> new ResourceNotFoundException("No hay una cuenta con este ID: " + compra.getNoTarjeta()));
        if(cuenta.getSaldo() >= compra.getMonto()){
            return ResponseEntity.ok().body(true);
        }else{
            return ResponseEntity.ok().body(false);
            //return ResponseEntity.
        }
    }

    @PostMapping("/datos")
    @CrossOrigin()
    public ResponseEntity<Cuenta> checkLogin(@RequestBody Cuenta tarjeta) throws ResourceNotFoundException{
        Cuenta cuenta = cuentaRepository.findById(tarjeta.getNoTarjeta())
                .orElseThrow(() -> new ResourceNotFoundException("No hay una cuenta con este ID: " + tarjeta.getNoTarjeta() + ":)"));

        if(tarjeta.getPin().equals(cuenta.getPin()))
            return ResponseEntity.ok().body(cuenta);
        else
            return ResponseEntity.badRequest().build();

    }

    @PutMapping("/actualizar")
    @CrossOrigin()
    public ResponseEntity<Cuenta> updateData(@RequestBody Cuenta tarjeta) throws ResourceNotFoundException{
        Cuenta cuenta = cuentaRepository.findById(tarjeta.getNoTarjeta())
                .orElseThrow(() -> new ResourceNotFoundException("No hay una cuenta con este ID: " + tarjeta.getNoTarjeta()));
        cuenta.setSaldo(tarjeta.getSaldo());

        return ResponseEntity.ok(cuentaRepository.save(cuenta));
    }


}
