package com.applab.gli.controller;

import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.exception.UploadAdminPhotoException;
import com.applab.gli.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Operation(summary = "Servicio para obtener a los administradores paginados")
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllAdminsPageable(@RequestBody PagingAdminsDTO pagingAdminsDTO){
        return adminService.getAllAdminsPageable(pagingAdminsDTO);
    }

    @Operation(summary = "Servicio para obtener a un administrador por medio de su id")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getAdminById(@PathVariable("id") Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @Operation(summary = "Servicio para guardar a un nuevo administrador")
    @PostMapping
    public ResponseEntity<ResponseDTO> saveAdmin(@RequestBody AdminDTO adminDTO) {
        return adminService.saveAdmin(adminDTO);
    }

    @Operation(summary = "Servicio para subir la foto de perfil de un administrador")
    @PostMapping("/image")
    public ResponseEntity<ResponseDTO> uploadAdminPhoto(@RequestParam("id") Long adminId, @RequestParam("photo") MultipartFile adminPhoto) throws UploadAdminPhotoException {
        return adminService.uploadAdminPhoto(adminId, adminPhoto);
    }

    @Operation(summary = "Servicio para actualizar a un administrador")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAdmin(@PathVariable("id") Long adminId, @RequestBody AdminDTO adminDTO) {
        return adminService.updateAdmin(adminId, adminDTO);
    }

    @Operation(summary = "Servicio para borrar (inhabilitar) a un administrador por medio de su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAdmin(@PathVariable("id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }

}
