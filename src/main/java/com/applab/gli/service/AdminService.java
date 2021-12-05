package com.applab.gli.service;

import com.applab.gli.dto.AdminDTO;
import com.applab.gli.dto.PagingAdminsDTO;
import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.exception.UploadAdminPhotoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    ResponseEntity<ResponseDTO> getAllAdminsPageable(PagingAdminsDTO pagingAdminsDTO);
    ResponseEntity<ResponseDTO> getAdminById(Long adminId);
    ResponseEntity<ResponseDTO> saveAdmin(AdminDTO adminDTO);
    ResponseEntity<ResponseDTO> uploadAdminPhoto(Long adminId, MultipartFile adminPhoto) throws UploadAdminPhotoException;
    ResponseEntity<ResponseDTO> updateAdmin(Long adminId, AdminDTO adminDTO);
    ResponseEntity<ResponseDTO> deleteAdmin(Long adminId);

}
