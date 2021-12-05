package com.applab.gli.service;

import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.PagingDTO;
import com.applab.gli.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<ResponseDTO> getAllAdminsPageable(PagingAdminsDTO pagingAdminsDTO);
    ResponseEntity<ResponseDTO> getAdminById(Long adminId);
    ResponseEntity<ResponseDTO> saveAdmin(AdminDTO adminDTO);
    ResponseEntity<ResponseDTO> updateAdmin(Long adminId, AdminDTO adminDTO);
    ResponseEntity<ResponseDTO> deleteAdmin(Long adminId);

}
