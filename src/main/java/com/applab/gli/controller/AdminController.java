package com.applab.gli.controller;

import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.PagingDTO;
import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPostsPageable(@RequestBody PagingAdminsDTO pagingAdminsDTO){
        return adminService.getAllAdminsPageable(pagingAdminsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPostById(@PathVariable("id") Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> savePost(@RequestBody AdminDTO adminDTO) {
        return adminService.saveAdmin(adminDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePost(@PathVariable("id") Long adminId, @RequestBody AdminDTO adminDTO) {
        return adminService.updateAdmin(adminId, adminDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePost(@PathVariable("id") Long adminId) {
        return adminService.deleteAdmin(adminId);
    }

}
