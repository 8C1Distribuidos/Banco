package com.banco.Backend3.controller;

import com.banco.Backend3.ResourceNotFoundException;
import com.banco.Backend3.model.Compra;
import com.banco.Backend3.model.Cuenta;
import com.banco.Backend3.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    CuentaRepository cuentaRepository;



    @PostMapping("/compra")
    @CrossOrigin()
    public ResponseEntity<Boolean> checkCompra(@RequestBody Compra compra) throws ResourceNotFoundException {

        if(compra != null){
            Optional<Cuenta> cuentaOp =  cuentaRepository.findById(compra.getNumeroTarjeta());
            Cuenta cuenta;
            if(cuentaOp.isPresent()){
                 cuenta = cuentaOp.get();
                if(cuenta.getSaldo() >= compra.getPrecioFinal() && cuenta.getFechaVencimiento().isAfter(LocalDate.now())){
                    cuenta.setSaldo(cuenta.getSaldo() - compra.getPrecioFinal());
                    cuentaRepository.save(cuenta);
                    return ResponseEntity.ok().body(true);

                }else{
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(false); // 422
                    //return ResponseEntity.
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false); //404
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false); //400


    }

    @PostMapping("/datos")
    @CrossOrigin()
    public ResponseEntity<Cuenta> checkLogin(@RequestBody Cuenta tarjeta) throws ResourceNotFoundException{

        if(tarjeta != null){
            Optional<Cuenta> cuentaOp = cuentaRepository.findById(tarjeta.getNoTarjeta());
            //.orElseThrow(() -> new ResourceNotFoundException("No hay una cuenta con este ID: " + tarjeta.getNoTarjeta() + ":)"));
            Cuenta cuenta;
            if(cuentaOp.isPresent()){
                cuenta = cuentaOp.get();
                if(tarjeta.getPin().equals(cuenta.getPin()))
                    return ResponseEntity.ok().body(cuenta);
                else
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null); // 422
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //404
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //400

    }

    @PutMapping("/actualizar")
    @CrossOrigin()
    public ResponseEntity<Cuenta> updateData(@RequestBody Cuenta tarjeta) throws ResourceNotFoundException{
        if(tarjeta != null){
            Optional<Cuenta> cuentaOp = cuentaRepository.findById(tarjeta.getNoTarjeta());
            Cuenta cuenta;
                    //.orElseThrow(() -> new ResourceNotFoundException("No hay una cuenta con este ID: " + tarjeta.getNoTarjeta()));
            if(cuentaOp.isPresent()){
                cuenta = cuentaOp.get();
                cuenta.setSaldo(tarjeta.getSaldo());

                return ResponseEntity.ok(cuentaRepository.save(cuenta));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //404
            }

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //400
    }


}
