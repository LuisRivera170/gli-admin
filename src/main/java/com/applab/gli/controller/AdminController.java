package com.applab.gli.controller;

import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.exception.UploadAdminPhotoException;
import com.applab.gli.service.AdminService;
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

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllAdminsPageable(@RequestBody PagingAdminsDTO pagingAdminsDTO){
        return adminService.getAllAdminsPageable(pagingAdminsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getAdminById(@PathVariable("id") Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveAdmin(@RequestBody AdminDTO adminDTO) {
        return adminService.saveAdmin(adminDTO);
    }

    @PostMapping("/image")
    public ResponseEntity<ResponseDTO> uploadAdminPhoto(@RequestParam("id") Long adminId, @RequestParam("photo") MultipartFile adminPhoto) throws UploadAdminPhotoException {
        return adminService.uploadAdminPhoto(adminId, adminPhoto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAdmin(@PathVariable("id") Long adminId, @RequestBody AdminDTO adminDTO) {
        return adminService.updateAdmin(adminId, adminDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAdmin(@PathVariable("id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }

}
