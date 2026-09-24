package com.example.pedidos360_bff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bff")
public class BffController {

    @FeignClient(name = "ms-orders", url = "${ms.orders.url}")
    interface OrdersClient {
        @GetMapping("/api/orders") Object getOrders();
        @PostMapping("/api/orders") Object createOrder(@RequestBody Object order);
        @PutMapping("/api/orders/{id}/estado") Object changeState(@PathVariable("id") Long id, @RequestParam("nuevoEstado") String estado);
    }

    @FeignClient(name = "ms-catalog", url = "${ms.catalog.url}")
    interface CatalogClient {
        @GetMapping("/api/catalog/productos") Object getCatalog();
    }

    @Autowired
    private OrdersClient ordersClient;
    @Autowired private CatalogClient catalogClient;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() { return ResponseEntity.ok(ordersClient.getOrders()); }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Object order) { return ResponseEntity.ok(ordersClient.createOrder(order)); }

    @PutMapping("/orders/{id}/estado")
    public ResponseEntity<?> changeOrderState(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(ordersClient.changeState(id, nuevoEstado));
    }

    @GetMapping("/catalog")
    public ResponseEntity<?> getCatalog() { return ResponseEntity.ok(catalogClient.getCatalog()); }
}