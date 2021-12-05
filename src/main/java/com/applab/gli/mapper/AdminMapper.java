package com.applab.gli.mapper;

import com.applab.gli.domain.Admin;
import com.applab.gli.dto.AdminDTO;
import com.applab.gli.enumeration.Status;

import static com.applab.gli.mapper.AreaMapper.toArea;
import static com.applab.gli.mapper.AreaMapper.toAreaDTO;
import static com.applab.gli.utils.Utils.isNullOrBlank;

public class AdminMapper {

    public static Admin toAdmin(AdminDTO adminDTO) {
        return toAdmin(adminDTO, new Admin());
    }

    public static Admin toAdmin(AdminDTO adminDTO, Admin admin) {
        if (!isNullOrBlank(adminDTO.getAdminName())) {
            admin.setName(adminDTO.getAdminName());
        }
        if (!isNullOrBlank(adminDTO.getAdminLastName())) {
            admin.setLastName(adminDTO.getAdminLastName());
        }
        if (!isNullOrBlank(adminDTO.getAdminEmail())) {
            admin.setEmail(adminDTO.getAdminEmail());
        }
        if (!isNullOrBlank(adminDTO.getAdminStatus())) {
            admin.setStatus(Status.valueOf(adminDTO.getAdminStatus()));
        }
        if (null != adminDTO.getAdminArea()) {
            admin.setArea(toArea(adminDTO.getAdminArea())); ;
        }
        if (null != adminDTO.getAdminCreatedAt()) {
            admin.setCreatedAt(adminDTO.getAdminCreatedAt());
        }
        if (null != adminDTO.getAdminUpdatedAt()) {
            admin.setUpdatedAt(adminDTO.getAdminUpdatedAt());
        }
        if (null != adminDTO.getAdminDeletedAt()) {
            admin.setDeletedAt(adminDTO.getAdminDeletedAt());
        }
        return admin;
    }

    public static AdminDTO toAdminDTO(Admin admin) {
        AdminDTO newAdminDTO = new AdminDTO();
        if (null != admin.getId()) {
            newAdminDTO.setAdminId(admin.getId());
        }
        if (!isNullOrBlank(admin.getName())) {
            newAdminDTO.setAdminName(admin.getName());
        }
        if (!isNullOrBlank(admin.getLastName())) {
            newAdminDTO.setAdminLastName(admin.getLastName());
        }
        if (!isNullOrBlank(admin.getFullName())) {
            newAdminDTO.setAdminFullName(admin.getFullName());
        }
        if (!isNullOrBlank(admin.getEmail())) {
            newAdminDTO.setAdminEmail(admin.getEmail());
        }
        if (null != admin.getPhoto()) {
            newAdminDTO.setAdminPhoto(admin.getPhoto());
        }
        if (null != admin.getStatus()) {
            newAdminDTO.setAdminStatus(admin.getStatus().getStatus());
        }
        if (null != admin.getArea()) {
            newAdminDTO.setAdminArea(toAreaDTO(admin.getArea()));
        }
        if (null != admin.getCreatedAt()) {
            newAdminDTO.setAdminCreatedAt(admin.getCreatedAt());
        }
        if (null != admin.getUpdatedAt()) {
            newAdminDTO.setAdminUpdatedAt(admin.getUpdatedAt());
        }
        if (null != admin.getDeletedAt()) {
            newAdminDTO.setAdminDeletedAt(admin.getDeletedAt());
        }
        return newAdminDTO;
    }

}
